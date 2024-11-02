package lab.edu.ve.ucab.Hotel;

import java.time.LocalDate;

public interface BookingService {
    // Colaboradores

    Booking book(Integer employeeId, Integer hotelId, RoomType roomType, LocalDate checkIn, LocalDate checkOut);

}
}
