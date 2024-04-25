package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.Department;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.Floor;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.Office;
import com.fshk.webservices.rest.restfulwebservicesfshk.exception.notfoundexception.DepartmentNotFoundException;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.DepartmentRepository;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.FloorRepository;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.OfficeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentFloorOfficeResource {

    private FloorRepository floorRepository;

    private OfficeRepository officeRepository;

    private DepartmentRepository departmentRepository;

    public DepartmentFloorOfficeResource(FloorRepository floorRepository, OfficeRepository officeRepository, DepartmentRepository departmentRepository) {
        this.floorRepository = floorRepository;
        this.officeRepository = officeRepository;
        this.departmentRepository = departmentRepository;
    }

//======================================================================================================================
// FLOOR
//    Get /floor in department
    @GetMapping("/departments/{department_id}/floors")
    public List<Floor> retrieveFloorsForDepartment(@PathVariable long department_id){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        return department.get().getFloors();
    }

    //    Post /floor in department
    @PostMapping("/departments/{department_id}/floors/{floor_id}")
    public ResponseEntity<Object> createFloorsFromDepartment(
            @PathVariable long department_id,
            @PathVariable long floor_id
    ){
        Floor floor = floorRepository.findById(floor_id).orElse(null);
        Department department = departmentRepository.findById(department_id).orElse(null);

        if (floor == null || department == null) {
            return ResponseEntity.badRequest().body("Invalid floor or department.");
        }

        department.addFloor(floor);

        departmentRepository.save(department);

        return ResponseEntity.ok("Floor added to department successfully.");
    }

    //  Office
    //    Get /offices in department
    @GetMapping("/departments/{department_id}/offices")
    public List<Office> retrieveOfficeForDepartment(@PathVariable long department_id){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        return department.get().getOffice();
    }

    //    Post /office in department
    @PostMapping("/departments/{department_id}/offices/{office_id}")
    public ResponseEntity<Object> createOfficeForDepartment(
            @PathVariable long department_id,
            @PathVariable long office_id
    ){
        Office office = officeRepository.findById(office_id).orElse(null);
        Department department = departmentRepository.findById(department_id).orElse(null);

        if (office == null || department == null) {
            return ResponseEntity.badRequest().body("Invalid floor or department.");
        }

        department.addOffice(office);

        departmentRepository.save(department);

        return ResponseEntity.ok("Floor added to department successfully.");
    }

}
