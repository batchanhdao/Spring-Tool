package HTTT.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import HTTT.entity.ThanhVien;
import HTTT.repository.thanhVienRepository;

@Controller

public class home {
	
	@GetMapping("/") 
	public String home(Model model, HttpSession tv) {
		model.addAttribute("user", tv.getAttribute("user"));
		return "home";
	}

}
