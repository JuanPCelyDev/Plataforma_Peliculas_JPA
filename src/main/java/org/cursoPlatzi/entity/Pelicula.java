package org.cursoPlatzi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Pelicula_tbl")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPelicula;
    private String titulo;
    private String fechaLanzamiento;
    private String descripcion;
    private double calificacion;
    private int duracion;

    @ManyToOne
    @JoinColumn(name = "fk_director")
    private Director director;

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

    public Pelicula() {
    }

    public Pelicula(String titulo, String fechaLanzamiento, String descripcion, double calificacion, int duracion, Director director, Categoria categoria) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.duracion = duracion;
        this.director = director;
        this.categoria = categoria;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getDuracion() {return duracion; }

    public void setDuracion(int duracion) {this.duracion = duracion; }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Película: " + titulo +" (" + fechaLanzamiento + "📅" + ")\n" +
                "Genero: " + categoria.getCategoria() + "🎬"+  "\n"+
                "Descripción: " + descripcion + "\n" +
                "Calificación: " + calificacion + "/5" + "🎞️✨"+  "\n"+
                "Duración: " + duracion + " minutos" + "⏳"+ "\n" +
                "Director: " + director.getNombre()+ " "+  director.getApellidos() + "\n";
    }
}
