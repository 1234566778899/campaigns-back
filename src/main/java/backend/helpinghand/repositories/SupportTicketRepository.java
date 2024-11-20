package backend.helpinghand.repositories;

import backend.helpinghand.entities.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
}
