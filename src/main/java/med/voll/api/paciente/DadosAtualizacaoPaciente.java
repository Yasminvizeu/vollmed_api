package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull(message = "Please enter id") Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}