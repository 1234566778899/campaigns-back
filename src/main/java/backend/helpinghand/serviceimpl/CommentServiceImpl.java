package backend.helpinghand.serviceimpl;

import backend.helpinghand.entities.Comment;
import backend.helpinghand.repositories.CommentRepository;
import backend.helpinghand.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> listCommentsByProfileId(Long profileId) {
        return commentRepository.findByDonor_Id(profileId);
    }

    @Override
    public List<Comment> listCommentsByCampaignId(Long campaignId) {
        return commentRepository.findByCampaign_Id(campaignId);
    }
}
