/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.PeliculaSerie;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.errores.ErrorServicio;
import com.alkemy.disney.repositorios.PersonajeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class PersonajeService {

    @Autowired
    PersonajeRepository personajeRepo;

    public void crearPersonaje(String imagen, String nombre, String edad, double peso, String historia) throws ErrorServicio {

        validar(imagen, nombre, edad, peso, historia);

        Personaje personaje = new Personaje();

        personaje.setImagen(imagen);
        personaje.setNombre(nombre);
        personaje.setEdad(edad);
        personaje.setPeso(peso);
        personaje.setHistoria(historia);

        personajeRepo.save(personaje);

    }

    public void modificarPersonaje(String id, String imagen, String nombre, String edad, double peso, String historia) throws Exception {

        validar(imagen, nombre, edad, peso, historia);
        Optional<Personaje> respuesta = personajeRepo.findById(id);

        if (respuesta.isPresent()) {

            Personaje personaje = respuesta.get();

            personaje.setImagen(imagen);
            personaje.setNombre(nombre);
            personaje.setEdad(edad);
            personaje.setPeso(peso);
            personaje.setHistoria(historia);

            personajeRepo.save(personaje);

        } else {
            throw new ErrorServicio("Error al buscar el personaje a modificar");
        }

    }

    public void eliminarPersonaje(String id) throws Exception {
        
    Optional<Personaje> respuesta = personajeRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Personaje personaje = respuesta.get();
            personajeRepo.delete(personaje);
        }else{
            throw new ErrorServicio("no se encontro personaje para eliminar");
        }
        
    }

    private void validar(String imagen, String nombre, String edad, double peso, String historia) throws ErrorServicio {
        
         if (imagen == null || imagen.isEmpty()) {
            throw new ErrorServicio("debe guardar una imagen");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("el nombre no puede ser nulo");
        }
        if (edad == null || edad.isEmpty()) {
            throw new ErrorServicio("la edad debe ser correcta");
        }
        if (peso < 0 ) {
        } else {
            throw new ErrorServicio("debe ingresar peso del personaje");
        }
        if (historia == null || historia.isEmpty() ) {
            throw new ErrorServicio("debe agregar una historia sobre el personaje");
        }

    }
}
