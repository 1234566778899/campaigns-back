package backend.helpinghand.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOCampaignSummary {
    private Long id;
    private String campaign_name;
    private Double money_goal;
    private Double money_raised;
    private Double percentage;
    private String start_date;
    private String end_date;
}
