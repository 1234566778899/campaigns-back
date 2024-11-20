package backend.helpinghand.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTODonor {
    private Long id;


    private String name;
    private String email;
    private String address;
    private String phone;
    private String birthdate;

    private String payment;
    private String donations;
    private String comments;
    private String notifications;
}
