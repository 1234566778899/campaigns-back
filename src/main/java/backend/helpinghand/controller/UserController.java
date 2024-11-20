package backend.helpinghand.controller;


import backend.helpinghand.dtos.DTOToken;
import backend.helpinghand.dtos.DTOUser;
import backend.helpinghand.entities.Authority;
import backend.helpinghand.entities.User;
import backend.helpinghand.security.JwtUtilService;
import backend.helpinghand.security.SecurityUser;
import backend.helpinghand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/helpinghands")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtilService jwtUtilService;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody DTOUser dtoUser){

        User newUser = userService.addUser(dtoUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<DTOToken> login(@RequestBody DTOUser dtoUser){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoUser.getUserName(),dtoUser.getPassword()));

        DTOToken dtoToken=new DTOToken();

        User userFound = userService.findByUsername(dtoUser.getUserName());

        dtoToken.setUserId(userFound.getId());
        dtoToken.setAuthorities( userFound.getAuthorities().stream().map(Authority::getName).collect(Collectors.joining(";","","")) );
        dtoToken.setJwtToken( jwtUtilService.generateToken(new SecurityUser(userFound)) );

        return new ResponseEntity<>(dtoToken, HttpStatus.OK);

    }
}
