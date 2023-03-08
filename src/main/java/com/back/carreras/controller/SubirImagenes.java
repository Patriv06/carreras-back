
package com.back.carreras.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SubirImagenes {
    @PostMapping("/upload-file")
            @CrossOrigin(origins={"https://rankingpilotos.web.app","http://localhost:4200","https://ranking-backoffice.web.app", "https://carreras-app-aoh3.vercel.app/"} )
    public String uploadImage(@RequestParam("file") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        return "se subió bien";

    }
    
}
