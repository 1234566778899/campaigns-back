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
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment_text;
    private String comment_date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
