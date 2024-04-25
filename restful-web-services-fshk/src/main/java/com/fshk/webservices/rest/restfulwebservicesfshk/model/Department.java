package com.fshk.webservices.rest.restfulwebservicesfshk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Staff> staff;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Floor> floors;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Office> office;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Semester> semesters;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Subject> subjects;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Consultation> consultations;

    public Department() {

    }

    public void addStaff(Staff staffMember) {
        this.staff.add(staffMember);
        staffMember.setDepartment(this);
    }

    public void removeStaff(Staff staffMember) {
        this.staff.remove(staffMember);
        staffMember.setDepartment(null);
    }

    public void addFloor(Floor floor) {
        this.floors.add(floor);
        floor.setDepartment(this);
    }

    public void addOffice(Office office) {
        this.office.add(office);
        office.setDepartment(this);
    }

    public Department(Long id, String name, List<Staff> staff, List<Floor> floors, List<Office> office, List<Semester> semesters, List<Subject> subjects, List<Consultation> consultations) {
        this.id = id;
        this.name = name;
        this.staff = staff;
        this.floors = floors;
        this.office = office;
        this.semesters = semesters;
        this.subjects = subjects;
        this.consultations = consultations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Office> getOffice() {
        return office;
    }

    public void setOffice(List<Office> office) {
        this.office = office;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", staff=" + staff +
                ", floors=" + floors +
                ", office=" + office +
                ", semesters=" + semesters +
                ", subjects=" + subjects +
                ", consultations=" + consultations +
                '}';
    }
}
