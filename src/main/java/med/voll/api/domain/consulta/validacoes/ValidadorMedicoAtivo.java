package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamento;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentDeConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamento dados){
        //escolha do medico é opcional
        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAllById(dados.idMedico());
        if(!medicoEstaAtivo){
                throw new ValidacaoException("Consulta nao pode ser agendad com o medio poruqe o medico esta inativo");
        }
    }


}
