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
        @Future // pra dizer que a data é no futuro, nao pode agendar consulta pra ontem
        LocalDateTime data,

        Especialidade especialidade){
}
