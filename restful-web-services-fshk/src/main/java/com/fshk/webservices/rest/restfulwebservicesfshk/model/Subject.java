package com.fshk.webservices.rest.restfulwebservicesfshk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.versioning.LessonTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Subject")
public class Subject {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String name;

    @Column(name = "subject_credits")
    private Long credits;

    @OneToOne
    @JoinColumn(name = "lesson_hour")
    @JsonIgnore
    private LessonTime lessonTime;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    @JsonIgnore
    private Semester semester;


    public Subject(Long id, String name, Long credits, LessonTime lessonTime, Staff staff, Department department, Semester semester) {
        super();
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.lessonTime = lessonTime;
        this.staff = staff;
        this.department = department;
        this.semester = semester;
    }

    public Subject() {

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

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public LessonTime getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LessonTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", lessonTime=" + lessonTime +
                ", staff=" + staff +
                ", department=" + department +
                ", semester=" + semester +
                '}';
    }
}
