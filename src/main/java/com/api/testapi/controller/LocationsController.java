package com.api.testapi.controller;

import com.api.testapi.exceptions.LocationNotFoundException;
import com.api.testapi.model.Location;
import com.api.testapi.repository.LocationRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class LocationsController {

    private final LocationRepository locationRepository;

    LocationsController(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @GetMapping("/locations")
    CollectionModel<EntityModel<Location>> all() {
        List<EntityModel<Location>> locations = locationRepository.findAll().stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(LocationsController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(LocationsController.class).all()).withRel("locations")))
                .collect(Collectors.toList());

        return CollectionModel.of(locations, linkTo(methodOn(LocationsController.class).all()).withSelfRel());
    }

    @PostMapping("/locations")
    Location newLocation(@RequestBody Location newLocation) {
        return locationRepository.save(newLocation);
    }

    @GetMapping("/locations/{id}")
    EntityModel<Location> one(@PathVariable Long id) {
        Location location = locationRepository.findById(id) //
                .orElseThrow(() -> new LocationNotFoundException(id));
        return EntityModel.of(location,
                linkTo(methodOn(LocationsController.class).one(id)).withSelfRel(),
                linkTo(methodOn(LocationsController.class).all()).withRel("locations"));
    }

    @PutMapping("/locations/{id}")
    Location replaceEmployee(@RequestBody Location newLocation, @PathVariable Long id) {

        return locationRepository.findById(id)
                .map(location -> {
                    location.setName(newLocation.getName());
                    location.setAddress(newLocation.getAddress());
                    return locationRepository.save(location);
                })
                .orElseGet(() -> {
                    newLocation.setId(id);
                    return locationRepository.save(newLocation);
                });
    }

    @DeleteMapping("/locations/{id}")
    void deleteEmployee(@PathVariable Long id) {
        locationRepository.deleteById(id);
    }
}
