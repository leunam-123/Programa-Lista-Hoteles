package lab.edu.ve.ucab.Hotel;
import java.util.List;

public class Hotel {
    private int hotelId;
    private int number;
    private RoomType roomType;
    private String hotelName;

    public Hotel (int hotelId, int number, RoomType roomType, String hotelName){
        this.hotelId=hotelId;
        this.number=number;
        this.roomType=roomType;
        this.hotelName=hotelName;
    }

    public Hotel(int hotelId, String hotelName) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
    }

    public int gethotelId() {
        return hotelId;
    }
}

