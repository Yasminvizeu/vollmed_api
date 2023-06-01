package med.voll.api.controller;

import med.voll.api.medico.DadosCadastroMedicos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("medicos")
public class MedicoControler {

    @PostMapping // usa classe dentro de clase pra pegar partes especificas do JSON
    public void cadastrar(@RequestBody DadosCadastroMedicos dados){ // requestBody: dizendo que o request ta vindo no corpo da requisição
        System.out.println(dados);
        //COMUNICAR QUE VAI DAR ERRO SE OS DADOS FOREM INSERIDOS EM LETRA MINUSCULA
        //DTO -data tranfer object, usado para transmitir os dados que chegam da api, nesse caso fouiusado o record DadosCadastroMedicos
    }

}
