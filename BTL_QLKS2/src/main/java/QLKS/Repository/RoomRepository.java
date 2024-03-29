package QLKS.Repository;

import org.springframework.data.repository.CrudRepository;

import QLKS.Entity.Room;

//RoomRepository được sử dụng trong việc tương tác với cơ sở dữ liệu để lấy và lưu thông tin hóa đơn.
//kế thừa crudrepository thao tác CRUD (Create, Read, Update, Delete) với đối tượng Room.

//CrudRepository<Room, Long> RoomRepository sẽ hoạt động với các đối tượng Room và sử dụng kiểu dữ liệu Long 
//cho thuộc tính ID của Room.

public interface RoomRepository extends CrudRepository<Room, Long>{

}
