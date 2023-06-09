package med.voll.api.domain.consulta;

import java.time.LocalDateTime;


//dto de resposta
public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
