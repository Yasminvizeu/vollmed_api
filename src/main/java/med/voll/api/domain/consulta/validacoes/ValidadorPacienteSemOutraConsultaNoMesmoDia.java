package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoMesmoDia implements ValidadorAgendamentDeConsultas{




    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamento dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if(pacientePossuiOutraConsultaNDia){
            throw new ValidacaoException("Paciente ja possui uma consulta marcada no mesmo dia");
        }
    }
}
