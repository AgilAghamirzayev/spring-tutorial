package app.controller;

import app.session.CustomerDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/")
@SessionAttributes(
        names = {CustomerDetails.ATTR}, // cd
        types = {CustomerDetails.class})
public class BookingController {

    private static String ftm(String f, Object... as) {
        return String.format(f, as);
    }

    @ModelAttribute(CustomerDetails.ATTR)
    public CustomerDetails create() {
        return new CustomerDetails();
    }

    /**
     * main resource
     * http://localhost:8081/booking
     */

    @GetMapping("booking")
    public String handle_booking_get(
            @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
            Model m) {
        log.info(ftm("GET -> /booking: %s", ""));
        log.info(cd);
        m.addAttribute("seat", cd.getSeat());
        return "1booking";
    }

    @PostMapping("booking")
    public RedirectView handle_booking_post(
            @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
    ) {
        log.info(cd);
        return new RedirectView("customer");
    }

    @GetMapping("customer")
    public String handle_customer_get(
            @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
            Model m) {
        m.addAttribute("firstname", cd.getFirstname());
        m.addAttribute("lastname", cd.getLastname());
        return "2customer";
    }

    @PostMapping("customer")
    public RedirectView handle_customer_post(
            @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd

    ) {
        log.info(ftm("POST -> /customer: %s", cd.getFirstname(), cd.getLastname()));
        log.info(cd);
        return new RedirectView("payment");
    }

    @GetMapping("payment")
    public String handle_payment_get(
            @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
            Model m) {
        m.addAttribute("cardno", cd.getCardno());
        return "3payment";
    }

    @PostMapping("payment")
    public RedirectView handle_payment_post(
            @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
    ) {
        log.info(ftm("POST -> /payment: %s", cd.getCardno()));
        log.info(cd);
        return new RedirectView("confirm");
    }

    @GetMapping("confirm")
    public String handle_confirm_get(
            @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
            Model model) {
        model.addAttribute("seat", cd.getSeat());
        model.addAttribute("firstname", cd.getFirstname());
        model.addAttribute("lastname", cd.getLastname());
        model.addAttribute("cardno", cd.getCardno());
        return "4confirm";
    }



}