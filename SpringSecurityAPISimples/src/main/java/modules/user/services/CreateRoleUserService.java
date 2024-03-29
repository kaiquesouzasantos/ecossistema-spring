package modules.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modules.user.UserRepository;
import modules.user.dto.CreateUserRoleDTO;
import modules.user.entities.Role;
import modules.user.entities.User;

@Service
public class CreateRoleUserService {
    @Autowired UserRepository userRepository;

    public User execute(CreateUserRoleDTO createUserRoleDTO) {
        Optional<User> userExists = userRepository.findById(createUserRoleDTO.getIdUser());
        List<Role> roles = new ArrayList<>();

        if (userExists.isEmpty()) {
            throw new Error("User does not exists!");
        }

        roles = createUserRoleDTO.getIdsRoles().stream().map(role -> {
            return new Role(role);
        }).collect(Collectors.toList());

        User user = userExists.get();
        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }
}
