package com.bookfinder.bookfinder;

import com.bookfinder.bookfinder.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bookfinder.bookfinder.principal.Principal;
import com.bookfinder.bookfinder.repository.LibroRepository;

@SpringBootApplication
public class BookFinderApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;


    public static void main(String[] args) {
        SpringApplication.run(BookFinderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(libroRepository, autorRepository);
        principal.Menu();
    }
}
