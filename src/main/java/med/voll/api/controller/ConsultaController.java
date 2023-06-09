package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private AgendaDeConsultas agenda;


    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados){
        //validaçoes e regra devem ser isoladas(classe de servico) não devem estra no controle, só controla o fluxo de execução
        //O controler faz a validação do Bean apenas
        agenda.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null,  null));
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        //validaçoes e regra devem ser isoladas(classe de servico) não devem estra no controle, só controla o fluxo de execução
        //O controler faz a validação do Bean apenas
        agenda.cancelar(dados);
        return ResponseEntity.ok(new DadosCancelamentoConsulta(null, null));
    }
}
