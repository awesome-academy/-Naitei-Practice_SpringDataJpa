package org.example.service;

import org.example.models.Follow;
import org.example.models.FollowId;
import org.example.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Override
    public Follow add(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public List<Follow> getAll() {
        return followRepository.findAll();
    }
}
