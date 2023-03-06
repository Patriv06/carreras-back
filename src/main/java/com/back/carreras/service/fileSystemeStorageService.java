
package com.back.carreras.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

/**
 *
 * @author priva
 */
@Service
public class fileSystemeStorageService implements storageService {
    
    @Value("${media.location}")
    private String medialocation;
    
    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() {
        rootLocation = Paths.get(medialocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException ex) {
            Logger.getLogger(fileSystemeStorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String Store(MultipartFile file) {
        try{
        if(file.isEmpty()){
        throw new RuntimeException("Error");
                          }
        String Filename = file.getOriginalFilename();
        Path destinationFile = rootLocation.resolve(Paths.get(Filename)).normalize().toAbsolutePath();
        try(InputStream inputStream=file.getInputStream()){
            long copy = Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }  
        return Filename; 
        }
        catch (IOException ex) {   
            Logger.getLogger(fileSystemeStorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }

    

    @Override
    public Resource loadAsResource(String filename) {
        try{
          Path file=rootLocation.resolve(filename);
          Resource resource=new UrlResource((file.toUri()));
          if (resource.exists() || resource.isReadable()){
            return resource;
          }else{
            throw new RuntimeException("no se puede leer el archivo"+ filename);
          }

              } catch (MalformedURLException ex) {
            Logger.getLogger(fileSystemeStorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    }