package med.voll.api.domain.medico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest//spring carrega tudo
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//USANDO BANCOS DE DADOS REAL, o mesmo da aplicacao,o ideal
    //seria usar um novo dtabase para os testes

@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Test
    void escolherMedicoAleatorioLivreNaData() {
    }
}