package com.fshk.webservices.rest.restfulwebservicesfshk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Consultation")
public class Consultation {

    public enum Day {
        Hëne,
        Marte,
        Mërkure,
        Enjte,
        Premte
    }

    @Id
    @Column(name = "consultation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consultation_day")
    private Day day;

    @Column(name = "start_of_consultation")
    private Long startOfConsultation;

    @Column(name = "end_of_consultation")
    private Long endOfConsultation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    @JsonIgnore
    private Staff staff;

    public Consultation(Long id, Day day, Long startOfConsultation, Long endOfConsultation, Department department, Staff staff) {
        this.id = id;
        this.day = day;
        this.startOfConsultation = startOfConsultation;
        this.endOfConsultation = endOfConsultation;
        this.department = department;
        this.staff = staff;
    }

    public Consultation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Long getStartOfConsultation() {
        return startOfConsultation;
    }

    public void setStartOfConsultation(Long startOfConsultation) {
        this.startOfConsultation = startOfConsultation;
    }

    public Long getEndOfConsultation() {
        return endOfConsultation;
    }

    public void setEndOfConsultation(Long endOfConsultation) {
        this.endOfConsultation = endOfConsultation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", day=" + day +
                ", startOfConsultation=" + startOfConsultation +
                ", endOfConsultation=" + endOfConsultation +
                ", department=" + department +
                ", staff=" + staff +
                '}';
    }
}