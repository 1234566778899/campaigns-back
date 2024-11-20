package backend.helpinghand.entities;

import backend.helpinghand.repositories.DonationStatusRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="donationStatus")
public class DonationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    @OneToOne
    @JoinColumn(name="donations")
    private Donation donation;
}
