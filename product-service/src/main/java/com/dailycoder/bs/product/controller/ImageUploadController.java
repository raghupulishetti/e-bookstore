package com.dailycoder.bs.product.controller;

import com.dailycoder.bs.common.exception.GenericException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/*
    Lot of improvement s can be done in image upload section.
    We can use AWS s3 to store images instead of this.
    Or we can have File Upload service as a separate service.
    Or we can move this to commons if we are using this feature in multiple services.
    For time being will go with no-cost implementation.
 */
@RestController("/products")
public class ImageUploadController {

    @PostMapping("/image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        if (file == null) {
            throw new GenericException("Invalid Image!!");
        }
        UUID uuid = UUID.randomUUID();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Files.createDirectories(Paths.get("images"));
        Path path = Paths.get("images/" + uuid + "_" + fileName);
        Path absolutePath = path.toAbsolutePath();
        try {
            Files.copy(file.getInputStream(), absolutePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        response.put("imageId", uuid + "_" + fileName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable String imageId) throws IOException {
        Optional<Path> images = Files.list(Paths.get("images")).filter(img -> img.getFileName().toString().equals(imageId)).findFirst();
        if (images.isPresent()) {
            final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(images.get()));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(inputStream.contentLength())
                    .body(inputStream);
        }
        return ResponseEntity.ok().build();
    }
}