package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class DonationCamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "camp", cascade = CascadeType.ALL)
    private List<DonationLog> donationLogs;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
