package org.example.Repository;


import org.example.Entity.Attendance;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends ListCrudRepository<Attendance, Long> {

}
