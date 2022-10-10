package com.itsol.recruit.service;

import com.itsol.recruit.core.Constants;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface UploadService {
    File save(MultipartFile file, String folder);
    void init();

    void save(String username, MultipartFile file, Long jobId);

    void saveAvatar(MultipartFile file, Long userId);

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();
}
