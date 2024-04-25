package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.Staff;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.StaffRepository;
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
public class StaffResource {

    private StaffRepository staffRepository;

    public StaffResource(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    //    GET /departments
    @GetMapping("/staff")
    public List<Staff> retrieveAllStaff(){
        return staffRepository.findAll();
    }


    //    Post /staff
    @PostMapping("/staff")
    public ResponseEntity<Object> createStaff(@Valid @RequestBody Staff staff){
        Staff savedStaff = staffRepository.save(staff);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{staff_id}")
                .buildAndExpand(savedStaff.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedStaff);
    }

}
