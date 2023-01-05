package modules.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import modules.user.UserRepository;
import modules.user.entities.User;

@Service
public class CreateUserService {
  @Autowired UserRepository userRepository;

  public User execute(User user) {
      var existe = userRepository.findByUsername(user.getUsername());
      if(existe.isPresent())
          throw new Error("USUARIO JA EXISTENTE");

      user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
      userRepository.save(user);

      return userRepository.save(user);
  }
}
