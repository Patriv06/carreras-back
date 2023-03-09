
package com.back.carreras.controller;

import com.back.carreras.model.ImageModel;
import com.back.carreras.repository.ImageRepository;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
  
public class ImageUploadController {

	@Autowired
	private ImageRepository imageRepository;

@PostMapping("/image/upload")
@CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )
public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    String fileType = file.getContentType();

    // Cargar la imagen
    BufferedImage originalImage = ImageIO.read(file.getInputStream());

    // Comprimir la imagen
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(originalImage, "png", os);
    byte[] compressedImage = os.toByteArray();
    os.close();

    ImageModel model = new ImageModel(fileName, fileType, compressedImage);
    imageRepository.save(model);

    return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
}

  @GetMapping("/imagenes/{imageName}")
          @CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )

  public static String getImageFormat(byte[] imageBytes) throws IOException {
    try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
        Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("No image readers found");
        }
        ImageReader reader = iterator.next();
        reader.setInput(imageInputStream);
        return reader.getFormatName().toLowerCase();
    }
}
}