package org.example.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class BookAuthorId implements Serializable {
    private Long BookId;
    private Long AuthorId;

    public BookAuthorId(){}

    public Long getBookId() {
        return BookId;
    }

    public void setBookId(Long bookId) {
        BookId = bookId;
    }

    public Long getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(Long authorId) {
        AuthorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthorId that = (BookAuthorId) o;
        return Objects.equals(BookId, that.BookId) && Objects.equals(AuthorId, that.AuthorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BookId, AuthorId);
    }
}
