package com.example.thymeleafspring.controller;

import com.example.thymeleafspring.model.Livro;
import com.example.thymeleafspring.service.AutorService;
import com.example.thymeleafspring.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livro")
public class LivroController {
    @Autowired private AutorService autorService;
    @Autowired private LivroService livroService;

    @GetMapping("/formulario/add")
    public ModelAndView getFormularioAdd(){
        ModelAndView view = new ModelAndView("livro-formulario");
        view.addObject("autorLista", autorService.getAutorLista());
        return view;
    }

    @PostMapping("/formulario/save")
    public String salvarLivro(@Valid Livro livro, BindingResult resultado, RedirectAttributes redirect){
        if(resultado.hasErrors()){
            redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatorios");
            return "redirect:/livro/formulario/add";
        }

        livroService.save(livro);
        return "redirect:/lista";
    }

    @GetMapping("/edita/{id}")
    public ModelAndView getEdita(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("livro-formulario");
        view.addObject("livro", livroService.getLivro(id));
        view.addObject("autorLista", autorService.getAutorLista());
        return view;
    }

    @GetMapping("/deleta/{id}")
    public String getDeleta(@PathVariable("id") Long id){
        livroService.delete(id);
        return "redirect:/lista";
    }
}
