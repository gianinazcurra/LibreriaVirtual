package com.Libreria.demo.servicios;

import ErrorServicio.ErrorServicio;
import com.Libreria.demo.entidades.Autor;
import com.Libreria.demo.entidades.Editorial;
import com.Libreria.demo.entidades.Libro;
import com.Libreria.demo.repositorio.AutorRepositorio;
import com.Libreria.demo.repositorio.EditorialRepositorio;
import com.Libreria.demo.repositorio.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    //GUARDAR UN LIBRO:creación
    @Transactional(propagation = Propagation.NESTED)
    public Libro guardar(long ISBN, String titulo, Integer anio, Autor autor, Editorial editorial) throws ErrorServicio {

        //VALIDACIONES   
        validar(titulo, ISBN, anio, autor, editorial);
        Libro libro = new Libro();

        //SETEO DE ATRIBUTOS    
        libro.setISBN(ISBN);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAlta(true);

        //PERSISTENCIA DEL OBJETO
        return libroRepositorio.save(libro);

    }

    //MODIFICAR DATOS
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String titulo, long ISBN, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta,Autor autor,Editorial editorial) throws ErrorServicio {
        validar(titulo, ISBN, anio, autor, editorial);
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setISBN(ISBN);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(true);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("No se pueden modificar los datos");
        }

    }

    //DAR DE BAJA
    public void darDeBaja(String id) throws ErrorServicio {
         
        Optional<Libro> optional = libroRepositorio.findById(id);
        if (optional.isPresent()) {

            Libro libro = optional.get();
            libro.setAlta(false);
        } else {
            throw new ErrorServicio("No se pueden modificar los datos");
        }
    }

    //CONSULTA POR ID
    @Transactional(readOnly = true)
    public void buscarPorId(String id) {
        Optional<Libro> optional = libroRepositorio.findById(id);

        if (optional.isPresent()) {
            libroRepositorio.findById(id);
        }
    }

    //CONSULTA POR TÍTULO
//    @Transactional(readOnly = true)
//    public Libro buscarPorTitulo(String titulo) throws ErrorServicio {
//         if (titulo == null || titulo.trim().isEmpty()) {
//            throw new ErrorServicio("Debe indicar el título del libro");
//        }else  {
//        public List<Libro> = libroRepositorio.buscarPorTitulo(titulo);
//        return libro;
//       }  
//    }

    //CONSULTA POR ISBN 
    @Transactional(readOnly = true)
    public Libro buscarPorISBN(Long ISBN) throws ErrorServicio {
        
        if (ISBN < 0) {
            throw new ErrorServicio("Debe indicar el ISBN correctamente");
        }else{
        Libro libro = libroRepositorio.buscarPorISBN(ISBN);
        return libro;
    }
        
        
}

 public List<Libro> listarLibros() {
        List<Libro> listaLibros = libroRepositorio.findAll();
        return listaLibros;
  }      
    
    //VALIDAR
    public void validar(String titulo, long ISBN, Integer anio, Autor autor, Editorial editorial) throws ErrorServicio {

        if (ISBN < 0) {
            throw new ErrorServicio("Debe indicar el ISBN correctamente");
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ErrorServicio("Debe indicar el título del libro");
        }

        if (anio == null) {
            throw new ErrorServicio("Debe indicar el año de emisión");
        }

        if (autor == null) {
            throw new ErrorServicio("Debe indicar el nombre del autor");
        }
        if (editorial == null) {
            throw new ErrorServicio("Debe indicar el nombre de la editorial");
        }
    }
}
