package med.voll.api.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicos(
        @NotNull(message = "Please enter id") //apenas o id é obrigatorio pra identificar quem vai ser atualizado, o resto é opcional
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {


}
