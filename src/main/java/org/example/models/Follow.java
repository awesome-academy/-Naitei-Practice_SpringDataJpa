package org.example.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "follows")
@IdClass(FollowId.class) // composite key
public class Follow {

    @Id
    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User follower;

    @Id
    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    private User followed;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
