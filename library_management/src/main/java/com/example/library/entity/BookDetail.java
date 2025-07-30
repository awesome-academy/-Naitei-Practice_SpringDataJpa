package com.example.library.entity;

import jakarta.persistence.*;
import java.io.File;

@Entity
@Table(name = "book_detail")
public class BookDetail {
    @Id
    @GeneratedValue
    private Long id;

    private String coverUrl;
    private int pageCount;
    private String language;
    private String description;

    @OneToOne(mappedBy = "bookDetail")
    private Book book;

    public BookDetail(){}

    public BookDetail(String coverUrl, int pageCount, String language, String description){
        this.coverUrl = coverUrl;
        this.pageCount = pageCount;
        this.language = language;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/book_covers/";
    private static final String DEFAULT_COVER = "/book_covers/default.jpeg";
    public String getCoverUrl() {
        if(coverUrl==null || coverUrl.trim().isEmpty()){
            return DEFAULT_COVER;
        }
        File file = new File(UPLOAD_DIR + coverUrl.trim());
        return file.exists() ? "/book_covers/" + coverUrl.trim() : DEFAULT_COVER;
    }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
