package org.dashia18.service.downloadmanager;

import java.util.List;
import org.dashia18.storage.model.File;

/*
Proxy design pattern
https://refactoring.guru/design-patterns/proxy
 */
public interface ThirdPartyDownloadLib {
    List<FileInfo> getFiles();

    FileInfo getFileInfo(long id);

    File getFile(long id);

    byte[] downLoadFile(File file);
}
