package lab.edu.ve.ucab.Company;

public interface CompanyService {
    // Colaboradores

    void addEmployee(Integer companyId, Integer employeeId);

    void deleteEmployee(Integer employeeId);

    boolean hasEmployee(Integer employeeId);

}

