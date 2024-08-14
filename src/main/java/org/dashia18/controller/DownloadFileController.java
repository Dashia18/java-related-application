package org.dashia18.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dashia18.service.downloadmanager.DownLoadManager;
import org.dashia18.service.downloadmanager.FileInfo;
import org.dashia18.util.Constants;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.COMMON_API_URI)
public class DownloadFileController {
    public static final String FILE_API_URI = "file/";
    public static final String FILES_API_URI = "files/";

    private final DownLoadManager thirdPartyYouTubeLibService;

    @GetMapping(value = FILES_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FileInfo> files() {

        log.info("A request to retrieve all files has been received");

        return thirdPartyYouTubeLibService.getFiles();
    }

    @GetMapping(value = FILE_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public FileInfo file(@RequestParam @Valid @NotNull long id) {

        log.info("A request to retrieve file for id {} has been received", id);

        return thirdPartyYouTubeLibService.getFileInfo(id);
    }

    @GetMapping(value = FILE_API_URI + "{id}/download", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadFile(@PathVariable long id) {
        log.info("A request to download file for id {}  has been received", id);

        return thirdPartyYouTubeLibService.downLoadFile(id);
    }
}
