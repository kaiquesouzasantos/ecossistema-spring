package modules.security;

import modules.user.UserRepository;
import modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existsUser = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new Error("USUARIO INEXISTENTE"));

        return new org.springframework.security.core.userdetails.User(
                existsUser.getUsername(), existsUser.getPassword(),
                true, true, true, true,
                existsUser.getRoles().stream().map(role -> {
                    return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
                }).collect(Collectors.toList())
            );
    }
}
