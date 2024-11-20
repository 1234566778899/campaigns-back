package backend.helpinghand.repositories;

import backend.helpinghand.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);
    public boolean existsByUserName(String username);
}
