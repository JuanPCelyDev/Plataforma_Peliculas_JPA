package org.cursoPlatzi.repository.impl;

import jakarta.persistence.EntityManager;
import org.cursoPlatzi.entity.Categoria;
import org.cursoPlatzi.repository.CategoriaRepository;

import java.util.List;

public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final EntityManager em;

    public CategoriaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Categoria> listar() {
        //Obtenemos el listado de categorias
       return em.createQuery("From Categoria", Categoria.class).getResultList();
    }

    @Override
    public Categoria porId(int id) {
        return em.find(Categoria.class,id);
    }

    @Override
    public void guardar(Categoria categoria) {
        //Se valida si exite la categoría, si existe se modifica con merge
        if(categoria.getIdCategoria() > 0){
            em.merge(categoria);
        }else{
            //Si no existe se agrega una nueva con persist
            em.persist(categoria);
        }

    }

    @Override
    public void eliminar(int id) {
        Categoria categoria = porId(id);
        if(categoria != null){
            em.remove(categoria);
        }else {
            System.out.println("No se encontró la categoría con ID: " + id);
        }

    }
}
