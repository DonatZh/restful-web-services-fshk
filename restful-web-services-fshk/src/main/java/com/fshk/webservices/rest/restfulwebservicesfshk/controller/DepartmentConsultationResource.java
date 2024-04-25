package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.Consultation;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.Department;
import com.fshk.webservices.rest.restfulwebservicesfshk.exception.notfoundexception.DepartmentNotFoundException;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentConsultationResource {

    private DepartmentRepository departmentRepository;
    private ConsultationRepository consultationRepository;

    public DepartmentConsultationResource(
            DepartmentRepository departmentRepository,
            ConsultationRepository consultationRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.consultationRepository = consultationRepository;
    }

    //======================================================================================================================
//    DEPARTMENT
//    GET /Consultation for department
    @GetMapping("/departments/{department_id}/consultations")
    public List<Consultation> retrieveConsultationForDepartment(
            @PathVariable long department_id
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        return department.get().getConsultations();
    }

//    POST /Consultation for department
    @PostMapping("/departments/{department_id}/consultations")
    public ResponseEntity<Object> createConsultationForDepartment(
            @PathVariable long department_id,
            @Valid @RequestBody Consultation consultation
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        consultation.setDepartment(department.get());

        Consultation savedConsultation = consultationRepository.save(consultation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{consultation_id}")
                .buildAndExpand(savedConsultation.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedConsultation);
    }

    //    Edit /Consultation for department
    @PutMapping("/departments/{department_id}/consultation/{consultation_id}")
    public ResponseEntity<Object> updateConsultationForDepartment(
            @PathVariable long department_id,
            @PathVariable long consultation_id,
            @Valid @RequestBody Consultation updateConsultation
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        Optional<Consultation> consultation = consultationRepository.findById(consultation_id);

        Consultation existionConsultation = consultation.get();
        existionConsultation.setDay(updateConsultation.getDay());
        existionConsultation.setStartOfConsultation(updateConsultation.getStartOfConsultation());
        existionConsultation.setEndOfConsultation(updateConsultation.getEndOfConsultation());

        Consultation savedConsultation = consultationRepository.save(existionConsultation);

        return ResponseEntity.ok(savedConsultation);
    }


    //    Delete /departments
    @DeleteMapping("/departments/{department_id}/consultation/{consultation_id}")
    public ResponseEntity<Void> deleteConsulationForDepartment(
            @PathVariable long department_id,
            @PathVariable long consultation_id
    ) {
        Optional<Department> department = departmentRepository.findById(department_id);

        Optional<Consultation> consultation = consultationRepository.findById(consultation_id);

        consultationRepository.deleteById(consultation_id);

        return ResponseEntity.noContent().build();
    }

}
