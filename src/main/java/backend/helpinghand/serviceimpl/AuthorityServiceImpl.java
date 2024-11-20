package backend.helpinghand.serviceimpl;

import backend.helpinghand.entities.Authority;
import backend.helpinghand.exceptions.InvalidDataException;
import backend.helpinghand.exceptions.ResourceNotFoundException;
import backend.helpinghand.repositories.AuthorityRepository;
import backend.helpinghand.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority findByName(String name) {
        Authority authorityFound = authorityRepository.findByName(name);
        if (authorityFound==null){
            throw new ResourceNotFoundException("Authority with name: "+ name+ " can not be found");
        }
        return authorityFound;

    }

    @Override
    public Authority findById(Long id) {
        Authority authorityFound = authorityRepository.findById(id).orElse(null);
        if (authorityFound==null){
            throw new ResourceNotFoundException("Authority with id: "+ id+ " can not be found");
        }
        return authorityFound;
    }
    @Override
    public Authority addAuthority(Authority authority) {
        if (authority.getName()==null || authority.getName().isBlank()) {
            throw new InvalidDataException("Authority name can not be blank");
        }
        return authorityRepository.save(authority);
    }
}
