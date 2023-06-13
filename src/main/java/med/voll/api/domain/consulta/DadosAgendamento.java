package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamento(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future // para dizer que a data é no futuro, não pode agendar consulta para ontem
        LocalDateTime data,

        Especialidade especialidade){

}
