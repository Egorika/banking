package blog.controller;

import blog.model.Payment;
import blog.service.CreditService;
import blog.service.PaymentService;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

	private UserService userService;
	private PaymentService paymentService;
	private CreditService creditService;

	@Autowired
	public HomeController(UserService userService, PaymentService paymentService, CreditService creditService) {
		this.userService = userService;
		this.paymentService = paymentService;
		this.creditService = creditService;
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("favourites", paymentService.findTop10ByFavouriteIsTrue());
		return "index";
	}

	@RequestMapping("/credit")
	public String credit(Model model) {
		model.addAttribute("credits", creditService.findAll());
		return "credit";
	}

	@RequestMapping("/settings")
	public String settings(Model model) {
		return "settings";
	}
}
