package com.api.EcommerceProjeto.services;
import java.util.Optional;

import com.api.EcommerceProjeto.Security.UserSS;
import com.api.EcommerceProjeto.domain.Pessoa;
import com.api.EcommerceProjeto.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);
        if(user.isPresent()) {
            return new UserSS(
                    user.get().getId(),
                    user.get().getEmail(),
                    user.get().getPassword(),
                    user.get().getPerfil());
        }
        throw new UsernameNotFoundException(email);
    }

}
