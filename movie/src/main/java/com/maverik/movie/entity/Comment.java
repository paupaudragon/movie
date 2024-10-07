package com.maverik.movie.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Comment")
@Table(name= "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;

}
