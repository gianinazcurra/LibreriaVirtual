
package com.Libreria.demo.controladores;

import com.Libreria.demo.entidades.Autor;
import com.Libreria.demo.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/administracionAutores")   
public class AutorControlador {
 
    @Autowired
    AutorServicio autorServicio;

    @GetMapping("/guardar")
    public String guardarAutor() {
        return "administracionAutores"; //me devuelve la vista
    }

    @PostMapping("/guardar")
    public String guardarAutor(ModelMap modelo, @RequestParam String nombre) throws Exception {
        try {
            autorServicio.guardar(nombre);
            System.out.println("Autor " + nombre);
            modelo.put("Bien hecho", "Autor ingresado de manera correcta");
        } catch (Exception e) {
            e.getMessage();
          modelo.put("Ha ocurrido un error", "No se ha podido guardar el autor");
        }
        return "administracionAutores";
    }

    @GetMapping("/mostrar") 
    public String mostrarAutores(ModelMap modelo) {
        List<Autor> autores = autorServicio.listaAutores();
        modelo.addAttribute("autor", autores);
        return "administracionAutores"; 
    }

}


