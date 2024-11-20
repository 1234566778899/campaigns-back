package backend.helpinghand.repositories;

import backend.helpinghand.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    public List<Organization> findByOrgName(String orgName);
}
