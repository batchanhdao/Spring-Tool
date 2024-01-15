package HTTT.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "thanhvien")
public class ThanhVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tentaikhoan;
	private String diachi;
	@Column(nullable=false, unique=true)
	private String email;
	private String password;
	private String vaitro;
	private Date registeredAt;
	
	@PrePersist
	public void register() {
		this.registeredAt = new Date();
	}

	public void setup(ThanhVien account) {
		// TODO Auto-generated method stub
		this.tentaikhoan=account.tentaikhoan;
		this.diachi=account.diachi;
		this.email=account.email;
		this.password=account.password;
		this.vaitro=account.vaitro;
		this.registeredAt = account.getRegisteredAt();
	}
}

