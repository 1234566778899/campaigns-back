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
@Table(name="campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String campName;
    private String description;
    private Double moneyGoal;
    private String startDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
    private List<Donation> donations;
}
