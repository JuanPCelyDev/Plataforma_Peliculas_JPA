package org.cursoPlatzi.view;

import jakarta.persistence.EntityManager;
import org.cursoPlatzi.entity.Categoria;
import org.cursoPlatzi.entity.Director;
import org.cursoPlatzi.entity.Pelicula;
import org.cursoPlatzi.repository.CategoriaRepository;
import org.cursoPlatzi.repository.DirectorRepository;
import org.cursoPlatzi.repository.PeliculaRepository;
import org.cursoPlatzi.util.ScannerUtil;

import java.util.List;


public class MenuApp {

    private final EntityManager em;
    private final PeliculaRepository peliculaRepository;
    private final CategoriaRepository categoriaRepository;
    private  final DirectorRepository directorRepository;

    private static final String PELICULA_ENTIDAD = "película";
    private static final String CATEGORIA_ENTIDAD = "categoria";
    private static final String DIRECTOR_ENTIDAD = "director";


    //Opciones menu inicial
    private static final int ENTRAR_COMO_INVITADO = 1;
    private static final int ENTRAR_COMO_ADMIN = 2;

    //Opciones Usuario
    private static final int MOSTRAR_CONTENIDO_OPCION = 3;
    private static final int BUSCAR_NOMBRE_OPCION = 4;
    private static final int BUSCAR_CONTENIDO_POR_CATEGORIA_OPCION = 5;
    private static final int MOSTRAR_CONTENIDO_POPULAR_OPCION = 6;

    //Opciones Administrador
    private static final int AGREGAR_PELICULA_OPCION = 7;
    private static final int AGREGAR_CATEGORIA_OPCION = 8;
    private static final int AGREGAR_DIRECTOR_OPCION = 9;
    private static final int ELIMINAR_PELICULA_OPCION = 10;
    private static final int ELIMINAR_CATEGORIA_OPCION = 11;
    private static final int ELIMINAR_DIRECTOR_OPCION = 12;

    private static final int SALIR_OPCION = 0;


    public MenuApp(EntityManager em,PeliculaRepository peliculaRepository, CategoriaRepository categoriaRepository, DirectorRepository directorRepository) {
        this.em = em;
        this.peliculaRepository = peliculaRepository;
        this.categoriaRepository = categoriaRepository;
        this.directorRepository = directorRepository;
    }



    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            int rol = ScannerUtil.capturarNumero("""
                    Seleccioné su rol
                    [1] Entrar como invitado
                    [2] Entrar como Administrador
                    [0]  Salir
                    """);

