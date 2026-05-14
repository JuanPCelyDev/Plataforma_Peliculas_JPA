package org.cursoPlatzi.repository;

import org.cursoPlatzi.entity.Pelicula;

import java.util.List;

public interface PeliculaRepository extends CrudRepository<Pelicula> {
 List<Pelicula> buscarPorTitulo(String titulo);
 List<Pelicula> buscarPopulares();
 List<Pelicula> buscarPorCategoria(int idCategoria);
 String verPelicula(String titulo);
 int duracionTotal();
}
