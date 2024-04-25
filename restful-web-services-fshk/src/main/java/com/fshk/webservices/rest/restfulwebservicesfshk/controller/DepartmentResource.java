package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.*;
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
public class DepartmentResource {

    private DepartmentRepository departmentRepository;
    private StaffRepository staffRepository;
    private SemesterRepository semesterRepository;
    private FloorRepository floorRepository;
    private OfficeRepository officeRepository;


    public DepartmentResource(
            DepartmentRepository departmentRepository,
            StaffRepository staffRepository,
            SemesterRepository semesterRepository,
            FloorRepository floorRepository,
            OfficeRepository officeRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.staffRepository = staffRepository;
        this.semesterRepository = semesterRepository;
        this.floorRepository = floorRepository;
        this.officeRepository = officeRepository;
    }

//======================================================================================================================
//    DEPARTMENT
//    GET /departments
    @GetMapping("/departments")
    public List<Department> retrieveAllDepartments(){
        return departmentRepository.findAll();
    }

//    POST /departments
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department){
        Department savedDepartment = departmentRepository.save(department);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/department_id")
                .buildAndExpand(savedDepartment.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedDepartment);
    }

    //    Edit /departments
    @PutMapping("/departments/{department_id}")
    public ResponseEntity<Object> updateDepartment(
            @PathVariable long department_id,
            @Valid @RequestBody Department updateDepartment
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        Department existingDepartment = department.get();
        existingDepartment.setName(updateDepartment.getName());

        Department savedDepartment = departmentRepository.save(existingDepartment);

        return ResponseEntity.ok(savedDepartment);
    }


    //    Delete /departments
    @DeleteMapping("/departments/{department_id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable long department_id) {
        departmentRepository.deleteById(department_id);
        return ResponseEntity.noContent().build();
    }

}
