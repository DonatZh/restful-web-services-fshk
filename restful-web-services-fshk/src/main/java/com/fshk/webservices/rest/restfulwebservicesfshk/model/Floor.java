package com.fshk.webservices.rest.restfulwebservicesfshk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Floor")
public class Floor {

    @Id
    @Column(name = "floor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "floor_number")
    private Long number;

    @OneToMany(mappedBy = "floor")
    @JsonIgnore
    private List<Office> offices;

    @OneToMany(mappedBy = "floor")
    @JsonIgnore
    private List<Classroom> classrooms;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    public Floor(Long id, Long number, List<Office> offices, List<Classroom> classrooms, Department department) {
        this.id = id;
        this.number = number;
        this.offices = offices;
        this.classrooms = classrooms;
        this.department = department;
    }

    public Floor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", number=" + number +
                ", offices=" + offices +
                ", classrooms=" + classrooms +
                ", department=" + department +
                '}';
    }
}
