package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.BaseRestController;
import com.tenniscourts.reservations.ReservationDTO;
import com.tenniscourts.schedules.ScheduleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Api(tags = {"Tennis Court Controller"})
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @PostMapping(value = "court")
    @ApiOperation(value = "Add a Tennis Court info",
            notes = "Saving a tennis court info and then return the tennis court created",
            response = TennisCourtDTO.class)
    public ResponseEntity<Void> addTennisCourt(
            @ApiParam(value = "Tennis court info to be saved", required = true) @RequestBody TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @GetMapping(value = "court/{tennisCourtId}")
    @ApiOperation(value = "Find a tennis court by id",
            notes = "Just one Tennis court entity can be found",
            response = TennisCourtDTO.class)
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(
            @ApiParam(value = "The Tennis court id to be found", required = true)
            @PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @GetMapping(value = "court/{tennisCourtId}/schedule")
    @ApiOperation(value = "Find the tennis court schedules",
            notes = "Return one Tennis court and all its schedules",
            response = TennisCourtDTO.class)
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(
            @ApiParam(value = "The Tennis court id for finding schedules", required = true)
            @PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
