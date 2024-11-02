package lab.edu.ve.ucab.Hotel;

public interface HotelService {

    // Colaboradores

    void addHotel(Integer hotelId, String hotelName);

    void setRoom(Integer hotelId, Integer number, RoomType roomType);

    Hotel findHotelBy(Integer hotelId);

}

