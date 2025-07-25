package com.example.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_detail_id")
    private BookDetail bookDetail;

    private LocalDate publishedDay;

    public Book(){}

    public Book(String title, Author author, Publisher publisher, LocalDate publishedDay){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedDay = publishedDay;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher publisher) { this.publisher = publisher; }

    public LocalDate getPublishedDay() { return publishedDay; }
    public void setPublishedDay(LocalDate publishedDay) { this.publishedDay = publishedDay;}

    public BookDetail getBookDetail() {return bookDetail;}
    public void setBookDetail(BookDetail bookDetail) {this.bookDetail = bookDetail;}
}
