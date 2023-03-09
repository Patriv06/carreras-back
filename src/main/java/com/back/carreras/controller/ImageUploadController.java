
package com.back.carreras.controller;

import com.back.carreras.model.ImageModel;
import com.back.carreras.repository.ImageRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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


@RequestMapping("/image")
public class ImageUploadController {

	@Autowired
	private ImageRepository imageRepository;

	@PostMapping("/upload")
        @CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )

	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			imageRepository.save(imageModel);
			return ResponseEntity.ok().body("Imagen subida correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Error al subir la imagen.");
		}
	}
        @GetMapping("/{name}")
             @CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )

public ResponseEntity<byte[]> getImageByName(@PathVariable String name) {
    Optional<ImageModel> imageModelOptional = imageRepository.findByName(name);
    if (imageModelOptional.isPresent()) {
        ImageModel imageModel = imageModelOptional.get();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(imageModel.getType())).body(imageModel.getPicByte());
    } else {
        return ResponseEntity.notFound().build();
    }
}
        
}