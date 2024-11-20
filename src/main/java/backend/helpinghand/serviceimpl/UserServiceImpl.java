package backend.helpinghand.serviceimpl;

import backend.helpinghand.dtos.DTOUser;
import backend.helpinghand.entities.Authority;
import backend.helpinghand.entities.User;
import backend.helpinghand.exceptions.InvalidDataException;
import backend.helpinghand.exceptions.ResourceNotFoundException;
import backend.helpinghand.repositories.UserRepository;
import backend.helpinghand.security.SecurityUser;
import backend.helpinghand.services.AuthorityService;
import backend.helpinghand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityService authorityService;

    @Override
    public User addUser(DTOUser dtoUser) {
        User newUser = new User();

        if (userRepository.existsByUserName(dtoUser.getUserName())) {
            throw new InvalidDataException("El nombre de usuario ya está en uso.");
        }
        newUser.setUserName(dtoUser.getUserName());
        String password = dtoUser.getPassword();

        if(password.length()<8){
            throw new InvalidDataException("La contraseña debe tener al menos 8 caracteres.");
        }


        newUser.setPassword( new BCryptPasswordEncoder().encode(password));

        newUser.setEnabled(true);

        newUser.setAuthorities(authorityListFromString(dtoUser.getAuthorities()));

        return userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        User userFound = userRepository.findByUserName(username);
        if (userFound==null){
            throw new ResourceNotFoundException("User with username: "+ username+ " can not be found");
        }
        return userFound;
    }

    @Override
    public User findById(Long id) {
        User userFound = userRepository.findById(id).orElse(null);
        if (userFound == null) {
            throw new ResourceNotFoundException("User with id: " + id + " can not be found");
        }
        return userFound;
    }




    public List<Authority> authorityListFromString(String authorityString) {

        List<String> authorityListString =  Arrays.stream(authorityString.split(";")).toList();
        List<Authority> authorityList = new ArrayList<>();
        for (String authorityStringItem: authorityListString){
            Authority authority = authorityService.findByName(authorityStringItem);
            authorityList.add(authority);
        }
        return  authorityList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecurityUser(findByUsername(username));
    }

}
