/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.PeliculaSerie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PC
 */
@Repository
public interface PeliculaRepository  extends JpaRepository<PeliculaSerie, String>{
    
        @Query("select j from Pelicula j where j.titulo LIKE :query or j.fechaDeCreacion LIKE :query or j.calificacion.calificacion LIKE :query")
    List<PeliculaSerie> findAllByQ(@Param("query") String query);

    @Query("select j from Pelicula j where j.id = :id")
    PeliculaSerie encontrarPorId(@Param("id") Long id);

    @Query("select j from Pelicula j where j.calificacion.calificacion = :q")
    List<PeliculaSerie> findAllByCalificacion(@Param("q") String q);
    
    @Query("select j from Pelicula j where j.genero.genero = :q")
    List<PeliculaSerie> findAllByGenero(@Param("q") String q);

    @Override
    @Query("select j from Pelicula j order by j.titulo")
    List<PeliculaSerie> findAll();
    
}
