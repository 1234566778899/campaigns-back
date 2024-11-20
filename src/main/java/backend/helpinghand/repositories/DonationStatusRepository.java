package backend.helpinghand.repositories;

import backend.helpinghand.entities.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationStatusRepository extends JpaRepository<DonationStatus, Long> {
    public DonationStatus findByStatus(String status);

}
