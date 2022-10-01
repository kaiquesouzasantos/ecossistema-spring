package br.com.book.controller;

import br.com.book.entity.Author;
import br.com.book.entity.Book;
import br.com.book.service.AuthorService;
import br.com.book.service.BookService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping("/book/form/add")
    public ModelAndView getFormAdd() {

        ModelAndView mv = new ModelAndView("bookform");
        List<Author> authorList = this.authorService.getAuthorList();
        mv.addObject("authorList", authorList);
        return mv;
    }

    @PostMapping("/book/form/save")
    public String saveBook(@Valid Book book, BindingResult result,
                           RedirectAttributes redirect) {

        if (result.hasErrors()) {
            redirect.addFlashAttribute("mensagem",
                    "Verifique os campos obrigatórios");
            return "redirect:/book/form/add";
        }

        this.bookService.save(book);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEdit(@PathVariable("id") Long id){

        ModelAndView mv = new ModelAndView("bookform");
        List<Author> authorList = this.authorService.getAuthorList();
        mv.addObject("authorList", authorList);

        Book book = this.bookService.findById(id);

        mv.addObject("book", book);

        return mv;

    }


}
