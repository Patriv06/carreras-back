
package com.back.carreras.service;



import com.back.carreras.model.Categorias;
import com.back.carreras.repository.CategoriasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService implements ICategoriasService{
     @Autowired
       public CategoriasRepository catRepo;

    @Override
    public List<Categorias> verCategorias() {
         Sort sortOrder; 
         sortOrder = Sort.by("idCat").descending();
        return catRepo.findAll(sortOrder);
      
    }

    @Override
    public void crearCategorias(Categorias cat) {
        catRepo.save(cat);
    }

    @Override
    public void borrarCategorias(Long id) {
        catRepo.deleteById(id);
    }

    @Override
    public Categorias buscarCategorias(Long id) {
        return catRepo.findById(id).orElse(null);
    }

    @Override
    public void modifCategorias(Categorias cat) {
        catRepo.save(cat);
    }

    

   

   
    
}
