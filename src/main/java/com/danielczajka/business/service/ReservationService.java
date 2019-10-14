package com.danielczajka.business.service;

import com.danielczajka.business.service.domain.RoomReservation;
import com.danielczajka.entity.Reservation;
import com.danielczajka.entity.Room;
import com.danielczajka.entity.User;
import com.danielczajka.repository.ReservationRepository;
import com.danielczajka.repository.RoomRepository;
import com.danielczajka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if (null!=reservations) {
            reservations.forEach(reservation -> {
                Optional<User> userResponse = this.userRepository.findById(reservation.getUserId());
                if(userResponse.isPresent()){
                    User user = userResponse.get();
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(user.getFirstName());
                    roomReservation.setLastName(user.getLastName());
                    roomReservation.setUserId(user.getId());
                }
            });

        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;

    };

}
