package org.dashia18.service.downloadmanager;

import java.util.List;
import org.dashia18.storage.model.File;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DownLoadManager {
    private final ThirdPartyDownloadLib thirdPartyDownloadLib;

    public DownLoadManager(@Qualifier("cashedService") ThirdPartyDownloadLib thirdPartyDownloadLib) {
        this.thirdPartyDownloadLib = thirdPartyDownloadLib;
    }

    public List<FileInfo> getFiles() {
        return thirdPartyDownloadLib.getFiles();
    }

    public FileInfo getFileInfo(long id) {
        return thirdPartyDownloadLib.getFileInfo(id);

    }

    public byte[] downLoadFile(long id) {
        File file = thirdPartyDownloadLib.getFile(id);
        return thirdPartyDownloadLib.downLoadFile(file);
    }
}