            switch (rol) {
                case ENTRAR_COMO_INVITADO -> menuUsuario();
                case ENTRAR_COMO_ADMIN -> menuAdministrador();
                case SALIR_OPCION -> opcion = SALIR_OPCION;
            }
        }
    }

    private void menuUsuario() {
        int opcion = -1;
        while (opcion != 0){

            System.out.println("Más de " + peliculaRepository.listar().size() + " películas disponibles para ti." + "🎥" + "✨");
            System.out.println("Más de " + peliculaRepository.duracionTotal() + " minutos de entretenimiento a tu alcance." + "🎬" + "✨"+ "\n");

            int opcionElegida = ScannerUtil.capturarNumero("""
                    ¿Qué deseas hacer?
                    [3] Mostrar películas disponibles
                    [4] Buscar por nombre
                    [5] Buscar por categoría
                    [6] Mostrar películas populares 👌 
                    [0] Cerrar sesión
                    """);

            switch (opcionElegida){
                case MOSTRAR_CONTENIDO_OPCION ->{
                    System.out.println("========== 🍿 Listado de Peliculas 🎬 ===========");
                    peliculaRepository.listar().forEach(System.out::println);
                }
                case BUSCAR_NOMBRE_OPCION -> {
                    String titulo = ScannerUtil.capturarTexto("Por favor ingresa el título de la película");
                    System.out.println("========== 🍿 Resultados de la búsqueda 🎬 ===========");
                    List<Pelicula> peliculas =  peliculaRepository.buscarPorTitulo(titulo);
                    peliculas.forEach(System.out::println);

                }

                case BUSCAR_CONTENIDO_POR_CATEGORIA_OPCION -> {
                    // 1. Se trae el listado de todas las categorías registradas
                    List<Categoria> categorias = categoriaRepository.listar();
                    if (categorias.isEmpty()) {
                        System.out.println("No hay categorías registradas.");
                        return;
                    }

                    mostrarCategorias();

                    int idElegido = ScannerUtil.capturarNumero("Ingrese un id de categoría a buscar");
                    List<Pelicula> peliculas = peliculaRepository.buscarPorCategoria(idElegido);
                    System.out.println("========== 🍿 Películas del genero" + "🎬 ===========");
                    peliculas.forEach(System.out::println);

                }

                case MOSTRAR_CONTENIDO_POPULAR_OPCION -> {
                    System.out.println("========== 🍿 Películas Populares 🎬 ===========");
                    peliculaRepository.buscarPopulares().forEach(System.out::println);
                }
                case SALIR_OPCION -> opcion = SALIR_OPCION;
            }
        }
    }

    private void menuAdministrador() {
        int opcion = -1;

        while (opcion != 0){
            int opcionElegida = ScannerUtil.capturarNumero("""
                    ¿Qué deseas hacer?
                    [7] Registrar película
                    [8] Registrar categoría
                    [9] Registrar director
                    [10] Eliminar Película
                    [11] Eliminar Categoría
                    [12] Eliminar Director
                    [0] Cerrar sesión
                    """);

            switch (opcionElegida){
                case AGREGAR_PELICULA_OPCION -> crearPelicula();
                case AGREGAR_CATEGORIA_OPCION -> crearCategoria();
                case AGREGAR_DIRECTOR_OPCION -> crearDirector();
                case ELIMINAR_PELICULA_OPCION -> eliminar(PELICULA_ENTIDAD);
                case ELIMINAR_CATEGORIA_OPCION -> eliminar(CATEGORIA_ENTIDAD);
                case ELIMINAR_DIRECTOR_OPCION -> eliminar(DIRECTOR_ENTIDAD);
                case SALIR_OPCION -> opcion = SALIR_OPCION;
            }
        }
    }

    public void crearPelicula(){
        System.out.println("\n--- REGISTRAR NUEVA PELÍCULA ---");
     try {
         String titulo = ScannerUtil.capturarTexto("Ingrese el titulo de la película");
         String fecha = ScannerUtil.capturarTexto("Ingrese el año de lanzamiento de la película(AAAA)");
         String descripcion = ScannerUtil.capturarTexto("Ingrese descripción de la película");
         double calificacion = ScannerUtil.capturarDecimal("Ingrese calificación de la película (0.0 - 5.0)");
         int duracion = ScannerUtil.capturarNumero("Ingrese la duración de la película(minutos)");

         System.out.println("\n--- Seleccioné un Director ---");
         mostrarDirectores();
         int idDir = ScannerUtil.capturarNumero("ingrese Id de director seleccionado");
         Director directorElegido = directorRepository.porId(idDir);

         System.out.println("\n--- Seleccioné una Categoría ---");
         mostrarCategorias();
         int idCate = ScannerUtil.capturarNumero("ingrese Id de Categoria seleccionado");
         Categoria categoriaElegida = categoriaRepository.porId(idCate);

         // 4. Validación y Persistencia
         if (directorElegido != null && categoriaElegida != null) {
             Pelicula pelicula = new Pelicula(titulo, fecha, descripcion, calificacion, duracion, directorElegido, categoriaElegida);

             em.getTransaction().begin();
             peliculaRepository.guardar(pelicula);
             em.getTransaction().commit();
             System.out.println(" ✨ Película guardada exitosamente ✨");
         } else {
             System.out.println("❌ Error: Director o Categoría no encontrados. Intente de nuevo.");
         }
     } catch (Exception e) {
         System.out.println("Error al agregar película: " + e.getMessage());
     }


    }

    public void crearCategoria(){
        System.out.println("\n--- REGISTRAR NUEVA CATEGORÍA ---");
       try {
           String nuevaCategoria = ScannerUtil.capturarTexto("Ingrese el nombre de la nueva categoría");
           Categoria categoria = new Categoria(nuevaCategoria);
           em.getTransaction().begin();
           categoriaRepository.guardar(categoria);
           em.getTransaction().commit();
           System.out.println("Categoría guardada existosamente");
       }catch (Exception e) {
           System.out.println("Error al agregar categoría: " + e.getMessage());
       }
    }

    public void crearDirector(){
        System.out.println("\n--- REGISTRAR NUEVO DIRECTOR ---");
        try {
            String nombreDirector = ScannerUtil.capturarTexto("Ingrese Nombre del director");
            String apellidoDirector = ScannerUtil.capturarTexto("Ingrese Apellido del director");

            Director director = new Director(nombreDirector,apellidoDirector);

            em.getTransaction().begin();
            directorRepository.guardar(director);
            em.getTransaction().commit();

            System.out.println("Director guardado exitosamente");
        }catch (Exception e) {
            System.out.println("Error al agregar director: " + e.getMessage());
        }
    }




    public void eliminar(String entidad){
        System.out.println("\n---✖️ ELIMINAR " + entidad.toUpperCase() +" ✖️---");
        try{
            if((PELICULA_ENTIDAD).equalsIgnoreCase(entidad)){
                mostrarPeliculas();
            } else if ((CATEGORIA_ENTIDAD).equalsIgnoreCase(entidad)) {
                mostrarCategorias();
            }else {
                mostrarDirectores();
            }
            int idEliminar = ScannerUtil.capturarNumero("Ingrese el id de " + entidad + " a eliminar");
            String confirmar = ScannerUtil.capturarTexto("¿Está seguro de eliminar '" + entidad + "'? (S/N)");
            if (confirmar.equalsIgnoreCase("S")) {
                em.getTransaction().begin();
                if ((PELICULA_ENTIDAD).equalsIgnoreCase(entidad)) {
                    peliculaRepository.eliminar(idEliminar);
                } else if ((CATEGORIA_ENTIDAD).equalsIgnoreCase(entidad)) {
                    categoriaRepository.eliminar(idEliminar);
                } else {
                    directorRepository.eliminar(idEliminar);
                }
                em.getTransaction().commit();
                System.out.println("Se ha eliminado " + entidad + " con id: " + idEliminar);
            }

        }catch (Exception e){
            System.out.println("Error al eliminar " + entidad + "existen registros asociados o el ID no existe");
        }

    }


    private void mostrarPeliculas(){
        List<Pelicula> peliculas = peliculaRepository.listar();
        if(peliculas.isEmpty()){
            System.out.println("⚠️ No hay películas registradas aún.");
            return;
        }
        peliculas.forEach(p->{
            System.out.printf("[%d], %s%n", p.getIdPelicula(), p.getTitulo());
        });
    }

    private void mostrarCategorias(){
        List<Categoria> categorias = categoriaRepository.listar();
        if(categorias.isEmpty()){
            System.out.println("⚠️ No hay categorías registradas aún.");
            return;
        }
        categorias.forEach(c->{
            System.out.printf("[%d], %s%n", c.getIdCategoria(), c.getCategoria());
        });
    }

    private void mostrarDirectores(){
     List<Director> directores = directorRepository.listar();
        if(directores.isEmpty()){
            System.out.println("⚠️ No hay directores registrados aún.");
            return;
        }
        directores.forEach(d -> {
            System.out.printf("[%d], %s, %s%n", d.getIdDirector(), d.getNombre(), d.getApellidos());
        });
    }

}
