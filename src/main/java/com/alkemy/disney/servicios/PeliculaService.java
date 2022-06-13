/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.PeliculaSerie;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.errores.ErrorServicio;
import com.alkemy.disney.repositorios.PeliculaRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliRepo;

    public void crearPeliSerie(String imagen, String titulo, Date fecha, String calificacion, Personaje personaje) throws Exception {

        validacion(imagen, titulo, fecha, calificacion, personaje);

        PeliculaSerie pelis = new PeliculaSerie();

        pelis.setImagen(imagen);
        pelis.setTitulo(titulo);
        pelis.setFecha(fecha);
        pelis.setCalificacion(calificacion);
        pelis.setPersonaje(personaje);

        peliRepo.save(pelis);
    }

    public void modificarPeliSerie(String id, String imagen, String titulo, Date fecha, String calificacion, Personaje personaje) throws Exception  {

        validacion(imagen, titulo, fecha, calificacion, personaje);

        Optional<PeliculaSerie> respuesta = peliRepo.findById(id);
        if (respuesta.isPresent()) {
            PeliculaSerie pelis = respuesta.get();
            pelis.setImagen(imagen);
            pelis.setTitulo(titulo);
            pelis.setFecha(fecha);
            pelis.setCalificacion(calificacion);
            pelis.setPersonaje(personaje);
            peliRepo.save(pelis);
        } else {
            throw new ErrorServicio("no se encontro la peli solicitada");
        }

    }

    public void eliminaePeliSerie(String id){
         Optional<PeliculaSerie> respuesta = peliRepo.findById(id);
         if (respuesta.isPresent()) {
             PeliculaSerie pelis = respuesta.get();
             peliRepo.delete(pelis);
            
        }
        
    }
    
    
    private void validacion(String imagen, String titulo, Date fecha, String calificacion, Personaje personaje) throws Exception {
        if (imagen == null || imagen.isEmpty()) {
            throw new ErrorServicio("debe guardar una imagen");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("el titulo no puede ser nulo");
        }
        if (fecha == null) {
            throw new ErrorServicio("la fecha debe ser correcta");
        }
        if (calificacion == null || calificacion.isEmpty()) {
            throw new ErrorServicio("debe ingresar una calificacion");
        }
        if (personaje == null) {
            throw new ErrorServicio("debe guardar un personaje existente");
        }
    }
}
