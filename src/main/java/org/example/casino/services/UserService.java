package org.example.casino.services;

import org.apache.coyote.BadRequestException;
import org.example.casino.dto.SignUpRequest;
import org.example.casino.models.User;
import org.example.casino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(SignUpRequest signUpRequest) throws BadRequestException {
        var userInDbObject = userRepository.findByUsernameAndEmail(signUpRequest.getUsername(), signUpRequest.getEmail());

        if(userInDbObject != null) {
            User userInDb = (User) userInDbObject;

            if(userInDb.getUsername().equalsIgnoreCase(signUpRequest.getUsername())) {
                throw new BadRequestException("User with the same username already exists");
            } else {
                throw new BadRequestException("User with the same email already exists");
            }
        } else {
            User newUser = new User(
                    signUpRequest.getFirstname(),
                    signUpRequest.getLastname(),
                    signUpRequest.getEmail(),
                    signUpRequest.getPassword(),
                    signUpRequest.getUsername(),
                    signUpRequest.getAge()
            );
            userRepository.save(newUser);
        }
    }
}
