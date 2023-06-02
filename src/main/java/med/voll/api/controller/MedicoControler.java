package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort = {"nome"})Pageable pag){ // page devolve a lista e a paginação
        return repository.findAll(pag).map(DadosListagemMedico::new);//stream().map() é usado pra converter de Medico para DadoListagemMedico

    }// usa o size e o page na URL pra limitar as paginacao ex:http://localhost:8080/medicos?size=1&page=0
    // da pra usar o parametro sort tbm ex: http://localhost:8080/medicos?sort=crm,desc&size=2&page=1
    @PutMapping //trabalha com atualizacoes
    @Transactional // metodo faz escrita então precisa de transaction
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
    @DeleteMapping("/{id}")  //similar a requisicao de atualizacao
    @Transactional //pois precisa fazer uma escrita
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);

    }
}
