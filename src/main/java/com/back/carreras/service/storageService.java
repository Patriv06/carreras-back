
package com.back.carreras.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface storageService {
    void init();
    
    String Store(MultipartFile file);
    
    Resource loadAsResource(String filename);
}
