package com.Libreria.demo.servicios;

import ErrorServicio.ErrorServicio;
import com.Libreria.demo.entidades.Editorial;
import com.Libreria.demo.repositorio.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    //GUARDAR UNA EDITORIAL:creación
    @Transactional(propagation = Propagation.NESTED)
    public Editorial guardar(String nombre) throws ErrorServicio {

        //VALIDACIONES   
        validar(nombre);

        Editorial editorial = new Editorial();

        //SETEO DE ATRIBUTOS    
        editorial.setNombre(nombre);
        editorial.setAlta(true);

        //PERSISTENCIA DEL OBJETO
        return editorialRepositorio.save(editorial);

    }

    //MODIFICAR DATOS
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre, boolean alta) throws ErrorServicio {
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("No se pueden modificar los datos");
        }

    }

    //CONSULTA POR NOMBRE
    @Transactional(readOnly = true)
    public Editorial buscarPorNombre(String nombre) throws ErrorServicio {
        validar(nombre);
        Editorial editorial = editorialRepositorio.buscarPorNombre(nombre);
        return editorial;
    }

    //TRAER LISTA POR NOMBRE
    @Transactional(readOnly = true)
    public List<Editorial> listaEditorial() {
        List<Editorial> listaEditorial = editorialRepositorio.findAll();
        return listaEditorial;
    }

    //CONSULTA
    @Transactional(readOnly = true)
    public void buscarPorId(String id) {
        Optional<Editorial> optional = editorialRepositorio.findById(id);
        if (optional.isPresent()) {
            editorialRepositorio.findById(id);
        }
    }

    //DAR DE BAJA
    public void darDeBaja(String id) throws ErrorServicio {
        Optional<Editorial> optional = editorialRepositorio.findById(id);
        if (optional.isPresent()) {
            Editorial editorial = optional.get();
            editorial.setAlta(false);
        } else {
            throw new ErrorServicio("No se pueden modificar los datos");
        }
    }

    //ELIMINAR UNA EDITORIAL:dar de baja
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional<Editorial> optional = editorialRepositorio.findById(id);

        if (optional.isPresent()) {
            editorialRepositorio.delete(optional.get());
        }
    }
    
    public List<Editorial> listaEditoriales() {
        List<Editorial> listaEditoriales = editorialRepositorio.findAll();
        return listaEditoriales;
  }      

    public void validar(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("EL NOMBRE NO PUEDE SER NULO");
        }

//        if (alta == null || alta.toString().isEmpty()) {
//            throw new ErrorServicio("NO PUEDE SER NULO ESTE VALOR");
//        }
    }
}
