package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamento;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {


    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();
        //devolve true se a data ta vindo no domingo
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        //devolve true se a hora for antes da abertura da clinica
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        //devolve true se a hora ta vindo depois do fechamento da clinica
        var depoisDoEncrrramentoDaClinica = dataConsulta.getHour() < 18;

        //verificando se o horario é valido
        if(domingo || antesDaAberturaDaClinica || depoisDoEncrrramentoDaClinica){
            throw new ValidacaoException("Consulta fora do horaio de funcionamento da clinica");
        }

    }
}
