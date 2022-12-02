package com.example.thymeleafspring.controller;

import com.example.thymeleafspring.model.Autor;
import com.example.thymeleafspring.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping("/formulario/add")
    public ModelAndView getFormularioAdd(){
        return new ModelAndView("autor-formulario");
    }

    @PostMapping("/formulario/save")
    public String salvarLivro(@Valid Autor autor, BindingResult resultado, RedirectAttributes redirect){
        if(resultado.hasErrors()){
            // redirect.addFlashAttribute -> injeta o objeto na pagina redirecionamento
            redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatorios");
            return "redirect:/";
        }

        autorService.save(autor);
        return "redirect:/lista";
    }
}
