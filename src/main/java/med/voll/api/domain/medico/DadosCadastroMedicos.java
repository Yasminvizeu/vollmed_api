package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedicos(
        @NotNull
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String sexo,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //expressao regular
        String crm,
        @NotNull
        Especialidade especialidade, //enum

        @NotNull
        @Valid
        DadosEndereco endereco

) {
    // criei um enum Especialidade para gravar as 4 especialidades
    //criei um record DadosEndereco  para gravar todos os dados do enreco

}
