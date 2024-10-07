package com.maverik.movie.service;

import com.maverik.movie.entity.Comment;
import com.maverik.movie.repo.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {

        //primary key need to be unique
        Optional<Comment> commOp = commentRepository.findById(comment.getId());
        if (commOp.isPresent()) {
            throw new IllegalStateException("comment already exists");
        }
        commentRepository.save(comment);
    }

    public void deleteComment(Integer commentId) {
        Optional<Comment> commOp = commentRepository.findById(commentId);
        if (commOp.isEmpty()) {
            throw new IllegalStateException("comment doesn't exists");
        }
        commentRepository.deleteById(commentId);
    }

    @Modifying
    @Transactional
    public Comment updateComment(Integer commentId, Comment comment) {
        Optional<Comment> commOp = commentRepository.findById(commentId);
        if(commOp.isEmpty()) {
            throw new IllegalStateException("comment doesn't exists");
        }
        Comment commentOriginal = commOp.get();
        if(!commentOriginal.getBody().equals(comment.getBody())) {
            commentOriginal.setBody(comment.getBody());
        }

        if(!commentOriginal.getName().equals(comment.getName())) {
            commentOriginal.setName(comment.getName());
        }

        if(!commentOriginal.getEmail().equals(comment.getEmail())) {
            commentOriginal.setEmail(comment.getEmail());
        }

        if(commentOriginal.getPostId().equals(comment.getPostId())) {
            commentOriginal.setPostId(comment.getPostId());
        }
        commentRepository.save(commentOriginal);
        return commentOriginal;
    }

    public Comment getAComment(Integer customer_id) {
        Optional<Comment> comm = commentRepository.findById(customer_id);
        return comm.orElse(null);
    }
}
