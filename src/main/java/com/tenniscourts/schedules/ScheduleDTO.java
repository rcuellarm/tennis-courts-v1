package com.tenniscourts.schedules;

import com.tenniscourts.reservations.ReservationDTO;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ApiModel(value="Schedule Model", description="Model for a schedule info")
public class ScheduleDTO {
    @ApiModelProperty(value = "Id of a schedule on the platform")
    private Long id;

    @ApiModelProperty(value = "The tennis court scheduled info")
    private TennisCourtDTO tennisCourt;

    @NotNull
    @ApiModelProperty(value = "The tennis court scheduled id")
    private Long tennisCourtId;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull
    @ApiModelProperty(value = "The start time for the schedule")
    private LocalDateTime startDateTime;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @ApiModelProperty(value = "The end time for the schedule")
    private LocalDateTime endDateTime;

}
