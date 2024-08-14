package org.dashia18.service.downloadmanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.dashia18.mapper.FileMapper;
import org.dashia18.storage.model.File;
import org.dashia18.storage.repo.FileRepo;
import org.springframework.stereotype.Service;

@Service("fileDownloader")
@Slf4j
@RequiredArgsConstructor
public class ThirdPartyDownloadLibService implements ThirdPartyDownloadLib {
    private final FileRepo fileRepo;
    private final FileMapper mapper;

    @Override
    public List<FileInfo> getFiles() {
        return mapper.toDtos(fileRepo.findAll());
    }

    @Override
    public FileInfo getFileInfo(long id) {
        return mapper.toDto(getFile(id));
    }

    @Override
    public File getFile(long id) {
        Optional<File> byId = fileRepo.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new NoSuchElementException("File with id=" + id + "is not found");
        }
    }

    @SneakyThrows
    @Override
    public byte[] downLoadFile(File file) {
        String path = "files/" + file.getFileName();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        try (InputStream is = classLoader.getResourceAsStream(path)) {
            if (is != null) {
                return IOUtils.toByteArray(is);
            }
        } catch (IOException e) {
            log.error("Some error occurred", e);
        }
        throw new IllegalStateException("File cannot be downloaded");
    }
}
