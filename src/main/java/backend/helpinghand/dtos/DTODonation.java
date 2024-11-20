package backend.helpinghand.dtos;

import backend.helpinghand.entities.Campaign;
import backend.helpinghand.entities.DonationStatus;
import backend.helpinghand.entities.Donor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor



public class DTODonation {
    private Long id;
    private Float moneyAmount;
    private String donationDate;
    private DonationStatus donationStatus;
    private String donationPayment;
    private Donor donor;
    private Campaign campaign;
}
