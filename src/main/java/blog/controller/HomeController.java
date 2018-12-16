package blog.controller;

import blog.model.Card;
import blog.service.CardService;
import blog.service.CreditService;
import blog.service.PaymentService;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	private UserService userService;
	private PaymentService paymentService;
	private CreditService creditService;
	private CardService cardService;

	@Autowired
	public HomeController(UserService userService, PaymentService paymentService, CreditService creditService, CardService cardService) {
		this.userService = userService;
		this.paymentService = paymentService;
		this.creditService = creditService;
		this.cardService = cardService;
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
		model.addAttribute("cards", cardService.findAll());
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

	@RequestMapping("/payments")
	public String payments(Model model) {
		return "payments";
	}

	@RequestMapping("/other-payment")
	public String otherPayment(Model model) {
		return "other-payment";
	}

	@RequestMapping(value = "/transfer/{id}", method = RequestMethod.GET)
	public ModelAndView transfer(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Card card = cardService.findById(id);
		modelAndView.addObject("card", card);
		modelAndView.setViewName("transfer");
		return modelAndView;
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String transfer(Model model) {
		Card card = cardService.findById(1);
		model.addAttribute("card", card);
		return "transfer";
	}

	@RequestMapping(value = "/transfer/complete", method = RequestMethod.GET)
	public ModelAndView complete(RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		String success = "Средства успешно переведены";
		redirectAttributes.addFlashAttribute("success", success);
		modelAndView.addObject("favourites", paymentService.findTop10ByFavouriteIsTrue());
		modelAndView.addObject("cards", cardService.findAll());
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
}
