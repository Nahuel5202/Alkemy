/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.entidades.PeliculaSerie;
import com.alkemy.disney.errores.ErrorServicio;
import com.alkemy.disney.repositorios.GeneroRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class GeneroService {
    
    
    @Autowired
    GeneroRepository generoRepo;
    
    public void crearGenero(String nombre, String imagen, PeliculaSerie peliserie) throws ErrorServicio{
        
        validar(nombre, imagen, peliserie);
        
        Genero genero = new Genero();
        
        genero.setNombre(nombre);
        genero.setImagen(imagen);
        genero.setPeliculaSerie(peliserie);
         
        generoRepo.save(genero); 
        
    }
    public void eliminarGenero(String id) throws ErrorServicio{
        
        Optional<Genero> respuesta = generoRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Genero genero = respuesta.get();
            generoRepo.delete(genero);
            
        }else{
        throw new ErrorServicio("no se encontro genero con es ID");
    }
    }
    
    
    
    
    private void validar(String nombre, String imagen, PeliculaSerie peliserie) throws ErrorServicio{
        
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("debe ingresar un nombre");
        }
        if (imagen == null || imagen.isEmpty()) {
            throw new ErrorServicio("debes ingresar uan direccion de la imagen");
        }if (peliserie == null ) {
            throw new ErrorServicio("deebs ingresar una pelicula o serie valida ");
        }
        
    }
}
