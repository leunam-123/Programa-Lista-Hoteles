package lab.edu.ve.ucab.Policy;

public interface BookingPolicyService {
    // Colaboradores

    void setCompanyPolicy(Integer companyId, List<RoomType> roomTypes);

    void setEmployeePolicy(Integer employeeId, List<RoomType> roomTypes);

    boolean isBookingAllowed(Integer employeeId, RoomType roomType);

}
