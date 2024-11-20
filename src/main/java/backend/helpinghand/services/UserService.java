package backend.helpinghand.services;

import backend.helpinghand.dtos.DTOUser;
import backend.helpinghand.entities.User;

import java.util.List;

public interface UserService {

    public User addUser(DTOUser dtoUser);
    public User findByUsername(String username);
    public User findById(Long id);
}
