package com.maverik.movie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverik.movie.entity.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ReadCommentService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getCommentsFromExternal() throws RuntimeException {
        try {

            String url = "https://jsonplaceholder.typicode.com/comments";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//            System.out.println(response.getBody());
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public List<Comment> readCommentsIntoCommentPOJO(String responseBody) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        List<Comment> comments = List.of(mapper.readValue(responseBody, Comment[].class));
        System.out.println(comments);

        return comments;

    }

}
