package aula_01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // -> lista de caminhos possiveis
public class HomeController {

    @RequestMapping("/") // -> indica um caminho/endereco/endPoint
    public String homeApp(Model model){
        model.addAttribute("mensagem", "mensagem injetada pelo Model"); // (key, value)
        return "index";
    }
}
