package com.Libreria.demo.servicios;

import ErrorServicio.ErrorServicio;
import com.Libreria.demo.entidades.Autor;
import com.Libreria.demo.repositorio.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    //GUARDAR UN AUTOR:creación
    @Transactional(propagation = Propagation.NESTED)
    public Autor guardar(String nombre) throws ErrorServicio {

        //VALIDACIONES   
        validar(nombre);
        Autor autor = new Autor();

        //SETEO DE ATRIBUTOS    
        autor.setNombre(nombre);
        autor.setAlta(true);

        //PERSISTENCIA DEL OBJETO
        return autorRepositorio.save(autor);

    }

    //MODIFICAR DATOS
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre, boolean alta) throws ErrorServicio {
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
//            autor.setAlta(true); 
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se pueden modificar los datos");
        }

    }

    //ELIMINAR UN AUTOR
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional<Autor> optional = autorRepositorio.findById(id);

        if (optional.isPresent()) {
            autorRepositorio.delete(optional.get());
        }
    }

    //DAR DE BAJA
    public void darDeBaja(String id) throws ErrorServicio {
        Optional<Autor> optional = autorRepositorio.findById(id);
        if (optional.isPresent()) {

            Autor autor = optional.get();
            autor.setAlta(false);
        } else {
            throw new ErrorServicio("No se pueden modificar los datos");
        }
    }

    //CONSULTA
    @Transactional(readOnly = true)
    public void buscarPorId(String id) {
        Optional<Autor> optional = autorRepositorio.findById(id);

        if (optional.isPresent()) {
            autorRepositorio.findById(id);
        }
    }
    //CONSULTA POR NOMBRE
    @Transactional(readOnly = true)
    public Autor buscarPorNombre(String nombre) throws ErrorServicio {
        validar(nombre);
        Autor autor = autorRepositorio.buscarPorNombre(nombre);
        return autor;
    }
    //TRAER LISTA POR NOMBRE
    @Transactional(readOnly = true)
    public List<Autor> listaAutores() {
        List<Autor> listaAutores = autorRepositorio.findAll();
        return listaAutores;
    }
    
    
    //VALIDAR
    public void validar(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("EL NOMBRE NO PUEDE SER NULO");
        }

        //Si no seteas el booleano al inicio como true, entonces podés validarlo
//        if (alta == null || alta.toString().isEmpty()) {
//            throw new ErrorServicio("NO PUEDE SER NULO ESTE VALOR");
//        }
    }

}
