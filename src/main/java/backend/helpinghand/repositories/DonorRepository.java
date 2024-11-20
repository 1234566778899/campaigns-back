package backend.helpinghand.repositories;

import backend.helpinghand.entities.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    public List<Donor> findByEmailContains(String email);

    public List<Donor> findByNameContains(String name);

    public List<Donor> findByAddressContains(String address);

    public List<Donor> findByPhoneContains(String phone);
}
