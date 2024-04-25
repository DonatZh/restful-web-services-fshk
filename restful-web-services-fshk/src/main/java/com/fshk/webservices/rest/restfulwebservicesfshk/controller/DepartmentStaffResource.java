package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.Department;
import com.fshk.webservices.rest.restfulwebservicesfshk.model.Staff;
import com.fshk.webservices.rest.restfulwebservicesfshk.exception.notfoundexception.DepartmentNotFoundException;
import com.fshk.webservices.rest.restfulwebservicesfshk.exception.notfoundexception.StaffNotFoundException;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.DepartmentRepository;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.StaffRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentStaffResource {

    private DepartmentRepository departmentRepository;
    private StaffRepository staffRepository;

    public DepartmentStaffResource(DepartmentRepository departmentRepository, StaffRepository staffRepository) {
        this.departmentRepository = departmentRepository;
        this.staffRepository = staffRepository;
    }

    //======================================================================================================================
//    STAFF
//    Get /staffs in department
    @GetMapping("/departments/{department_id}/staffs")
    public List<Staff> retrieveStaffsFromDepartment(@PathVariable long department_id){
        Optional<Department> department = departmentRepository.findById(department_id);

        if(department.isEmpty()){
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        return department.get().getStaff();
    }

//    //    Post /staffs in department
//    @PostMapping("/departments/{department_id}/staffs")
//    public ResponseEntity<Object> createStaffsFromDepartment(@PathVariable long department_id, @Valid @RequestBody Staff staff){
//        Optional<Department> department = departmentRepository.findById(department_id);
//
//        if(department.isEmpty()){
//            throw new DepartmentNotFoundException("id: " + department_id);
//        }
//
//        staff.setDepartment(department.get());
//
//        Staff savedStaff = staffRepository.save(staff);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{staff_id}")
//                .buildAndExpand(savedStaff.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(savedStaff);
//    }

    //    Delete /staffs in department
//    @DeleteMapping("/departments/{department_id}/staffs/{staff_id}")
//    public ResponseEntity<Void> deleteStaffsFromDepartment(
//            @PathVariable long department_id,
//            @PathVariable long staff_id
//    ){
//        Optional<Department> department = departmentRepository.findById(department_id);
//
//        if(department.isEmpty()){
//            throw new DepartmentNotFoundException("id: " + department_id);
//        }
//
//        Optional<Staff> staff = staffRepository.findById(staff_id);
//
//        if(staff.isEmpty()){
//            throw new StaffNotFoundException("id: " + staff_id);
//        }
//
//        staffRepository.deleteById(staff_id);
//
//        return ResponseEntity.noContent().build();
//    }

    //    Edit /staffs in department
    @PutMapping("/departments/{department_id}/staffs/{staff_id}")
    public ResponseEntity<Object> updateStaffsFromDepartment(
            @PathVariable long department_id,
            @PathVariable long staff_id,
            @Valid @RequestBody Staff updateStaff
    ){
        Optional<Department> department = departmentRepository.findById(department_id);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("id: " + department_id);
        }

        Optional<Staff> staff = staffRepository.findById(staff_id);

        if (staff.isEmpty()) {
            throw new StaffNotFoundException("id: " + staff_id);
        }

        Staff existingStaff = staff.get();
        existingStaff.setName(updateStaff.getName());
        existingStaff.setEmail(updateStaff.getEmail());

        Staff savedStaff = staffRepository.save(existingStaff);

        return ResponseEntity.ok(savedStaff);
    }

    @PostMapping("/departments/{department_id}/staff/{staff_id}")
    public ResponseEntity<String> addStaffToDepartment(
            @PathVariable long staff_id,
            @PathVariable long department_id
    ) {
        // Retrieve the existing staff member and department from the database
        Staff staffMember = staffRepository.findById(staff_id).orElse(null);
        Department department = departmentRepository.findById(department_id).orElse(null);

        if (staffMember == null || department == null) {
            return ResponseEntity.badRequest().body("Invalid staff member or department.");
        }

        // Add the staff member to the department
        department.addStaff(staffMember);

        // Save the changes to the database
        departmentRepository.save(department);

        return ResponseEntity.ok("Staff member added to department successfully.");
    }

    @DeleteMapping("/departments/{department_id}/staff/{staff_id}")
    public ResponseEntity<String> removeStaffFromDepartment(
            @PathVariable long staff_id,
            @PathVariable long department_id
    ) {
        // Retrieve the existing staff member and department from the database
        Staff staffMember = staffRepository.findById(staff_id).orElse(null);
        Department department = departmentRepository.findById(department_id).orElse(null);

        if (staffMember == null || department == null) {
            return ResponseEntity.badRequest().body("Invalid staff member or department.");
        }

        // Remove the staff member from the department
        department.removeStaff(staffMember);

        // Save the changes to the database
        departmentRepository.save(department);

        return ResponseEntity.ok("Staff member removed from department successfully.");
    }


}
