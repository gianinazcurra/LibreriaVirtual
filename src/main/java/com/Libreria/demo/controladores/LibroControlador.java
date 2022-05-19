
package com.Libreria.demo.controladores;

import com.Libreria.demo.entidades.Autor;
import com.Libreria.demo.entidades.Editorial;
import com.Libreria.demo.entidades.Libro;
import com.Libreria.demo.servicios.AutorServicio;
import com.Libreria.demo.servicios.EditorialServicio;
import com.Libreria.demo.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/administracionLibro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

  @GetMapping("/guardarLibro")
    public String guardarLibro(ModelMap modelo) {
      List<Autor> autores = autorServicio.listaAutores();
        modelo.addAttribute("autor", autores);
        List<Editorial> editoriales = editorialServicio.listaEditoriales();
        modelo.addAttribute("editorial", editoriales);
        return "administracionLibro";
    }

    @PostMapping("/guardarLibro")
    public String guardarLibro(ModelMap modelo,@RequestParam Long isbn, @RequestParam String titulo,
            @RequestParam Integer anio, @RequestParam(required = false) Autor autor, @RequestParam(required = false) Editorial editorial) throws Exception {
        try {
            libroServicio.guardar(isbn,titulo, anio, autor, editorial);
            modelo.put("exito", "Autor ingresado con éxito!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelo.put("error", "No se ha podido guardar el autor");
            System.out.println("HAY UN ERROR");
        }
        return "administracionLibro";
    }

    @GetMapping("/mostrarLibros")
    public String mostrarLibros(ModelMap modelo) {
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libro", libros);
        return "administracionLibro";
    }

}


