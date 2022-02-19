package com.tenniscourts.tenniscourts;

import com.tenniscourts.schedules.ScheduleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Tennis Court Model", description="Model for a tennis court info")
public class TennisCourtDTO {
    @ApiModelProperty(value = "Id of a tennis court on the platform")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "The tennis court name")
    private String name;

    @ApiModelProperty(value = "The tennis court schedules created")
    private List<ScheduleDTO> tennisCourtSchedules;

}
