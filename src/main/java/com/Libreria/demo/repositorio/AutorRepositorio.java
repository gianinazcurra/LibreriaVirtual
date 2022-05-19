
package com.Libreria.demo.repositorio;

import com.Libreria.demo.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AutorRepositorio extends JpaRepository<Autor,String>{
    
    
    @Query("SELECT c FROM Autor c WHERE c.nombre=:nombre" )
    public Autor buscarPorNombre(@Param("nombre")String nombre);
    
//    @Query("SELECT c FROM Autor c WHERE c.id=:id" )
//    public Autor buscarPorId(@Param("nombre")String id);
    
    
}
