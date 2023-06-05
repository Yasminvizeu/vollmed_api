package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("medicos")
public class MedicoControler {

    @Autowired
    private MedicoRepository repository;


    @PostMapping // usa classe dentro de clase pra pegar partes especificas do JSON
    @Transactional //metodo de escrita precisa ter transação ativa
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder){ // requestBody: dizendo que o request ta vindo no corpo da requisição
        //COMUNICAR QUE VAI DAR ERRO SE OS DADOS FOREM INSERIDOS EM LETRA MINUSCULA
        //DTO -data tranfer object, usado para transmitir os dados que chegam da api, nesse caso foi usado o record DadosCadastroMedicos
        //Records são usados para representar uma classe imutável, contendo apenas atributos, construtor e métodos de leitura, de uma maneira muito simples e enxuta.
        //pois apenas representar dados que serão recebidos ou devolvidos pela API, sem nenhum tipo de comportamento.
        var medico = new Medico(dados);
        repository.save(medico);//usado o metodo 201 com DTO para devolver dentro das boas praticas

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();//alem de devolver o codigo 201, o cadastrar tambem precisa o cabeçalho com a URI e no corpo da responsa o recurso recem criado(detalhamento)

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));//DTO de detalhamento de dados medicos como parametro
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=10, sort = {"nome"})Pageable pag){ // page devolve a lista e a paginação
        var page =  repository.findAllByAtivoTrue(pag).map(DadosListagemMedico::new);//stream().map() é usado pra converter de Medico para DadoListagemMedico
        return ResponseEntity.ok(page); //devolvendo OK
    }// usa o size e o page na URL pra limitar as paginacao ex:http://localhost:8080/medicos?size=1&page=0
    // da pra usar o parametro sort tbm ex: http://localhost:8080/medicos?sort=crm,desc&size=2&page=1

    @PutMapping //trabalha com atualizacoes
    @Transactional // metodo faz escrita então precisa de transaction
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok( new DadosDetalhamentoMedico(medico));
    }


    @DeleteMapping("/{id}")  //similar a requisicao de atualizacao
    @Transactional //pois precisa fazer uma escrita
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build(); // incluindo um conteudo à resposta, devolvendo o codigo 204 e não  o 200
    }
    //nova coluna no banco de dados
}
