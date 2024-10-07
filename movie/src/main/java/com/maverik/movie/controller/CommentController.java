package com.maverik.movie.controller;


import com.maverik.movie.entity.Comment;
import com.maverik.movie.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path= "api/v1/comments")
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> comments = commentService.getComments();
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @GetMapping(path= "api/v1/comments/{comment_id}")
    public ResponseEntity<Comment> getAComment(@PathVariable("comment_id") Integer commentId) {
        if( commentService.getAComment(commentId)!= null){
            return ResponseEntity.ok(commentService.getAComment(commentId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path= "api/v1/comments")
    public ResponseEntity<Comment>  addANewComment(@RequestBody Comment comment){
        try{
            commentService.addComment(comment);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping(path = "api/v1/comments/{comment_id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("comment_id") Integer commentId){
        try{
            commentService.deleteComment(commentId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(commentService.getAComment(commentId));
    }

    @Transactional
    @PutMapping(path="api/v1/comments/{comment_id}")
    public void updateComment(
            @PathVariable("comment_id") Integer commentId, @RequestParam Comment comment){

        commentService.updateComment(commentId,comment);

    }
}
