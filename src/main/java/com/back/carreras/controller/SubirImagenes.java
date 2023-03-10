
package com.back.carreras.controller;

import com.back.carreras.model.ImageModel;
import com.back.carreras.repository.ImageRepository;

import java.io.IOException;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;




@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )

public class SubirImagenes {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
		imageRepository.save(imageModel);
		return ResponseEntity.ok("Imagen subida con éxito");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		Optional<ImageModel> imageModel = imageRepository.findById(id);
		if(imageModel.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf(imageModel.get().getType()));
			return new ResponseEntity<>(imageModel.get().getPicByte(), headers, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<ImageModel>> getAllImages() {
		List<ImageModel> imageModels = imageRepository.findAll();
		return ResponseEntity.ok(imageModels);
	}
        @GetMapping("/getImageByName/{name}")
        public ResponseEntity<byte[]> getImageByName(@PathVariable String name) {
             Optional<ImageModel> optionalImage = imageRepository.findByName(name);
             ImageModel image = optionalImage.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
             return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getPicByte());
}
       @DeleteMapping("/delete/{name}")
       public ResponseEntity<String> deleteImageByName(@PathVariable String name) {
              Optional<ImageModel> imageModel = imageRepository.findByName(name);
              if(imageModel.isPresent()) {
              imageRepository.delete(imageModel.get());
              return ResponseEntity.ok("Imagen eliminada con éxito");
               } else {
                return ResponseEntity.notFound().build();
                      }
        }
}
