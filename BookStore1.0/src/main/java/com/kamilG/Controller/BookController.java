package com.kamilG.Controller;

import com.kamilG.model.Book;
import com.kamilG.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/book")
public class BookController {

  @Autowired private BookService bookService;

  @RequestMapping(path = "/add", method = RequestMethod.GET)
  public String addBook(Model model) {
    model.addAttribute("book", new Book());
    return "bookForm";
  }

  @RequestMapping(path = "/add", method = RequestMethod.POST)
  public String add(@ModelAttribute Book book) {
    this.bookService.saveOrUpdateBook(book);
    return "redirect:/main";
  }
}
