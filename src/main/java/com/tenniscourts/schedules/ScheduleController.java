package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import com.tenniscourts.reservations.ReservationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@RestController
@Api(tags = {"Schedule Controller"})
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    @PostMapping(value = "schedule")
    @ApiOperation(value = "Add a Schedule for a tennis court",
            notes = "Saving a schedule info and then return the schedule created",
            response = ScheduleDTO.class)
    public ResponseEntity<Void> addScheduleTennisCourt(
            @ApiParam(value = "Schedule info to be saved", required = true) @RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @GetMapping(value = "schedule")
    @ApiOperation(value = "Filter Schedules by two dates",
            notes = "Multiple Schedules can be found",
            response = ScheduleDTO.class)
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(
            @ApiParam(value = "The start date for filtering schedules", required = true)
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @ApiParam(value = "The end date for filtering schedules", required = true)
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @GetMapping(value = "schedule/{scheduleId}")
    @ApiOperation(value = "Find a schedule by id",
            notes = "Just one Schedule can be found",
            response = ScheduleDTO.class)
    public ResponseEntity<ScheduleDTO> findByScheduleId(
            @ApiParam(value = "The Schedule id to be found", required = true)
            @PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
