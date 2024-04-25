package com.fshk.webservices.rest.restfulwebservicesfshk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.versioning.Name;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Staff")
public class Staff {

    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(name = "staff_name")
    private Name name;

    @Column(name = "staff_email")
    private String email;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    public Staff(Long id, Name name, String email, List<Consultation> consultations, List<Subject> subjects, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.consultations = consultations;
        this.subjects = subjects;
        this.department = department;
    }

    public Staff() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", consultations=" + consultations +
                ", subjects=" + subjects +
                ", department=" + department +
                '}';
    }
}
