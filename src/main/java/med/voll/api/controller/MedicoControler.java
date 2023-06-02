package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("medicos")
public class MedicoControler {

    @Autowired
    private MedicoRepository repository;


    @PostMapping // usa classe dentro de clase pra pegar partes especificas do JSON
    @Transactional //metodo de escrita precisa ter transação ativa
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados){ // requestBody: dizendo que o request ta vindo no corpo da requisição
        //COMUNICAR QUE VAI DAR ERRO SE OS DADOS FOREM INSERIDOS EM LETRA MINUSCULA
        //DTO -data tranfer object, usado para transmitir os dados que chegam da api, nesse caso foi usado o record DadosCadastroMedicos
        //Records são usados para representar uma classe imutável, contendo apenas atributos, construtor e métodos de leitura, de uma maneira muito simples e enxuta.
        //pois apenas representar dados que serão recebidos ou devolvidos pela API, sem nenhum tipo de comportamento.
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar(){
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();//stream().map() é usado pra converter de Medico para DadoListagemMedico

    }

}
