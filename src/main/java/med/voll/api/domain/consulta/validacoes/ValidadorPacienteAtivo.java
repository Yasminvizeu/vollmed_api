package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamento;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentDeConsultas{

    @Autowired private PacienteRepository repository;

    public void validar(DadosAgendamento dados){

        var pacienteEstaAtivo = repository.findAllById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta nao pode ser agendada com o paciente poruqe o medico esta inativo");
        }
    }
}
