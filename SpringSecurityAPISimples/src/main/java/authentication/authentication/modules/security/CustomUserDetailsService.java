package authentication.authentication.modules.security;

import authentication.authentication.modules.user.UserRepository;
import authentication.authentication.modules.user.entities.User;
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
        User existsUser = userRepository.findByUsernameFetchRoles(username);

        if (existsUser == null) {
            throw new Error("User does not exists!");
        } else {
            return new org.springframework.security.core.userdetails.User(
                    existsUser.getUsername(), existsUser.getPassword(),
                    true, true, true, true,
                    existsUser.getRoles().stream().map(role -> {
                        return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
                    }).collect(Collectors.toList())
            );
        }
    }
}
