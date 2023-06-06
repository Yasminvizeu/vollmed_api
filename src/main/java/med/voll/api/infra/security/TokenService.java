package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret; //criado no porperties api.security.token.secret={JWT_SECRET} por estar entre chaves o spring sabe que deve procurar uma variavel de ambiente no computador
    public String gerarToken(Usuario usuario){
        try {
            var algoritimo = Algorithm.HMAC256(secret); //PRECISA DE SENHA PRA USAR O algoritimo
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin())// forma de identificar os usuarios
                    .withClaim("id", usuario.getId()) // chave assossiada a um valor
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
                throw new RuntimeException("erro ao gerar o token",exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
