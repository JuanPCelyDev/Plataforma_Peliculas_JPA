# 🎬 Plataforma Pelis - JPA - Hibernate CRUD

Este proyecto es una aplicación de consola en Java que gestiona un catálogo de películas, 
directores y categorías. Fue desarrollado para practicar conceptos avanzados de persistencia de datos,
arquitectura de software y principios de programación limpia (Clean Code).

## 🚀 Funcionalidades

### 👤 Modo Usuario (Invitado)
*   **Bienvenida:** visualización mensaje con cantidad de películas disponibles y tiempo de visualización total 
*   **Listar Catálogo:** Visualización de todas las películas disponibles.
*   **Búsqueda Avanzada:** Filtros por título de película y por categoría.
*   **Recomendados:** Sección de películas populares basada en calificaciones.

### 🛠️ Modo Administrador
*   **Gestión Completa (CRUD):** Registro y eliminación de películas, directores y categorías.
*   **Integridad de Datos:** Validaciones para asegurar que las relaciones entre tablas sean correctas.

## 🛠️ Tecnologías Utilizadas

*   **Java 17:** Uso de lambdas, records y nuevas APIs de texto.
*   **JPA / Hibernate:** Mapeo objeto-relacional y gestión de transacciones.
*   **MySQL:** Base de datos relacional para la persistencia.
*   **Maven:** Gestión de dependencias.

## 🏗️ Arquitectura y Patrones

El proyecto sigue una estructura organizada para facilitar el mantenimiento:
*   **Pattern Repository:** Desacoplamiento de la lógica de acceso a datos de la lógica de negocio.
*   **Inyección de Dependencias:** El `EntityManager` se propaga desde el Main hacia los componentes que lo requieren.
*   **DRY (Don't Repeat Yourself):** Refactorización de métodos comunes para evitar duplicidad de código.

## ⚙️ Configuración del Proyecto

1.  **Base de Datos:** Crea una base de datos en MySQL llamada `tu_nombre_db`.
2.  **Persistencia:** Configura tus credenciales (usuario y contraseña) en el archivo:
    `src/main/resources/META-INF/persistence.xml`
3.  **Ejecución:** Ejecuta la clase `Main.java` para iniciar la aplicación de consola.

---
Desarrollado con ❤️ por [Juan Pablo Cely Millán/ JuanPCelyDev en GITHUB]
