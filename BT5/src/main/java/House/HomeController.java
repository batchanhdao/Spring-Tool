package House;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("ses")
public class HomeController {
	@Autowired
	private HouseRepository houseRep;
	
	@ModelAttribute("ses")
	public House ses() {
		return new House();
	}
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/viewhouses")
	public String view(Model model) {
		List<House> houses = (List<House>) houseRep.findAll();
		List<House> housesapp = new ArrayList<>();
		for (House house : houses) {
			if(house.getApproved()==0) {
				housesapp.add(house);
			}
		}
		model.addAttribute("houses", housesapp);
		return "viewhouses";
	}
	
	@GetMapping("/approve/{id}")
	public String approve(@PathVariable("id") String id) {
		House house = houseRep.findById(id).orElse(null);
		house.setApproved(1);
		houseRep.save(house);
		return "redirect:/viewhouses";
	}
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") String id) {
		House house = houseRep.findById(id).orElse(null);
		model.addAttribute("house",house);
		return "deletehouse";
	}
	
	@GetMapping("/confirmdelete/{id}")
	public String confirmdelete(@PathVariable("id") String id) {
		houseRep.deleteById(id);
		return "redirect:/viewhouses";
	}
	
}
