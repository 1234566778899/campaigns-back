package backend.helpinghand.controller;


import backend.helpinghand.entities.Campaign;
import backend.helpinghand.entities.Comment;
import backend.helpinghand.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpinghands")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> listAllComments() {
        return new ResponseEntity<>(commentService.listAllComments(), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment>  addComment( @RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @GetMapping("/comments/profiles/{profile_id}")
    public ResponseEntity<List<Comment>> listCommentsByProfileId(@PathVariable ("profile_id") Long profileId){
        return new ResponseEntity<>(commentService.listCommentsByProfileId(profileId),HttpStatus.OK);
    }

    @GetMapping("/comments/campaigns/{campaign_id}")
    public ResponseEntity<List<Comment>> listCommentsByCampaignId(@PathVariable ("campaign_id") Long id){
        return new ResponseEntity<>(commentService.listCommentsByCampaignId(id),HttpStatus.OK);
    }
}
