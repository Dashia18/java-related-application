package org.dashia18.service.downloadmanager;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInfo {
    private long id;
    private String fileName;
    private String name;
    private long size;
    private String metaInfo;
}
