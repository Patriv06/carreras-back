
package com.back.carreras.repository;

import com.back.carreras.model.ImageModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	Optional<ImageModel> findByName(String name);
        
        void deleteByName(String name);
    
}
