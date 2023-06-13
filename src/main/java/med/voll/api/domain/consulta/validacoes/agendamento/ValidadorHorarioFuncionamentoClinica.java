package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamento;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentDeConsultas {


    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();
        //devolve true se a data ta vindo no domingo
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        //devolve true se a hora for antes da abertura da clinica
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        //devolve true se a hora ta vindo depois do fechamento da clinica
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        //verificando se o horario é valido
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

    }
}
