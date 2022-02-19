package com.tenniscourts.schedules;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value="Create Schedule Model", description="Model for creating a schedule info")
public class CreateScheduleRequestDTO {

    @NotNull
    @ApiModelProperty(value = "Id of a tennis court to be scheduled")
    private Long tennisCourtId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    @ApiModelProperty(value = "The start time of a schedule")
    private LocalDateTime startDateTime;

}
