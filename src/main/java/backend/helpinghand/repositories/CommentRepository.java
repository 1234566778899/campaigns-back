package backend.helpinghand.repositories;

import backend.helpinghand.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByDonor_Id(Long donorId);
    public List<Comment> findByCampaign_Id(Long campaignId);
}
