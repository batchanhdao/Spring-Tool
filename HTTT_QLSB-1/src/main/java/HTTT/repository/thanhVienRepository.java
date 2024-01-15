package HTTT.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import HTTT.entity.ThanhVien;


public interface thanhVienRepository extends CrudRepository<ThanhVien, Long>{
	List<ThanhVien> findByEmail(String email);
	
}
