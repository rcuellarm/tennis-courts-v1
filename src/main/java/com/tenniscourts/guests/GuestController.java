package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @PostMapping(value = "guest")
    public ResponseEntity<Void> addGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getId())).build();
    }

    @GetMapping(value = "guest/{guestId}")
    public ResponseEntity<GuestDTO> findGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.findGuest(guestId));
    }

    @GetMapping(value = "guest")
    public ResponseEntity<List<GuestDTO>> findGuests() {
        return ResponseEntity.ok(guestService.findGuests());
    }

    @GetMapping(value = "guest/name/{name}")
    public ResponseEntity<List<GuestDTO>> findGuest(@PathVariable String name) {
        return ResponseEntity.ok(guestService.findGuestByName(name));
    }

    @DeleteMapping(value = "guest/{guestId}")
    public ResponseEntity<String> deleteGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.deleteGuest(guestId));
    }

    @PutMapping(value = "guest")
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(guestDTO));
    }
}
