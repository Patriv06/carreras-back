
package com.back.carreras.service;

import com.back.carreras.model.Pilotos;
import com.back.carreras.repository.PilotosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PilotosService implements IPilotosService{
    @Autowired
       public PilotosRepository pilotRepo;

    @Override
    public List<Pilotos> verPilotos() {
        Sort sortOrder  = Sort.by("idPiloto").descending();
       
        return pilotRepo.findAll(sortOrder);
    }

    @Override
    public void crearPilotos(Pilotos pilot) {
        pilotRepo.save(pilot);
    }

    @Override
    public void borrarPilotos(Long id) {
        pilotRepo.deleteById(id);
    }

    @Override
    public Pilotos buscarPilotos(Long id) {
        return pilotRepo.findById(id).orElse(null);
    }
    

    @Override
    public void modifPilotos(Pilotos pilot) {
        pilotRepo.save(pilot);
    }

    @Override
    public List<Pilotos> verPilotosPuntAntOrdenado() {
     Sort sortOrder = Sort.by("puntajeAntPiloto").descending(); 
 
       return pilotRepo
               .findAll(sortOrder);
    }

    @Override
    public List<Pilotos> verPilotosPuntActOrdenado() {
        Sort sortOrder = Sort.by("puntajeActPiloto").descending(); 
 
       return pilotRepo
               .findAll(sortOrder);
    }

    @Override
    public List<Pilotos> verPilotosXNombre(String nom) {
        return pilotRepo.findByNombrePiloto(nom);
    }

   
    
}
