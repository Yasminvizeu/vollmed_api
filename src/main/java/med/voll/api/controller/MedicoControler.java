package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("medicos")
public class MedicoControler {

    @PostMapping
    public void cadastrar(@RequestBody String cep ){ // dizendo que o request ta vindo no corpo da requisição
        System.out.println(cep);
    }



}
