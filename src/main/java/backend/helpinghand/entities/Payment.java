package backend.helpinghand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="paymentcards")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardnumber;
    private String expirydate;
    private Integer cvv;

    @OneToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;
}
