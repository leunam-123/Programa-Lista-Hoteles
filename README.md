[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/WNsIxqQJ)

## Introducción

Construir un motor de reservas de hotel corporativo. Este motor debe satisfacer las necesidades de tres tipos diferentes de actores:

- **Gerente del Hotel**: Configurar todos los diferentes tipos de habitaciones y sus respectivas cantidades para su hotel.
- **Administrador de la Empresa**: Agregar/eliminar empleados y también crear políticas de reserva para su empresa y empleados.
- **Empleado**: Reservar una habitación de hotel.

Para lograr esto, el motor necesita proporcionar cuatro servicios principales que trabajen en estrecha colaboración entre sí.

Los cuatro servicios se describen a continuación. 

### Servicio de Hotel
Utilizado por el gerente del hotel para definir los tipos y el número de habitaciones de cada tipo que tiene el hotel. También puede devolver información del hotel dado un ID de hotel.

```java
public interface HotelService {

  // Colaboradores

  void addHotel(Integer hotelId, String hotelName);

  void setRoom(Integer hotelId, Integer number, RoomType roomType);

  Hotel findHotelBy(Integer hotelId);

}
```

#### Reglas

- El método `addHotel(...)` debe lanzar una excepción cuando el ID del hotel ya exista o crear el hotel de lo contrario.
- El método `setRoom(...)` debe lanzar una excepción si el hotel no existe. Debe insertar o actualizar una habitación según su número de habitación.
- El método `findHotelBy(Integer hotelId)` debe devolver toda la información sobre el hotel.

### Servicio de Empresa
Permite a los administradores de empresas agregar y eliminar empleados.

```java
public interface CompanyService {

  // Colaboradores

  void addEmployee(Integer companyId, Integer employeeId);

  void deleteEmployee(Integer employeeId);

  boolean hasEmployee(Integer employeeId);

}
```

#### Reglas

- Los empleados no deben duplicarse.
- Al eliminar un empleado, todas las reservas y políticas asociadas al empleado también deben ser eliminadas del sistema.

### Servicio de Política de Reservas
Permite a los administradores de empresas crear políticas de reserva para su empresa y empleados. Las políticas de reserva determinan si una solicitud de reserva de un empleado es permitida por su empresa. Hay dos tipos de políticas de reserva:

- **Política de Reserva de la Empresa**: Indica qué tipos de habitaciones pueden reservarse. Por ejemplo, una empresa puede permitir solo habitaciones estándar (individuales/dobles) o puede permitir habitaciones estándar y suites junior.
- **Política de Reserva de Empleados**: Indica qué tipos de habitaciones puede reservar un empleado específico. Por ejemplo, un empleado podría tener permitido reservar solo una habitación estándar mientras que otro empleado podría tener permitido reservar habitaciones estándar, junior suite y master suite.

```java
public interface BookingPolicyService {

  // Colaboradores

  void setCompanyPolicy(Integer companyId, List<RoomType> roomTypes);

  void setEmployeePolicy(Integer employeeId, List<RoomType> roomTypes);

  boolean isBookingAllowed(Integer employeeId, RoomType roomType);

}
```

#### Reglas de Negocio

- Las políticas de empleados tienen precedencia sobre las políticas de la empresa. Si existe una política para un empleado, se debe respetar independientemente de lo que diga la política de la empresa (si la hay).
- Si no existe una política de empleado, se debe revisar la política de la empresa.
- Si no existen políticas ni de empleado ni de empresa, el empleado debe tener permitido reservar cualquier habitación.

#### Reglas Técnicas

- Los métodos `setCompanyPolicy(...)` y `setEmployeePolicy(...)` deben crear una nueva política o actualizar una existente. No se permiten políticas duplicadas de empresa o empleado.
- El método `isBookingAllowed(...)` debe tener en cuenta al empleado y la empresa para la que trabaja el empleado.

### Servicio de Reservas
Permite a los empleados reservar habitaciones en hoteles.

```java
public interface BookingService {

  // Colaboradores

  Booking book(Integer employeeId, Integer hotelId, RoomType roomType, LocalDate checkIn, LocalDate checkOut);

}
```

#### Reglas

- La reserva debe contener un ID único, employeeId, hotelId, roomType, checkIn y checkOut.
- La fecha de salida debe ser al menos un día después de la fecha de entrada.
- Validar si el hotel existe y si el tipo de habitación es proporcionado por el hotel.
- Verificar si la reserva está permitida según las políticas de reserva definidas, si las hay. Ver Servicio de Política de Reservas para más detalles.
- La reserva solo debe permitirse si hay al menos un tipo de habitación disponible durante todo el período de reserva.
- Hacer un seguimiento de todas las reservas. Por ejemplo, si el hotel tiene 5 habitaciones estándar, no debe haber más de 5 reservas en el mismo día.
- Las habitaciones del hotel pueden ser reservadas muchas veces siempre y cuando no haya conflictos con las fechas.
- Devolver confirmación de la reserva al empleado o error de lo contrario (también se pueden usar excepciones).

### Directrices de Diseño

- La interfaz pública de los servicios no puede cambiarse, excepto sus constructores para inyectar colaboradores.
- Los servicios no deben tener dependencias circulares, es decir, si el servicio A usa B, el servicio B no puede usar el servicio A.
- Los datos persistentes deben almacenarse en un repositorio (en memoria).
- Crear una buena estructura de paquetes, respetando los diferentes usuarios y áreas de la aplicación. Ejem:
  - ve.edu.ucab.lab.company
  - ve.edu.ucab.lab.hotel
  - ve.edu.ucab.lab.policy
- Las implementaciones de las interfaces deben llamarse igual que la interfaz pero con el prefijo `InMemory`. Ejem: `InMemoryBookingService`, `InMemoryCompanyService`, etc.
- Para las excepciones utilizar la exception de Java `IllegalArgumentException`. No es necesario crear excepciones personalizadas.