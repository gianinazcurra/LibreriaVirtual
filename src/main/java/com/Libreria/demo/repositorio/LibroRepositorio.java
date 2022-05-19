
package com.Libreria.demo.repositorio;

import com.Libreria.demo.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LibroRepositorio extends JpaRepository<Libro,String>{

@Query("SELECT c FROM Libro c WHERE c.titulo=:titulo" )
    public List <Libro> buscarPorTitulo(@Param("titulo")String titulo);

 
@Query("SELECT c FROM Libro c WHERE c.ISBN=:ISBN" )
    public Libro buscarPorISBN(@Param("ISBN")Long ISBN);

@Query("SELECT c FROM Libro c WHERE c.id=:id" )
    public Libro buscarPorId(@Param("id")String id);   
    
//@Query("SELECT c FROM Libro c WHERE c.nombre LIKE %nombre%" )
//    public Libro buscarPor(@Param("id")String id);   
//    
}

//select a fromlibro a where a.autor.nombre like nombre

