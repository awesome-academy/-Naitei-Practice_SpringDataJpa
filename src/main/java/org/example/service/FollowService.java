package org.example.service;

import org.example.models.Follow;

import java.util.List;

public interface FollowService {
    Follow add(Follow follow);
    List<Follow> getAll();
}
