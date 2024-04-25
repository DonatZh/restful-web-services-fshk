package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.Subject;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.SubjectRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SubjectResource {

    private SubjectRepository subjectRepository;

    public SubjectResource(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    //    GET /departments
    @GetMapping("/subject")
    public List<Subject> retrieveAllSubjects(){
        return subjectRepository.findAll();
    }


    //    Post /staff
    @PostMapping("/subject")
    public ResponseEntity<Object> createSubject(@Valid @RequestBody Subject subject){
        Subject savedSubject = subjectRepository.save(subject);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{subject_id}")
                .buildAndExpand(savedSubject.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedSubject);
    }

}
