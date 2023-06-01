package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //usada para api REST
@RequestMapping("/hello") // usado pra dizer qual URL esse controller vai responder
public class helloController {

    @GetMapping // requisisão /hello do tipo get
    public String olaMundo(){
        return "Hello World!";
    }
}
