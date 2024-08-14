package org.dashia18.mapper;

import java.util.List;
import org.dashia18.service.downloadmanager.FileInfo;
import org.dashia18.storage.model.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FileMapper {
    @Mapping(target = "size", source = "fileSize")
    FileInfo toDto(File dao);

    List<FileInfo> toDtos(List<File> daos);

    File fromDto(FileInfo dto);
}
