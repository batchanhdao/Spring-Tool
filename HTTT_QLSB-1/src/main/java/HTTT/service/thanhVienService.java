package HTTT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HTTT.entity.ThanhVien;
import HTTT.repository.thanhVienRepository;

@Service
public class thanhVienService {
	@Autowired // tự động tiêm các đối tượng để sử dụng các phương thức và thuộc tính
	private thanhVienRepository userRepo;
	
	public boolean CheckLogin(List<ThanhVien> account, String email, String password) {
		if(account == null) {
			return false;
		}
		if(account.get(0).equals(password) == false) {
			return false;
		}
		
		return true;
	}

}
