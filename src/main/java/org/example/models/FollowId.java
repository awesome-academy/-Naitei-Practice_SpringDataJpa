package org.example.models;

import java.io.Serializable;
import java.util.Objects;

public class FollowId implements Serializable {

    private Integer follower;
    private Integer followed;

    public FollowId() {}

    public FollowId(Integer follower, Integer followed) {
        this.follower = follower;
        this.followed = followed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowId)) return false;
        FollowId that = (FollowId) o;
        return Objects.equals(follower, that.follower) &&
                Objects.equals(followed, that.followed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, followed);
    }
}
