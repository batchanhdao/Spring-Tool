package QLS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("BookInfo")
public class HomeController {
	@Autowired
    private BookRepository bookRepo;

    @ModelAttribute("BookInfo")
    public Book book() {
        return new Book();
    }
    
    @GetMapping("/")
	public String home(Model model, @ModelAttribute("BookInfo") Book bookInfo) {
		model.addAttribute("bookSearch", bookInfo);
		return "home";
	}

	@PostMapping("/search")
	public String search(Model model, @ModelAttribute("bookSearch") Book bookSearch, 
			@ModelAttribute("BookInfo") Book bookInfo) {
		bookInfo.setBookcode(bookSearch.getBookcode());
		bookInfo.setTitle(bookSearch.getTitle());
		bookInfo.setAuthor(bookSearch.getAuthor());
		bookInfo.setCategory(bookSearch.getCategory());
		
		List<Book> books = new ArrayList<Book>();
		List<Book> allBook = (List<Book>) bookRepo.findAll();
		for(Book b:allBook) {
			if(b.getBookcode().equals(bookSearch.getBookcode())==true 
					|| b.getTitle().equals(bookSearch.getTitle())==true
					|| b.getAuthor().equals(bookSearch.getAuthor())==true
					|| b.getCategory().equals(bookSearch.getCategory())==true) {
				books.add(b);
			}
		}
		model.addAttribute("books", books);
		return "booklist";
	}
	@GetMapping("/booked/{bookcode}")
	public String Booked(@PathVariable("bookcode") String bookcode) {
		Book book = bookRepo.findById(bookcode).orElse(null);
		book.setBooked(1);
		bookRepo.save(book);
		return "redirect:/";
	}
	@GetMapping("/cancelbook/{bookcode}")
	public String CancelBook(@PathVariable("bookcode") String bookcode) {
		Book book = bookRepo.findById(bookcode).orElse(null);
		book.setBooked(0);
		bookRepo.save(book);
		return "redirect:/";
	}
	
}
