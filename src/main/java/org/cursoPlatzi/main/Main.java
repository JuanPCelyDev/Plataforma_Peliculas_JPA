package org.cursoPlatzi.main;


import jakarta.persistence.EntityManager;
import org.cursoPlatzi.repository.CategoriaRepository;
import org.cursoPlatzi.repository.DirectorRepository;
import org.cursoPlatzi.repository.PeliculaRepository;
import org.cursoPlatzi.repository.impl.CategoriaRepositoryImpl;
import org.cursoPlatzi.repository.impl.DirectorRepositoryImpl;
import org.cursoPlatzi.repository.impl.PeliculaRepositoryImpl;
import org.cursoPlatzi.util.JPAUtil;
import org.cursoPlatzi.view.MenuApp;

public class Main {

    public static final String NOMBRE_PLATAFORMA = "Plataforma Pelis - JPA";
    public static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        System.out.println("Bienvenido a "+ NOMBRE_PLATAFORMA + "🍿" + " - V" + VERSION + "\n");

        System.out.println("¡Disfruta de tu experiencia en " + NOMBRE_PLATAFORMA + "!" + "🎉" + "✨");

        try {
            System.out.println("Intentando conectar con la base de datos...");
            EntityManager em = JPAUtil.getEntityManager();

            System.out.println("Conexión con base de datos establecida");
            try{

                //1. Se instancian los repositorios(Se inyectan dependencias)
                CategoriaRepository categoriaRepository = new CategoriaRepositoryImpl(em);
                DirectorRepository directorRepository = new DirectorRepositoryImpl(em);
                PeliculaRepository peliculaRepository = new PeliculaRepositoryImpl(em);

                //2. Se inicia el menú
                MenuApp menuApp = new MenuApp(em,peliculaRepository, categoriaRepository, directorRepository);
                menuApp.iniciar();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            em.close();
        } catch (Exception e) {
            System.err.println("❌ ERROR DE CONEXIÓN:");
            e.printStackTrace();
        }
    }
}