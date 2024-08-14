package org.dashia18.service.downloadmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dashia18.mapper.FileMapper;
import org.dashia18.storage.model.File;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("cashedService")
public class CashedFilesService implements ThirdPartyDownloadLib {
    private final ThirdPartyDownloadLib thirdPartyDownloadLib;
    private final FileMapper mapper;

    private final Map<Long, FileInfo> cache = new HashMap<>();

    public CashedFilesService(@Qualifier("fileDownloader") ThirdPartyDownloadLib thirdPartyDownloadLib,
                              FileMapper mapper) {
        this.thirdPartyDownloadLib = thirdPartyDownloadLib;
        this.mapper = mapper;
    }


    @Override
    public List<FileInfo> getFiles() {
        return thirdPartyDownloadLib.getFiles();
    }

    @Override
    public FileInfo getFileInfo(long id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            FileInfo fileInfo = thirdPartyDownloadLib.getFileInfo(id);
            cache.put(id, fileInfo);
            return fileInfo;
        }
    }

    @Override
    public File getFile(long id) {
        if (cache.containsKey(id)) {
            return mapper.fromDto(cache.get(id));
        } else {
            File file = thirdPartyDownloadLib.getFile(id);
            cache.put(id, mapper.toDto(file));
            return file;
        }
    }

    @Override
    public byte[] downLoadFile(File file) {
        return thirdPartyDownloadLib.downLoadFile(file);
    }
}
