package org.example.casino.services;

import org.apache.coyote.BadRequestException;
import org.example.casino.models.User;
import org.example.casino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String auth(String emailOrUsername, String password) throws BadRequestException {
        var userInDbObject = userRepository.findByUsernameOrEmail(emailOrUsername, emailOrUsername);

        if (userInDbObject == null) {
            throw new BadRequestException("User not found");
        } else {
            User userInDb = (User) userInDbObject;
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInDb.getUsername(), password));

            return jwtService.generateJwtToken(
                    userInDb.getId(),
                    userInDb.getUsername(),
                    userInDb.getEmail()
            );
        }
    }
}
