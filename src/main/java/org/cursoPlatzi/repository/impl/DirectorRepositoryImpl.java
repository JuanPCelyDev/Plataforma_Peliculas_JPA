package org.cursoPlatzi.repository.impl;

import jakarta.persistence.EntityManager;
import org.cursoPlatzi.entity.Director;
import org.cursoPlatzi.repository.DirectorRepository;

import java.util.List;

public class DirectorRepositoryImpl implements DirectorRepository {

    private final EntityManager em;

    public DirectorRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Director> listar() {
        //Listar todos los directores
        return em.createQuery("From Director", Director.class).getResultList();
    }

    @Override
    public Director porId(int id) {
        return em.find(Director.class, id);
    }

    @Override
    public void guardar(Director director) {
      //Se valida si existe el director, si existe se modifica con merge
        if(director.getIdDirector() > 0){
            em.merge(director);
        }else{
            //Si no existe se crea uno nuevo con persist
            em.persist(director);
        }
    }

    @Override
    public void eliminar(int id) {
       Director director = porId(id);
       if(director != null){
           em.remove(director);
       }else {
           System.out.println("No se encontró director con ID: " + id);
       }
    }
}
