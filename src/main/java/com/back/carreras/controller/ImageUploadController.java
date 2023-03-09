
package com.back.carreras.controller;

import com.back.carreras.model.ImageModel;
import com.back.carreras.repository.ImageRepository;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
     @CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )



public class ImageUploadController {

	@Autowired
	private ImageRepository imageRepository;

	@PostMapping("/image/upload")
        @CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )
 public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    String fileType = file.getContentType();
    byte[] fileData = compressImage(file.getBytes());

    ImageModel model = new ImageModel(fileName, fileType, fileData);
    imageRepository.save(model);

    return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
  }

  @GetMapping("/image/{imageName}")
  public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
    Optional<ImageModel> model = imageRepository.findByName(imageName);
    if (model.isPresent()) {
      return ResponseEntity.ok().contentType(MediaType.valueOf(model.get().getType())).body(model.get().getPicByte());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private byte[] compressImage(byte[] imageData) throws IOException {
    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
    BufferedImage image = ImageIO.read(bis);

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    Thumbnails.of(image).size(1024, 1024).outputQuality(0.5).toOutputStream(bos);

    return bos.toByteArray();
  }
}