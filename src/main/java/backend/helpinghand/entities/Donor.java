package backend.helpinghand.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String birthdate;

    @OneToOne(mappedBy = "donor", fetch = FetchType.EAGER)
    private Payment payment;

    @JsonIgnore
    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER)
    private List<Donation> donations;

    @JsonIgnore
    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER)
    private List<Notification> notifications;

    @JsonIgnore
    @OneToMany(mappedBy = "donor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SupportTicket> supportTickets;
}
