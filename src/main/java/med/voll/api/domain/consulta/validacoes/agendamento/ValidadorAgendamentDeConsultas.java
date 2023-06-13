package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamento;

public interface ValidadorAgendamentDeConsultas {
    //solucionar polimorfimo do metodo validar  com o usao de interfaces

    //impicito que todos os metodos de uma interface sao publicos
    void validar(DadosAgendamento dados);

}
