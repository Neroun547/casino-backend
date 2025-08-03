package org.example.casino.services;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.casino.dto.SignUpRequest;
import org.example.casino.models.User;
import org.example.casino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(SignUpRequest signUpRequest) throws BadRequestException {
        var userInDbObject = userRepository.findByUsernameOrEmail(signUpRequest.getUsername(), signUpRequest.getEmail());

        if(userInDbObject != null) {
            User userInDb = (User) userInDbObject;

            if(userInDb.getUsername().equalsIgnoreCase(signUpRequest.getUsername())) {
                throw new BadRequestException("User with the same username already exists");
            } else {
                throw new BadRequestException("User with the same email already exists");
            }
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordHash = passwordEncoder.encode(signUpRequest.getPassword());

            User newUser = new User(
                    signUpRequest.getFirstname(),
                    signUpRequest.getLastname(),
                    signUpRequest.getEmail(),
                    passwordHash,
                    signUpRequest.getUsername(),
                    signUpRequest.getAge()
            );
            userRepository.save(newUser);
        }
    }

    User getUserByUsernameOrEmail(String usernameOrEmail) {
        var userInDb = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (userInDb == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return (User) userInDb;
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByUsernameOrEmail;
    }
}
