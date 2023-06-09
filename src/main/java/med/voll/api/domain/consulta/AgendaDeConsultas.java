package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // executa regras de negocio e validações
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamento dados) {
        //regras de negocio vem antes de tudo
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) { //garante que o id existe e devolve o erro
            throw new ValidacaoException("Id do medico informado não existe");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = medicoRepository.findById(dados.idMedico()).get(); // findByI devolve um optional por isso por um .get()
        var consulta = new Consulta(null, paciente, medico, dados.data());
        //OBJETIVO É SALVAR OS DADOS DO AGENDAMENTO NO BANCO, PRA ISSO DEVE SE INJETAR A DEPENDENCIA DO REPOSITORY
        consultaRepository.save(consulta);
    }
}
