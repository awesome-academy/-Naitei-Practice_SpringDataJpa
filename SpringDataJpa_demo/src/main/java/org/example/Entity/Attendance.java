package org.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "attendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "check_in")
    private LocalTime checkIn;
    @Column(name = "check_out")
    private LocalTime checkOut;
    @Column(name = "note")
    private String note;

    @Override
    public String toString() {
        return "Attendance{id=" + id + ", date=" + date + "}";

    }

}


