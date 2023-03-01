
package com.back.carreras.service;

import com.back.carreras.model.PuntPorCarrera;
import com.back.carreras.repository.PuntPorCarreraRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PuntPorCarreraService implements IPuntPorCarreraService {
    @Autowired
       public PuntPorCarreraRepository ppcarrRepo;

    @Override
    public List<PuntPorCarrera> verPuntPorCarrera() {
        Sort sortOrder  = Sort.by("puestoPPCarrera").descending();
        
        return ppcarrRepo.findAll(sortOrder);
    }

    @Override
    public void crearPuntPorCarrera(PuntPorCarrera ppc) {
        ppcarrRepo.save(ppc);
    }

    @Override
    public void borrarPuntPorCarrera(Long id) {
        ppcarrRepo.deleteById(id);
    }

    @Override
    public PuntPorCarrera buscarPuntPorCarrera(Long id) {
       return ppcarrRepo.findById(id).orElse(null);
    }

    @Override
    public void modifPuntPorCarrera(PuntPorCarrera ppc) {
        ppcarrRepo.save(ppc);
    }

  
   
  
    
}
