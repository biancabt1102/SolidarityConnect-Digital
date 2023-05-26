package br.com.solidarityconnect.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.solidarityconnect.repository.UsuarioRepository;


@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmailUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

}
