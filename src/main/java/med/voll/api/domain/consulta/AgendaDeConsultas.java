package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentDeConsultas;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // executa regras de negocio e validações
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentDeConsultas> validadores; //implementando a interface

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamento dados) {
        //regras de negocio vem antes de tudo //validacoes de integridade
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) { //garante que o id existe e devolve o erro
            throw new ValidacaoException("Id do medico informado não existe");
        }

        //chama a lista de validadores
        validadores.forEach(v -> v.validar(dados));

        //validacoes

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados); // findByI devolve um optional por isso por um .get()
        if (medico == null) {
            throw new ValidacaoException("Não existe medico disponivel nessa data");

        }
        var consulta = new Consulta(null, paciente, medico, dados.data(), null); // o medico é opcinal e se for nulo deve entrar um medico aleatorio como default
        //OBJETIVO É SALVAR OS DADOS DO AGENDAMENTO NO BANCO, PRA ISSO DEVE SE INJETAR A DEPENDENCIA DO REPOSITORY
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamento dados) {
        //escolhe medico aleatoriamente no banco de dados
        //verifica se tem id do medico
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById((dados.idMedico()));
        }
        //não foi escolhido o medico então precisa preencher a especialidade tbm, se nao , lanca exception
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatorio e não foi escolhida");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
