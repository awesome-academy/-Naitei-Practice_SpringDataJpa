package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DonationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "camp_id")
    private DonationCamp camp;

    private int quantityMl;

    @Temporal(TemporalType.DATE)
    private Date donationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public DonationCamp getCamp() {
        return camp;
    }

    public void setCamp(DonationCamp camp) {
        this.camp = camp;
    }

    public int getQuantityMl() {
        return quantityMl;
    }

    public void setQuantityMl(int quantityMl) {
        this.quantityMl = quantityMl;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }
}

