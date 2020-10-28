package com.ecutb.assignment.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Table(name = "book")
public class Book implements Serializable {
    private static final long serialVersionUID = 8710430446784923307L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Isbn cannot be empty.")
    @Size(min = 13, max = 13, message = "Isbn must consist of 13 digits.")
    private String isbn;
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    @NotEmpty(message = "Genre cannot be empty.")
    @Size(min = 4, message = "Genre length invalid")
    private String genre;
    @NotEmpty(message = "Author cannot be empty.")
    @Size(min = 3, message = "Author length invalid")
    private String author;
    private boolean isAvailable;


}
