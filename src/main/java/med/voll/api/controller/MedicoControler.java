package med.voll.api.controller;

import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("medicos")
public class MedicoControler {

    @Autowired
    private MedicoRepository repository;

    @PostMapping // usa classe dentro de clase pra pegar partes especificas do JSON
    public void cadastrar(@RequestBody DadosCadastroMedicos dados){ // requestBody: dizendo que o request ta vindo no corpo da requisição
        //COMUNICAR QUE VAI DAR ERRO SE OS DADOS FOREM INSERIDOS EM LETRA MINUSCULA
        //DTO -data tranfer object, usado para transmitir os dados que chegam da api, nesse caso foi usado o record DadosCadastroMedicos
        //Records são usados para representar uma classe imutável, contendo apenas atributos, construtor e métodos de leitura, de uma maneira muito simples e enxuta.
        //pois apenas representar dados que serão recebidos ou devolvidos pela API, sem nenhum tipo de comportamento.
        repository.save(new Medico(dados));
    }

}
