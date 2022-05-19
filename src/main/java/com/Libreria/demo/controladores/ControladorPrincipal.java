
package com.Libreria.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
    
    @Controller
@RequestMapping("")
public class ControladorPrincipal {

//    @GetMapping("") // solo lo muestro
//    public String index() {
//        return "index";
//    }    
     
    @GetMapping("")
    public String index(ModelMap modelo) { // lo muestro y le inyecto información dinámica con thymeleaf: "♥PAGINA COMISION 9♥"
        modelo.put("nombreControlador", "PROYECTO LIBRERIA");
        return "index";
    }
    
    @GetMapping("/administracionAutores")
    public String administracionAutores(){
        return "administracionAutores.html";
    }
    
    @GetMapping("/administracionEditorial")
    public String administracionEditorial(){
        return "administracionEditorial.html";
    }
    
    @GetMapping("/administracionLibro")
    public String administracionLibro(){
        return "administracionLibro.html";
    }

}

   

