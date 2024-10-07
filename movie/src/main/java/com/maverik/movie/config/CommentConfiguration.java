package com.maverik.movie.config;


import com.maverik.movie.entity.Comment;
import com.maverik.movie.service.ReadCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommentConfiguration {

    ReadCommentService readCommentServiceService;

    @Autowired
    public CommentConfiguration(ReadCommentService readCommentServiceService) {
        this.readCommentServiceService = readCommentServiceService;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args->{

            String responseBody = readCommentServiceService.getCommentsFromExternal();
            List<Comment> comments = readCommentServiceService.readCommentsIntoCommentPOJO(responseBody);
            readCommentServiceService.populateCommentsIntoDB(comments);

        };

    };
}
