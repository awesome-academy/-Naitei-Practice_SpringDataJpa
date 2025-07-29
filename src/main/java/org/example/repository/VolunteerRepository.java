package org.example.repository;

import java.util.List;

import org.example.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Volunteer findByEmail(String email);

    List<Volunteer> findByFullNameContaining(String fullName);

    @Query("SELECT DISTINCT v " +
            "FROM Volunteer v " +
            "JOIN v.skillLevels sl " +
            "JOIN sl.skill s " +
            "WHERE s.name = :name")
    List<Volunteer> findBySkillName(@Param("name") String name);

    @Query("SELECT DISTINCT v " +
            "FROM Volunteer v "+
            "JOIN v.participationLogs vp " +
            "JOIN vp.campaign c " +
            "WHERE CURRENT_DATE " +
            "BETWEEN c.startDate " +
            "AND c.endDate")
    List<Volunteer> findActiveVolunteers();




}