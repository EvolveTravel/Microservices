package com.backendtravelbox.trip.interfaces.rest;

import com.backendtravelbox.trip.domain.model.commands.DeleteTripCommand;
import com.backendtravelbox.trip.domain.model.queries.GetAllTripQuery;
import com.backendtravelbox.trip.domain.model.queries.GetTripByIdQuery;
import com.backendtravelbox.trip.domain.service.TripCommandService;
import com.backendtravelbox.trip.domain.service.TripQueryService;
import com.backendtravelbox.trip.interfaces.rest.resource.CreateTripResource;
import com.backendtravelbox.trip.interfaces.rest.resource.TripResource;
import com.backendtravelbox.trip.interfaces.rest.resource.UpdateTripResource;
import com.backendtravelbox.trip.interfaces.rest.transform.CreateTripCommandFromResourceAssembler;
import com.backendtravelbox.trip.interfaces.rest.transform.TripResourceFromEntityAssembler;
import com.backendtravelbox.trip.interfaces.rest.transform.UpdateTripCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/trips", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Trip", description = "Trip Management Endpoints")
public class TripController {

    public final TripCommandService tripCommandService;
    public final TripQueryService tripQueryService;

    public TripController(TripCommandService tripCommandService, TripQueryService tripQueryService) {
        this.tripCommandService = tripCommandService;
        this.tripQueryService = tripQueryService;
    }

    @PostMapping
    public ResponseEntity<TripResource> createTrip(@RequestBody CreateTripResource createTripResource) {

        var createTripCommand = CreateTripCommandFromResourceAssembler.toCommandFromResource(createTripResource);
        var id = tripCommandService.handle(createTripCommand);
        if (id == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getTripByIdQuery = new GetTripByIdQuery(id);
        var trip = tripQueryService.handle(getTripByIdQuery);
        if (trip.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var tripResource = TripResourceFromEntityAssembler.toResourceFromEntity(trip.get());
        return new ResponseEntity<>(tripResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TripResource>> getAllTrips() {
        var getAllTripQuery = new GetAllTripQuery();
        var trip = tripQueryService.handle(getAllTripQuery);
        var tripResource = trip.stream().map(TripResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(tripResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResource> getTripById(@PathVariable Long id) {
        var getTripByIdQuery = new GetTripByIdQuery(id);
        var trip = tripQueryService.handle(getTripByIdQuery);
        if (trip.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var tripResource = TripResourceFromEntityAssembler.toResourceFromEntity(trip.get());
        return ResponseEntity.ok(tripResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResource> updateTrip(@PathVariable Long id, @RequestBody UpdateTripResource updateTripResource) {
        var updateUserCommand = UpdateTripCommandFromResourceAssembler.toCommandFromResource(id, updateTripResource);
        var updatedTrip = tripCommandService.handle(updateUserCommand);
        if (updatedTrip.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var tripResource = TripResourceFromEntityAssembler.toResourceFromEntity(updatedTrip.get());
        return ResponseEntity.ok(tripResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        var deleteTripCommand = new DeleteTripCommand(id);
        tripCommandService.handle(deleteTripCommand);
        return ResponseEntity.ok("Trip with given id successfully deleted");
    }
}