package com.crcl.iam.clients;

import com.crcl.iam.configuration.clients.FeignFormConfig;
import com.crcl.iam.configuration.clients.ServerOauth2ClientConfig;
import com.crcl.core.dto.responses.FileUploadResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@FeignClient(name = "${client.srvStorage.name}",
        url = "${client.srvStorage.url}",
        configuration = {
                ServerOauth2ClientConfig.class,
                FeignFormConfig.class
        })
public interface ServerStorageClient {
    @GetMapping("/files/{tag}/{objectName}")
    ByteArrayResource downloadFile(@PathVariable("objectName") String objectName, @PathVariable("tag") String tag);

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FileUploadResult uploadFile(@RequestPart("file") MultipartFile multipartFile);

    @PostMapping(value = "/files/all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<FileUploadResult> uploadMultipleFile(@RequestPart("files") List<MultipartFile> multipartFiles);
}
