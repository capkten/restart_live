package org.capten.live.utils;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageUtil {

    private static final int MAX_FILE_SIZE = 2 * 1024 * 1024; // 2MB

    public File compressImage(File inputFile, File outputFile) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputFile);
        float quality = 1.0f; // Initial quality

        while (true) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                Thumbnails.of(originalImage)
                        .scale(1.0) // No scaling, just compress
                        .outputQuality(quality)
                        .outputFormat("jpg")
                        .toOutputStream(baos);

                byte[] imageBytes = baos.toByteArray();
                if (imageBytes.length <= MAX_FILE_SIZE) {
                    try (OutputStream os = new FileOutputStream(outputFile)) {
                        os.write(imageBytes);
                    }
                    break;
                }

                // Reduce quality if the file size is still too large
                quality -= 0.1f;
                if (quality < 0.1f) {
                    throw new IOException("Could not compress image to the desired size.");
                }
            }
        }

        return outputFile;
    }

    public boolean checkImageType(MultipartFile file) {
        // Check if the file is an image
        if (file == null || file.getContentType() == null) {
            return false;
        }
        String contentType = file.getContentType();
        return contentType.startsWith("image/");
    }

    public boolean checkImageSize(MultipartFile file, int maxSize) {
        // Check if the file size is within the allowed limit
        return file.getSize() <= maxSize;
    }
}
