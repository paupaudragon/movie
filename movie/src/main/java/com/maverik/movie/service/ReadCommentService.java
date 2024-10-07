package com.maverik.movie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverik.movie.entity.Comment;
import com.maverik.movie.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ReadCommentService {

    private final RestTemplate restTemplate= new RestTemplate();

    private final CommentRepository commentRepository;

    @Autowired
    public ReadCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public String getCommentsFromExternal() throws RuntimeException {
        try {

            String url = "https://jsonplaceholder.typicode.com/comments";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public List<Comment> readCommentsIntoCommentPOJO(String responseBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return List.of(mapper.readValue(responseBody, Comment[].class));

    }

    public void populateCommentsIntoDB(List<Comment> comments){

        int batch = 10;
        int i;
        for(i=0; i<batch; i++){
            commentRepository.save(comments.get(i));
        }

        System.out.println(commentRepository.count());
    }

}
