package backend.helpinghand.services;

import backend.helpinghand.entities.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> listAllComments();
    public Comment addComment(Comment comment);
    public List<Comment> listCommentsByProfileId(Long profileId);
    public List<Comment> listCommentsByCampaignId(Long campaignId);
}
