package org.cursoPlatzi.repository.impl;

import jakarta.persistence.EntityManager;
import org.cursoPlatzi.entity.Pelicula;
import org.cursoPlatzi.repository.PeliculaRepository;

import java.util.List;

public class PeliculaRepositoryImpl implements PeliculaRepository {

    private final EntityManager em;

    public PeliculaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Pelicula> listar() {
        //Se usa JPQL para traer todas las películas
        return em.createQuery("From Pelicula", Pelicula.class).getResultList();
    }

    @Override
    public Pelicula porId(int id) {
        // em.find busca directamente por la llave primaria
        return em.find(Pelicula.class, id);
    }
    @Override
    public void guardar(Pelicula pelicula) {
        //Si la pelicula ya existe se actualiza con merge
        if(pelicula.getIdPelicula() > 0 ){
            em.merge(pelicula);
        }else{
            // Si la pelicula no existe se crea con persist
            em.persist(pelicula);
        }
    }

    @Override
    public void eliminar(int id) {
        Pelicula pelicula = porId(id);
          if(pelicula != null){
              em.remove(pelicula);
          }else {
              System.out.println("No se encontró la película con ID: " + id);
          }

    }

    @Override
    public List<Pelicula> buscarPorTitulo(String titulo) {
        return  em.createQuery("SELECT p FROM Pelicula p WHERE p.titulo LIKE :t ", Pelicula.class)
                .setParameter("t", "%" + titulo + "%")
                .getResultList();
    }

    @Override
    public List<Pelicula> buscarPopulares() {
        return em.createQuery("SELECT p FROM Pelicula p WHERE p.calificacion > 4", Pelicula.class).getResultList();
    }

    @Override
    public List<Pelicula> buscarPorCategoria(int idCategoria) {
        return em.createQuery("SELECT p FROM Pelicula p WHERE p.categoria.idCategoria = :c", Pelicula.class)
                .setParameter("c", idCategoria)
                .getResultList();
    }

    @Override
    public String verPelicula(String titulo) {
        return "Reproduciendo Película... " + titulo + "🍿🎬";
    }

    @Override
    public int duracionTotal() {
        return listar().stream()
                .mapToInt(Pelicula::getDuracion)
                .sum();
    }
}
