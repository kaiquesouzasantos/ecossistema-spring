package com.example.thymeleafspring.controller;

import com.example.thymeleafspring.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired private LivroService livroService;

    @GetMapping("/")
    public String getRedirectLista(){
        return "redirect:/lista";
    }

    @GetMapping("/lista")
    public ModelAndView getLista(){
        ModelAndView view = new ModelAndView("index");
        view.addObject("livroLista", livroService.getLivroLista());
        return view;
    }
}
