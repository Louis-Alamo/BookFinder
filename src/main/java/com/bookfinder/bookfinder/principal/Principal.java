package com.bookfinder.bookfinder.principal;

import com.bookfinder.bookfinder.model.*;
import com.bookfinder.bookfinder.repository.AutorRepository;
import com.bookfinder.bookfinder.repository.LibroRepository;
import com.bookfinder.bookfinder.service.ConsultaApi;
import com.bookfinder.bookfinder.service.ConvertirDatos;

import java.util.*;


public class Principal {

    private final String URL_BASE = "https://gutendex.com/books";
    private final String menu =
            """
                    Bienvenido a BookFinder
                                
                    1. Buscar libro por titulo
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    6. Salir.
                                
                    Opcion >""";
    /**
     * Objetos de apoyo para el procesamiento de datos
     */
    private final Scanner entrada = new Scanner(System.in);
    private final ConsultaApi consultaApi = new ConsultaApi();
    private final ConvertirDatos conversor = new ConvertirDatos();

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }


    /**
     * Metodo principal de la aplicacion donde se ejecuta el menu principal
     */
    public void Menu() {
        while (true) {
            try {
                mostrarMenu();

            } catch (InputMismatchException e) {
                System.out.println("Entrada u/o formato incorrecto");
                entrada.nextLine(); // Consumir el carácter de nueva línea

            }
        }
    }

    public void mostrarMenu() throws InputMismatchException {
        System.out.print(menu);
        int opcion = entrada.nextInt();
        entrada.nextLine(); // Consumir el carácter de nueva línea

        switch (opcion) {
            case 1:
                buscarLibroPorTitulo();
                break;
            case 2:
                mostrarLibrosRegistrados();
                break;
            case 3:
                mostrarAutoresRegistrados();
                break;
            case 4:
                mostrarAutoresVivosEnDeterminadoAno();
                break;
            case 5:
                mostrarLibrosPorIdioma();
                break;
            case 6:
                System.out.println("Gracias por usar BookFinder");
                System.exit(0);
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    // Métodos de búsqueda por medio de la base de datos

    /**
     * Método que muestra los libros registrados en la base de datos
     *
     * @throws InputMismatchException : Excepción que se lanza cuando se ingresa un tipo de dato no válido
     */
    public void mostrarLibrosRegistrados() throws InputMismatchException {

        List<Libro> libros = libroRepository.findAll();

        if (!libros.isEmpty()) {
            libros.forEach(System.out::println);
        } else {
            System.out.println("No hay libros registrados");
        }
    }

    /**
     * Método que muestra los autores registrados en la base de datos
     *
     * @throws InputMismatchException : Excepción que se lanza cuando se ingresa un tipo de dato no válido
     */
    public void mostrarAutoresRegistrados() throws InputMismatchException {

        List<Autor> autores = autorRepository.findAll();

        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("No hay autores registrados");
        }
    }

    /**
     * Método que muestra los autores vivos en un determinado año
     *
     * @throws InputMismatchException : Excepción que se lanza cuando se ingresa un tipo de dato no válido
     */
    public void mostrarAutoresVivosEnDeterminadoAno() throws InputMismatchException {
        System.out.print("Ingresa el año menor a buscar: ");
        int anoMenor = entrada.nextInt();
        entrada.nextLine(); // Consumir el carácter de nueva línea
        // Aquí deberías realizar alguna acción con el número

        System.out.print("Ingresa el año mayor a buscar: ");
        int anoMayor = entrada.nextInt();
        entrada.nextLine();

        List<Autor> autores = autorRepository.buscarAutoresEntreDosAnos(anoMenor, anoMayor);

        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("No hay autores registrados en ese rango de años");
        }
    }


    /**
     * Método que busca libros por idioma
     *
     * @throws InputMismatchException : Excepción que se lanza cuando se ingresa un tipo de dato no válido
     */
    public void mostrarLibrosPorIdioma() throws InputMismatchException {
        System.out.print("Ingresa el idioma a buscar: ");
        String idiomaBuscar = entrada.nextLine();

        try {
            Idiomas idioma = Idiomas.fromNombreCompleto(idiomaBuscar);
            List<Libro> libros = libroRepository.findByIdioma(idioma);

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en ese idioma.");
            } else {
                System.out.println("Libros encontrados en " + idioma.getNombreCompleto() + ":");
                for (Libro libro : libros) {
                    System.out.println(libro.toString());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }



    // Métodos de búsqueda por medio de APIs

    /**
     * Método que busca un libro por su título
     *
     * @throws InputMismatchException : Excepción que se lanza cuando se ingresa un tipo de dato no válido
     */
    public void buscarLibroPorTitulo() throws InputMismatchException {
        System.out.print("Escribe el nombre del libro que deseas buscar: ");
        String titulo = entrada.nextLine();

        // Construir la URL con el parámetro de búsqueda
        String urlConsulta = URL_BASE + "?search=" + titulo.replace(" ", "%20");

        try {
            String respuesta = consultaApi.obtenerDatos(urlConsulta);
            ApiResponse apiResponse = conversor.obtenerDatos(respuesta, ApiResponse.class);

            List<DatosLibro> libros = apiResponse.results();
            if (!libros.isEmpty()) {
                DatosLibro datosLibro = libros.get(0);

                Optional<Autor> autorOpt = autorRepository.findByNombre(datosLibro.autor()[0].nombre());

                Autor autor;
                if (autorOpt.isPresent()) {
                    autor = autorOpt.get();
                } else {
                    autor = new Autor(datosLibro.autor()[0]);
                    autorRepository.save(autor);
                }

                Libro libro = new Libro(datosLibro, autor);
                libroRepository.save(libro);

                System.out.println(libro);

            } else {
                System.out.println("No se encontraron libros con ese título.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
