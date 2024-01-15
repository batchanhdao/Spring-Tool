package Student;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("ses")
public class Contro {
	
	public boolean check = true;
	
	@Autowired
	private Repo repo;
	
	@ModelAttribute("ses")
	public Student ses() {
		return new Student();
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("department", Arrays.asList("a","ab","abc"));
		model.addAttribute("error",new Student());
		return "home";
	}
	@PostMapping("/add")
	public String add(Model model
			, @ModelAttribute("ses") Student ses
			, @RequestParam("department") String department) {
		ses.setDepartment(department);
		
		Student error = new Student();
		if(ses.getId().equals(null) || ses.getId().equals("")) {
			error.setId("id không để trống");
			check=false;
		}
		if(ses.getName().equals(null) || ses.getName().equals("")) {
			error.setName("name không để trống");
			check=false;
		}
		if(ses.getDob().equals(null) || ses.getDob().equals("")) {
			error.setDob("dob không để trống");
			check=false;
		}
		Student stu = (Student) repo.findById(ses.getId()).orElse(null);
		if(stu != null) {
			error.setId("id đã tồn tại");
			check=false;
		}
		if(check) {
			return "add";
		}
		model.addAttribute("department", Arrays.asList("a","ab","abc"));
		model.addAttribute("error",error);
		check=true;
		return "home";
	}
	@GetMapping("/confirm")
	public String Confirm(@ModelAttribute("ses") Student ses) {
		ses.setApproved(0);
		repo.save(ses);
		return "redirect:/";
	}
}
