package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String title;

    private int total_quantity;

    private String publisher;

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
