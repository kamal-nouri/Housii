package com.axsos.housii.housii.controllers;

import com.axsos.housii.housii.models.Category;
import com.axsos.housii.housii.models.House;
import com.axsos.housii.housii.models.User;
import com.axsos.housii.housii.services.HousiiService;
import com.axsos.housii.housii.validator.UserValidator;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HousiiController {
    private final HousiiService housiiService;
    private final UserValidator userValidator;

    public HousiiController(HousiiService housiiService, UserValidator userValidator) {
        this.housiiService = housiiService;
        this.userValidator = userValidator;
    }

    @RequestMapping("/")
    public String home(HttpSession session, Model model, @ModelAttribute("category") Category category, @ModelAttribute("house") House house) {
        List<Category> categories = housiiService.allCategories();
        model.addAttribute("categories", categories);
        return "home.jsp";
    }

    /////////////////////////////////////////////////////////////////
    //start of registration
    /////////////////////////////////////////////////////////////////
    @RequestMapping("/register")
    public String showRegistrationForm(@ModelAttribute("user") User user) {
        return "signup.jsp";
    }

    @RequestMapping(value = "/process_register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, Model model)
            throws UnsupportedEncodingException, MessagingException {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "signup.jsp";
        }
        housiiService.register(user, getSiteURL(request));
        model.addAttribute("message", "Please check your email to verify your account.");
        return "signin.jsp";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        return "signin.jsp";
    }

    @RequestMapping(value = "/loginuser", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if (housiiService.authenticateUser(user.getEmail(), user.getPassword())) {
            User user1 = housiiService.findByEmail(user.getEmail());
            if (user1.isEnabled()) {
                session.setAttribute("userId", user1.getId());
                return "redirect:/";
            } else {
                System.out.println("account is not verified");
                model.addAttribute("error", "account is not verified");
                return "signin.jsp";
            }
        } else {
            System.out.println("bad password");
            model.addAttribute("error", "Either password/email are incorrect");
            return "signin.jsp";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code, Model model, @ModelAttribute("user") User user) {
        if (housiiService.verify(code)) {
            model.addAttribute("message", "Congratulations, your account has been verified.");
        } else {
            model.addAttribute("message", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
        }
        return "signin.jsp";
    }

    /////////////////////////////////////////////////////////////////
    //end of registration
    /////////////////////////////////////////////////////////////////
    @RequestMapping("/{name}")
    public String categories(HttpSession session, Model model, @ModelAttribute("category") Category category, @PathVariable("name") String name) {
//        if (session.getAttribute("userId") == null) {
//            return "redirect:/login";
//        } else {
//            Long userId = (Long) session.getAttribute("userId");
//            User user = housiiService.findUser(userId);

//        List<House> houses=housiiService.allHousesByCategory(name);
        model.addAttribute("category", housiiService.findCategory(name));
        return "category.jsp";
    }

    @RequestMapping("/cat/{id}")
    public String ShowCat(@PathVariable("id") Long id, Model model, @ModelAttribute("house") House house, HttpSession session) {
        House house1 = housiiService.findHouse(id);
        System.out.println(house1.getId());
        model.addAttribute("house", house1);
        return "show.jsp";
    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<String> search(HttpServletRequest request) {
        return housiiService.search(request.getParameter("term"));
    }

    @PostMapping( value = "/searchLocation")
    public String searchByLocation(HttpSession session, Model model,@ModelAttribute("house") House house) {
        model.addAttribute("searchedHouses",housiiService.allHousesByLocation(house.getLocation()));
        return "search.jsp";
    }

    @RequestMapping(value = "/rent/{id}", method = RequestMethod.POST)
    public String addRent(Model model, HttpSession session,@RequestParam("date")String date, @PathVariable("id") Long id) throws ParseException {
        if(session.getAttribute("userId")==null){
            return "redirect:/login";
        }
        System.out.println(date);
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String[] sDate=date.split("-");
        System.out.println(date1);

        System.out.println(sDate[0]+ " "+sDate[1]+ " "+sDate[2]);
        House rented = housiiService.rentHouse((Long) session.getAttribute("userId"), id,date1);
        return "redirect:/cat/" + id;
    }

    @RequestMapping("/add")
    public String addHouse(@ModelAttribute("house")House house,Model model) {
        model.addAttribute("categories",housiiService.allCategories());
        return "addHouse.jsp";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addNewHouse(@Valid @ModelAttribute("house")House house,BindingResult result){
        if(result.hasErrors()){
            System.out.println(house.getLocation());
            System.out.println(result);
            return "addHouse.jsp";
        }
        else{
            housiiService.newHouse(house);
            return "redirect:/";
        }
    }
    @RequestMapping("/edit/{id}")
    public String editHouse(@PathVariable("id")Long id, @ModelAttribute("house")House house, Model model, HttpSession session){
        model.addAttribute("house", housiiService.findHouse(id));
        model.addAttribute("categories",housiiService.allCategories());
        System.out.println(housiiService.findHouse(id).getLocation());
        return "editHouse.jsp";
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.PUT)
    public String updateHouse(@PathVariable("id")Long id,@Valid @ModelAttribute("house")House house,BindingResult result){
        if(result.hasErrors()){
            return "editHouse.jsp";
        }else{
            housiiService.updateHousi(house);
            return "redirect:/";

        }

    }
    @RequestMapping("/profile")
    public String profile(Model model,HttpSession session){
        User user=housiiService.findUser((Long) session.getAttribute("userId"));
        List<House> rented=housiiService.rentedHousesForUser(user);
        model.addAttribute("rented",rented);
        model.addAttribute("user",user);
        return "profile.jsp";

    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
