package ru.hogwarts.school_3.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school_3.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    Avatar findAvatar(Long id);
}
