package org.example.Entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="employee")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String department;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Attendance> attendances;

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name=" + name + "}";

    }

}


