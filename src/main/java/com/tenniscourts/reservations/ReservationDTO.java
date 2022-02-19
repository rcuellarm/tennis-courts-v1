package com.tenniscourts.reservations;

import com.tenniscourts.guests.GuestDTO;
import com.tenniscourts.schedules.ScheduleDTO;
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
@ApiModel(value="Reservation Model", description="Model for a reservation info")
public class ReservationDTO {
    @ApiModelProperty(value = "Id of a reservation on the platform")
    private Long id;

    @ApiModelProperty(value = "Schedule related with the reservation")
    private ScheduleDTO schedule;

    @ApiModelProperty(value = "Guest who schedules the reservation")
    private GuestDTO guest;

    @ApiModelProperty(value = "The reservation status", allowableValues = "READY_TO_PLAY, CANCELLED, RESCHEDULED")
    private String reservationStatus;

    @ApiModelProperty(value = "Previous reservation if someone changes it")
    private ReservationDTO previousReservation;

    @ApiModelProperty(value = "Refund amount if the reservation is cancelled")
    private BigDecimal refundValue;

    @ApiModelProperty(value = "The paid amount for scheduling the reservation")
    private BigDecimal value;

    @NotNull
    @ApiModelProperty(value = "The schedule id taken for the reservation")
    private Long scheduledId;

    @NotNull
    @ApiModelProperty(value = "The id of the guest who schedules the reservation")
    private Long guestId;
}
