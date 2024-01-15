package HTTT.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import HTTT.entity.MatHang;
import HTTT.repository.matHangRepository;

@Controller
@RequestMapping("/mat_hang")

public class matHangController {
	
	@Autowired
	private matHangRepository mhRep;

	@GetMapping("/listMH")
	public String listMHGet(Model model) {
		List<MatHang> mathangs = new ArrayList<MatHang>();
		mathangs = (List<MatHang>) mhRep.findAll();
		System.out.println(mathangs);
		model.addAttribute("mathangs", mathangs);
		return "qlmh";
	}
	
	@GetMapping("/editMH/{id}")
	public String editGet(Model model, @PathVariable("id") String id) {
		Long nid = Long.parseLong(id);
		MatHang mathang = (MatHang) mhRep.findById(nid).orElse(null);
		model.addAttribute("mathang", mathang);
		return "edit";
	}
	@GetMapping("/addMH")
	public String addMHGet(Model model) {
		model.addAttribute("mathang", new MatHang());
		return "add";
	}
	@PostMapping("/saveMH")
	public String savePost(MatHang mathang) {
		System.out.println(mathang);
		mhRep.save(mathang);
		return "redirect:/mat_hang/listMH";
	}
	
	@GetMapping("deleteMH/{id}")
	public String deleteGet(@PathVariable("id") String id) {
		Long nid = Long.parseLong(id);
		mhRep.deleteById(nid);
		return "redirect:/mat_hang/listMH";
	}
	
}
