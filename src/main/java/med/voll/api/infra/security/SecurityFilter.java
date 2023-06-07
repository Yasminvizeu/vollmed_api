package med.voll.api.infra.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //classes genericas
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var tokenJWT = recuperarToken(request);

        System.out.println(tokenJWT); //teste
    //SEGUE O FLUXO DA REQUISIÇÃO
        filterChain.doFilter(request,response);
    }//dar um system.out pra garantir que filro é executado apenas uma unica vez por request

    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null){
            throw new RuntimeException("Token JWT nao enviado no cabeçalho authorization ");
        }
        return authorizationHeader.replace("Bearer", "");
    }
}
