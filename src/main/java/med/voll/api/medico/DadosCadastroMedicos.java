package med.voll.api.medico;

public record DadosCadastroMedicos(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
    // criei um enum Especialidade para gravar as 4 especialidades
    //criei um record DadosEndereco  para gravar todos os dados do enreco

}
