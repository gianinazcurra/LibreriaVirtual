
package com.Libreria.demo.repositorio;

import com.Libreria.demo.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EditorialRepositorio extends JpaRepository<Editorial,String>{
    
    @Query("SELECT a FROM Editorial a WHERE a.nombre=:nombre" )
    public Editorial buscarPorNombre(@Param("nombre")String nombre);
    
//    @Query("SELECT a FROM Editorial a WHERE a.id=:id" )
//    public Editorial buscarPorId(@Param("id")String id);
//    
    
}
