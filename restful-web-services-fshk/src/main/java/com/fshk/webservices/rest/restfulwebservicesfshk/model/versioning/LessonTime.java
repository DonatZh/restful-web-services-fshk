package com.fshk.webservices.rest.restfulwebservicesfshk.model.versioning;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.Subject;
import jakarta.persistence.*;

@Entity
@Table(name = "LessonTime")
public class LessonTime {

    @Id
    @Column(name = "lesson_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "lessonTime")
    @JsonIgnore
    private Subject subject;

    @Column(name = "lesson_hour")
    private Integer time;

    public LessonTime(Long id, Subject subject, Integer time) {
        this.id = id;
        this.subject = subject;
        this.time = time;
    }

    public LessonTime() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LessonTime{" +
                "id=" + id +
                ", subject=" + subject +
                ", time=" + time +
                '}';
    }
}
