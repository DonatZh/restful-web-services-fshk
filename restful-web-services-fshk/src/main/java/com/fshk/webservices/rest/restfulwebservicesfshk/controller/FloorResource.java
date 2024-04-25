package com.fshk.webservices.rest.restfulwebservicesfshk.controller;

import com.fshk.webservices.rest.restfulwebservicesfshk.model.*;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.ClassroomRepository;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.FloorRepository;
import com.fshk.webservices.rest.restfulwebservicesfshk.repository.OfficeRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class FloorResource {

    private FloorRepository floorRepository;
    private OfficeRepository officeRepository;
    private ClassroomRepository classroomRepository;

    public FloorResource(FloorRepository floorRepository, OfficeRepository officeRepository, ClassroomRepository classroomRepository) {
        this.floorRepository = floorRepository;
        this.officeRepository = officeRepository;
        this.classroomRepository = classroomRepository;
    }

    //    GET /floor
    @GetMapping("/floors")
    public List<Floor> retrieveAllFloors(){
        return floorRepository.findAll();
    }


    //    Post /floor
    @PostMapping("/floors")
    public ResponseEntity<Object> createFloor(@Valid @RequestBody Floor floor){
        Floor savedFloor = floorRepository.save(floor);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{floor_id}")
                .buildAndExpand(savedFloor.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedFloor);
    }

    //  GET /Offices in floor
    @GetMapping("/floors/{floor_id}/offices")
    public List<Office> retrieveOfficesFromFloors(@PathVariable long floor_id){
        Optional<Floor> floor = floorRepository.findById(floor_id);

        return floor.get().getOffices();
    }

    // POST /Offices in floor
    @PostMapping("/floors/{floor_id}/offices")
    public ResponseEntity<Object> createOfficesFromFloors(@PathVariable long floor_id, @Valid @RequestBody Office office){
        Optional<Floor> floor = floorRepository.findById(floor_id);

        office.setFloor(floor.get());

        Office savedOffice = officeRepository.save(office);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{office_id}")
                .buildAndExpand(savedOffice.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedOffice);
    }

    //  GET /Classrooms in floor
    @GetMapping("/floors/{floor_id}/classrooms")
    public List<Classroom> retrieveClassroomsFromFloors(@PathVariable long floor_id){
        Optional<Floor> floor = floorRepository.findById(floor_id);

        return floor.get().getClassrooms();
    }

    // POST /Classrooms in floor
    @PostMapping("/floors/{floor_id}/classrooms")
    public ResponseEntity<Object> createClassroomsFromFloors(@PathVariable long floor_id, @Valid @RequestBody Classroom classroom){
        Optional<Floor> floor = floorRepository.findById(floor_id);

        classroom.setFloor(floor.get());

        Classroom savedClassroom = classroomRepository.save(classroom);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{classroom_id}")
                .buildAndExpand(savedClassroom.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedClassroom);
    }

}
