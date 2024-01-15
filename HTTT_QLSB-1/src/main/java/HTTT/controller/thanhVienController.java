package HTTT.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import HTTT.entity.ThanhVien;
import HTTT.repository.thanhVienRepository;
import HTTT.service.thanhVienService;

@Controller
@RequestMapping("/thanh_vien")
public class thanhVienController {
	
	@Autowired
	private thanhVienRepository tvRep;
	
	@GetMapping("/login")
	public String loginGet() {
		return "login";
	}
	@PostMapping("/loginPost")
	public String loginPost(HttpSession tv, 
			@RequestParam("email") String email, 
			@RequestParam("password") String password) {
		System.out.println(email + " " + password);

		List<ThanhVien> user = (List<ThanhVien>) tvRep.findByEmail(email);
		if(user != null) {
			tv.setAttribute("user",user.get(0).getTentaikhoan());
			System.out.println("ten tai khoan: " + tv.getAttribute("user"));
			return "redirect:/";
		}
		
		return "redirect:/thanh_vien/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession tv) {
		tv.setAttribute("user", null);
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupGet(Model model) {
		model.addAttribute("thanhvien", new ThanhVien());
		return "signup";
	}
	
	@PostMapping("/signupPost")
	public String signupPost(ThanhVien thanhvien) {
		System.out.println(thanhvien);
		thanhvien.setVaitro("ROLE_NV");
		tvRep.save(thanhvien);
		return "redirect:/thanh_vien/login";
	}

}
