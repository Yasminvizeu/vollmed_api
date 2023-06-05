package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull(message = "Please enter id") Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}