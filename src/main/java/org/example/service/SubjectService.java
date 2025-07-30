package org.example.service;

import org.example.models.Subject;

import java.util.List;

public interface SubjectService {
    Subject add(Subject subject);

    Iterable<Subject> getAll();
}
