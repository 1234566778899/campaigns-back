package backend.helpinghand.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float moneyAmount;
    private String donationDate;


    @OneToOne(mappedBy = "donation", fetch = FetchType.EAGER)
    private DonationStatus donationStatus;


    @OneToOne
    @JoinColumn(name = "paymentCard")
    private Payment donationPayment;


    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;


    @ManyToOne
    @JoinColumn(name ="campaign_id")
    private Campaign campaign;
}
