
package com.back.carreras.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public String uploadImage(@RequestParam("file") MultipartFile file)throws Exception{
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());

 

   
    


   String  Path_Directory="/src/main/resources/static/image";
    Files.copy(file.getInputStream(), Paths.get(Path_Directory+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
     
    
    return "se subió bien";

   
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






