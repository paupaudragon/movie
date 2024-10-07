package com.maverik.movie.entity;


import jakarta.persistence.Column;
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

    @Column(
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            columnDefinition = "TEXT"
    )
    private String body;

}
