package com.tenniscourts.guests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value="Guess Model", description="Model for a guess info")
public class GuestDTO {
    @ApiModelProperty(value = "Id of a guess on the reservation platform")
    private Long id;

    @ApiModelProperty(value = "Name of a guess")
    private String name;
}
