package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.Department;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.Semester;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.Subject;
import com.fshk.webservices.rest.restfulwebservicesfshk.exception.notfoundexception.DepartmentNotFoundException;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.DepartmentRepository;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.SemesterRepository;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.SubjectRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentSemesterResource {

    private DepartmentRepository departmentRepository;
    private SemesterRepository semesterRepository;

    private SubjectRepository subjectRepository;

    public DepartmentSemesterResource(
            DepartmentRepository departmentRepository,
            SemesterRepository semesterRepository,
            SubjectRepository subjectRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.semesterRepository = semesterRepository;
        this.subjectRepository = subjectRepository;
    }


//======================================================================================================================
// SEMESTER
    //    Get /semester in department
    @GetMapping("/departments/{department_id}/semesters")
    public List<Semester> retrieveSemestersFromDepartment(@PathVariable long department_id){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        return department.get().getSemesters();
    }

//    Post /semester in department
    @PostMapping("/departments/{department_id}/semesters")
    public ResponseEntity<Object> createSemestersFromDepartment(
            @PathVariable long department_id,
            @Valid @RequestBody Semester semester
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        semester.setDepartment(department.get());

        Semester savedSemester = semesterRepository.save(semester);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{semester_id}")
                .buildAndExpand(savedSemester.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedSemester);
    }

//    Delete /semester in department
    @DeleteMapping("/departments/{department_id}/semesters/{semester_id}")
    public ResponseEntity<Void> deleteSemestersFromDepartment(
            @PathVariable long department_id,
            @PathVariable long semester_id
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        Optional<Semester> semester = semesterRepository.findById(semester_id);

        semesterRepository.deleteById(semester_id);

        return ResponseEntity.noContent().build();
    }

    //    Edit /semester in department
    @PutMapping("/departments/{department_id}/semesters/{semester_id}")
    public ResponseEntity<Object> updateSemestersFromDepartment(
            @PathVariable long department_id,
            @PathVariable long semester_id,
            @Valid @RequestBody Semester updateSemester
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        Optional<Semester> semester = semesterRepository.findById(semester_id);

        Semester existingSemester = semester.get();
        existingSemester.setName(updateSemester.getName());
        existingSemester.setNumber(updateSemester.getNumber());

        Semester savedSemester = semesterRepository.save(existingSemester);

        return ResponseEntity.ok(savedSemester);
    }
//======================================================================================================================
//SUBJECT
    //    Get /subjects in semester
    @GetMapping("/departments/{department_id}/semesters/{semester_id}/subjects")
    public List<Subject> retrieveSubjectsFromDepartment(
            @PathVariable long department_id,
            @PathVariable long semester_id
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        Optional<Semester> semester = semesterRepository.findById(semester_id);

        return semester.get().getSubject();
    }

    //    Post /subjects in semester
    @PostMapping("/departments/{department_id}/semesters/{semester_id}/subjects")
    public ResponseEntity<Object> createSubjectsFromDepartment(
            @PathVariable long department_id,
            @PathVariable long semester_id,
            @Valid @RequestBody Subject subject
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        Optional<Semester> semester = semesterRepository.findById(semester_id);

        subject.setSemester(semester.get());

        Subject saveSubject = subjectRepository.save(subject);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{subject_id}")
                .buildAndExpand(saveSubject.getId())
                .toUri();

        return ResponseEntity.created(location).body(saveSubject);
    }
//
//    //    Delete /semester in department
//    @DeleteMapping("/departments/{department_id}/semesters/{semester_id}")
//    public ResponseEntity<Void> deleteSemestersFromDepartment(
//            @PathVariable long department_id,
//            @PathVariable long semester_id
//    ){
//        Optional<Department> department = departmentRepository.findById(department_id);
//
//        if(department.isEmpty()){
//            throw new DepartmentNotFoundException("id: " + department_id);
//        }
//
//        Optional<Semester> semester = semesterRepository.findById(semester_id);
//
//        semesterRepository.deleteById(semester_id);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    //    Edit /semester in department
//    @PutMapping("/departments/{department_id}/semesters/{semester_id}")
//    public ResponseEntity<Object> updateStaffsFromDepartment(
//            @PathVariable long department_id,
//            @PathVariable long semester_id,
//            @Valid @RequestBody Semester updateSemester
//    ){
//        Optional<Department> department = departmentRepository.findById(department_id);
//
//        if (department.isEmpty()) {
//            throw new DepartmentNotFoundException("id: " + department_id);
//        }
//
//        Optional<Semester> semester = semesterRepository.findById(semester_id);
//
//        Semester existingSemester = semester.get();
//        existingSemester.setName(updateSemester.getName());
//        existingSemester.setNumber(updateSemester.getNumber());
//
//        Semester savedSemester = semesterRepository.save(existingSemester);
//
//        return ResponseEntity.ok(savedSemester);
//    }



}
