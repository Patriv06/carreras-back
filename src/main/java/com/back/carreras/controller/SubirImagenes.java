
package com.back.carreras.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
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

  
   String  Path_Directory=new ClassPathResource("templates/").getFile().getAbsolutePath();
  
     Files.copy(file.getInputStream(), Paths.get(Path_Directory+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        return "se subió bien"+Path_Directory;

    }
    
}
