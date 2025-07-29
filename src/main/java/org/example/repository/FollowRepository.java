package org.example.repository;

import org.example.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FollowRepository extends JpaRepository<Follow, Long> {
}
