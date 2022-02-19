package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import com.tenniscourts.guests.GuestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Api(tags = {"Reservation Controller"})
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @PostMapping(value = "reservation")
    @ApiOperation(value = "Book a Reservation",
            notes = "Saving a reservation info and then return the reservation created",
            response = ReservationDTO.class)
    public ResponseEntity<Void> bookReservation(
            @ApiParam(value = "Reservation info to be saved", required = true) @RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @GetMapping(value = "reservation/{reservationId}")
    @ApiOperation(value = "Find a reservation by id",
            notes = "Just one Reservation can be found",
            response = ReservationDTO.class)
    public ResponseEntity<ReservationDTO> findReservation(
            @ApiParam(value = "Reservation id", required = true) @PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @PutMapping(value = "reservation/{reservationId}")
    @ApiOperation(value = "Update the Reservation info which is cancelled",
            notes = "First, look for the Reservation by id and then update his state to CANCELLED",
            response = ReservationDTO.class)
    public ResponseEntity<ReservationDTO> cancelReservation(
            @ApiParam(value = "The id of the Reservation to be cancelled", required = true) @PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PutMapping(value = "reservation/{reservationId}/schedule/{scheduleId}")
    @ApiOperation(value = "Reschedule the Reservation",
            notes = "Cannot reschedule to the same slot, the " +
                    "previous Reservation status is set to RESCHEDULED and a new Reservation is created",
            response = ReservationDTO.class)
    public ResponseEntity<ReservationDTO> rescheduleReservation(
            @ApiParam(value = "Previous reservation id", required = true) @PathVariable Long reservationId,
            @ApiParam(value = "The new schedule id to be taken", required = true) @PathVariable Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
