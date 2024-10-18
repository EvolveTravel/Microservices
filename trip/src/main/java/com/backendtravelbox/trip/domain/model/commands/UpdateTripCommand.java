package com.backendtravelbox.trip.domain.model.commands;

public record UpdateTripCommand(Long id,
                                String origin,
                                String destination,
                                String date) {
}