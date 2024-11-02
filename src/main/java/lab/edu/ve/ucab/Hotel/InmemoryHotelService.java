package lab.edu.ve.ucab.Hotel;


import java.util.List;

public class InmemoryHotelService implements HotelService {
    private int hotelId;
    private int number;
    private RoomType roomType;
    private String hotelName;
    List<Hotel> hotelList;


    public InmemoryHotelService(int hotelId,int number, RoomType roomType, String hotelName){
        this.hotelId=hotelId;
        this.number=number;
        this.roomType=roomType;
        this.hotelName=hotelName;
    }

    public List<Hotel> getHotelId(int hotelId) {
        return hotelList;
    }

    @Override
    public void addHotel(Integer hotelId, String hotelName) {
        hotelList.stream().filter(hotel -> hotel.gethotelId() == hotelId).findFirst().ifPresent(hotel -> {
            throw new IllegalArgumentException();
        });

        hotelList.add(new Hotel(hotelId, hotelName));
    }

    @Override
    public void setRoom(Integer hotelId, Integer number, RoomType roomType) {
        System.out.println("El Id del hotel es: "+hotelId);
        System.out.println(" ");
        System.out.println("El número de habitaciones es: "+number);
        System.out.println(" ");
        System.out.println("El tipo de habitación es: "+roomType);
    }

    @Override
    public Hotel findHotelBy(Integer hotelId) {
        return null;
    }
}
