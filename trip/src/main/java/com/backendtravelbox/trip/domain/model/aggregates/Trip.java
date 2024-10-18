package com.backendtravelbox.trip.domain.model.aggregates;

import com.backendtravelbox.trip.domain.model.commands.CreateTripCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "Trip")
public class Trip extends AbstractAggregateRoot<Trip> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String origin;

    @Getter
    private String destination;

    @Getter
    private String date;

    public Trip() {
        this.origin = Strings.EMPTY;
        this.destination = Strings.EMPTY;
        this.date = Strings.EMPTY;
    }

    public Trip(String origin, String destination, String date) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public Trip(CreateTripCommand command) {
        this();
        this.origin = command.origin();
        this.destination = command.destination();
        this.date = command.date();
    }

    public Trip updateTrip(String origin, String destination, String date) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        return this;
    }
}