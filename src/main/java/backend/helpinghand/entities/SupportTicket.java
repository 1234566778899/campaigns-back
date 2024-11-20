package backend.helpinghand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "support_tickets")
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String ticketDescription;
    private String ticketDate;
    private String ticketDateUpdate;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;
}
