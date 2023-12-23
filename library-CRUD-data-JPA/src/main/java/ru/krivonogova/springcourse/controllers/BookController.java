package ru.krivonogova.springcourse.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.krivonogova.springcourse.models.Book;
import ru.krivonogova.springcourse.models.Person;
import ru.krivonogova.springcourse.services.BooksService;
import ru.krivonogova.springcourse.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BookController {
	
//	private final BookDAO bookDAO;
//	private final PersonDAO personDAO;
	
	private final BooksService booksService;
    private final PeopleService peopleService;

	@Autowired
	public BookController(BooksService booksService, PeopleService peopleService) {
		this.booksService = booksService;
		this.peopleService = peopleService;
	}
	
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
    	model.addAttribute("book", booksService.findOne(id));
    	
    	Person bookOwner = booksService.getOwner(id);

        if (bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }
    
	@GetMapping("/new")
	public String newBook(@ModelAttribute("book") Book book) {
		return "books/new";
	}
	
    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, 
    					BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		return "books/new";
    	}
    	
    	booksService.save(book);
    	return "redirect:/books";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
    	model.addAttribute("book", booksService.findOne(id));
    	return "books/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, 
    		BindingResult bindingResult,
    		@PathVariable("id") int id) {
    	
    	if(bindingResult.hasErrors()) {
    		return "books/edit";
    	}
    	
    	booksService.update(id, book);
    	return "redirect:/books";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
    	booksService.delete(id);
    	return "redirect:/books";
    }
    
    // выдача книги
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
    	booksService.assign(id, selectedPerson);
    	
    	return "redirect:/books/" + id;
    }
    
    // возврат книги
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books/" + id;
    }
}


