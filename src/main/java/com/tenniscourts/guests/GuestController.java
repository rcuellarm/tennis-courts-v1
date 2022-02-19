package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;

@AllArgsConstructor
@RestController
@Produces("application/json")
@Api(tags = {"Guest Controller"})
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @PostMapping(value = "guest")
    @ApiOperation(value = "Save a Guest info",
            notes = "Saving a Guest info and then return the entity created",
            response = GuestDTO.class)
    public ResponseEntity<Void> addGuest(@ApiParam(value = "Guest info to be saved", required = true) @RequestBody GuestDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getId())).build();
    }

    @GetMapping(value = "guest/{guestId}")
    @ApiOperation(value = "Find a Guest by id",
            notes = "Just one Guest can be found",
            response = GuestDTO.class)
    public ResponseEntity<GuestDTO> findGuest(@ApiParam(value = "Guest id", required = true) @PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.findGuest(guestId));
    }

    @GetMapping(value = "guest")
    @ApiOperation(value = "List all the guests",
            notes = "Multiple values of Guest can be found",
            response = GuestDTO.class,
            responseContainer = "List")
    public ResponseEntity<List<GuestDTO>> findGuests() {
        return ResponseEntity.ok(guestService.findGuests());
    }

    @GetMapping(value = "guest/name/{name}")
    @ApiOperation(value = "Find a Guest by name",
            notes = "Multiple values of Guest can be found because is finding a part of the name",
            response = GuestDTO.class,
            responseContainer = "List")
    public ResponseEntity<List<GuestDTO>> findGuest(@ApiParam(value = "A part of a Guest name", required = true) @PathVariable String name) {
        return ResponseEntity.ok(guestService.findGuestByName(name));
    }

    @DeleteMapping(value = "guest/{guestId}")
    @ApiOperation(value = "Delete a Guest",
            notes = "First, look for the Guest by id and then delete it",
            response = String.class)
    public ResponseEntity<String> deleteGuest(@ApiParam(value = "Guest id", required = true) @PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.deleteGuest(guestId));
    }

    @PutMapping(value = "guest")
    @ApiOperation(value = "Update the Guest info",
            notes = "First, look for the Guest by id and then update his info",
            response = GuestDTO.class)
    public ResponseEntity<GuestDTO> updateGuest(@ApiParam(value = "Guest info to be updated", required = true) @RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(guestDTO));
    }
}
