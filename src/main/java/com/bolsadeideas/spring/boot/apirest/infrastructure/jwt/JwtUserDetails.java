package com.bolsadeideas.spring.boot.apirest.infrastructure.jwt;

import com.bolsadeideas.spring.boot.apirest.app.services.users.IUserService;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JwtUserDetails implements UserDetailsService {

    private final IUserService userService;

    @Autowired
    public JwtUserDetails(IUserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByEmail(email);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("The user not found");
        }

        User currentUser = optionalUser.orElseThrow();
        List<GrantedAuthority> authorities = currentUser.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(currentUser.getEmail(),
                currentUser.getPassword(),
                currentUser.isEnabled(),
                true,
                true,
                true,
                authorities);
    }
}
