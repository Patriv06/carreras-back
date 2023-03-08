
package com.back.carreras.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SubirImagenes {
    @PostMapping("/upload-file")
            @CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )
    
    
              public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
          
                  try {
                      // Obtenemos la ruta donde se almacenará la imagen
                      String uploadDir = "/src/main/resources/static/image";
          
                      // Obtenemos el nombre de la imagen
                      String fileName = imageFile.getOriginalFilename();
          
                      // Creamos el archivo en la ruta especificada
                      File uploadPath = new File(uploadDir);
                      if (!uploadPath.exists()) {
                          uploadPath.mkdirs();
                      }
          
                      // Guardamos la imagen en el servidor
                      Path filePath = Paths.get(uploadDir + File.separator + fileName);
                      Files.write(filePath, imageFile.getBytes());
          
                      return "Imagen cargada correctamente";
                  } catch (IOException e) {
                      return "Error al cargar la imagen";
                  }
              }
          
@GetMapping("/imagen/{nombreArchivo}")
public ResponseEntity<ByteArrayResource> obtenerImagen(@PathVariable String nombreArchivo) throws IOException {
    String rutaImagen = "/src/main/resources/static/image/" + nombreArchivo; // Cambia esto por la ruta completa en tu servidor
    File archivo = new File(rutaImagen);
    if (!archivo.exists()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    byte[] imagenBytes = Files.readAllBytes(archivo.toPath());
    ByteArrayResource recurso = new ByteArrayResource(imagenBytes);
    return ResponseEntity.ok()
            .headers(headers)
            .contentLength(imagenBytes.length)
            .body(recurso);
}
}






