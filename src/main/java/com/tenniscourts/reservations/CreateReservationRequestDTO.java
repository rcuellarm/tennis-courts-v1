package com.tenniscourts.reservations;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@ApiModel(value="Create Reservation Model", description="Model for creating a reservation info")
public class CreateReservationRequestDTO {

    @NotNull
    @ApiModelProperty(value = "Id of a guest who makes a reservation")
    private Long guestId;

    @NotNull
    @ApiModelProperty(value = "Id of a tennis court schedule which is taken for a reservation")
    private Long scheduleId;

    @NotNull
    @ApiModelProperty(value = "The amount paid for a reservation")
    private BigDecimal value;

}
