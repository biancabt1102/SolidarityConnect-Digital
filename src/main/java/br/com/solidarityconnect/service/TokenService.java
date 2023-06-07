package br.com.solidarityconnect.service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.solidarityconnect.models.Credencial;
import br.com.solidarityconnect.models.Token;
import br.com.solidarityconnect.models.Usuario;
import br.com.solidarityconnect.repository.UsuarioRepository;




@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Token generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256("meusecret");
        var jwt = JWT.create()
                    .withSubject(credencial.email())
                    .withIssuer("SolidarityConnect")
                    .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
                    .sign(alg)
                    ;
        return new Token(jwt, "JWT", "Bearer");
    }

    public Usuario validate(String token) {
        Algorithm alg = Algorithm.HMAC256("meusecret");
        var email = JWT.require(alg)
                .withIssuer("SolidarityConnect")
                .build()
                .verify(token)
                .getSubject();

        return usuarioRepository.findByEmailUsuario(email).orElseThrow(
                () -> new JWTVerificationException("usuario nao encontrado"));

    }


}
