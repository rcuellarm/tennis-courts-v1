package com.tenniscourts.reservations;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.guests.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = GuestService.class)
public class GuestServiceTest {

    @InjectMocks
    GuestService guestService;

    @Mock
    GuestRepository guestRepository;

    @Mock
    GuestMapper guestMapper;

    @Test
    public void getGuestsTest() {

        List<Guest> list = new ArrayList<Guest>();
        list.add(mock(Guest.class));
        list.add(mock(Guest.class));
        list.add(mock(Guest.class));

        when(guestRepository.findAll()).thenReturn(list);

        assertEquals(3, guestService.findGuests().size());
    }


    @Test
    public void createGuest() {
        Guest newGuest1 = new Guest();
        newGuest1.setName("Novak Djokovic");
        when(guestRepository.saveAndFlush(ArgumentMatchers.any(Guest.class))).thenReturn(newGuest1);
        Guest guest = guestRepository.saveAndFlush(newGuest1);

        assertThat(guest.getName(), equalTo(newGuest1.getName()));
        verify(guestRepository).saveAndFlush(newGuest1);
    }

    @Test
    public void deleteGuest() {
        Guest guest = new Guest();
        guest.setName("John McEnroe");
        guest.setId(1L);
        when(guestRepository.findById(guest.getId())).thenReturn(Optional.of(guest));
        String message = guestService.deleteGuest(guest.getId());
        verify(guestRepository).delete(guest);
        assertEquals(message,"Guest Deleted");
    }

    @Test
    public void findById() {
        Guest guest = new Guest();
        guest.setName("John McEnroe");
        guest.setId(1L);
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setName(guest.getName());
        guestDTO.setId(guest.getId());
        when(guestRepository.findById(guest.getId())).thenReturn(Optional.of(guest));
        when(guestMapper.map(guest)).thenReturn(guestDTO);
        GuestDTO expected = guestService.findGuest(guest.getId());
        verify(guestRepository).findById(guest.getId());
        assertEquals(expected.getName(), guest.getName());
    }


    @Test
    public void findByIdNotFound() {
        Guest guest = new Guest();
        guest.setId(89L);
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setName(guest.getName());
        guestDTO.setId(guest.getId());
        when(guestRepository.findById(guest.getId())).thenReturn(Optional.empty());
        Executable executable = () -> guestService.findGuest(guest.getId());
        assertThrows(EntityNotFoundException.class, executable, "Guest not found.");
        verify(guestRepository, atMost(1)).findById(89L);
    }

    @Test
    public void updateGuest() {
        Guest guest = new Guest();
        guest.setId(89L);
        guest.setName("Andre Agassi");
        Guest updatedGuest = new Guest();
        updatedGuest.setId(guest.getId());
        updatedGuest.setName("Pete Sampras");
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setName(updatedGuest.getName());
        guestDTO.setId(updatedGuest.getId());
        when(guestRepository.findById(guest.getId())).thenReturn(Optional.of(guest));
        when(guestRepository.save(updatedGuest)).thenReturn(updatedGuest);
        when(guestMapper.map(updatedGuest)).thenReturn(guestDTO);
        GuestDTO expected = guestService.updateGuest(guestDTO);
        verify(guestRepository).save(updatedGuest);
        verify(guestRepository).findById(guest.getId());
        assertEquals(expected.getName(), updatedGuest.getName());
    }


}
