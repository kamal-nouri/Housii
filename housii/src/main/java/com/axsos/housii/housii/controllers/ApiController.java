package com.axsos.housii.housii.controllers;

import com.axsos.housii.housii.models.House;
import com.axsos.housii.housii.models.User;
import com.axsos.housii.housii.services.HousiiService;
import com.axsos.housii.housii.validator.UserValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    private final HousiiService housiiService;
    private final UserValidator userValidator;

    public ApiController(HousiiService housiiService, UserValidator userValidator) {
        this.housiiService = housiiService;
        this.userValidator = userValidator;
    }
    @RequestMapping("/api/users")
    public List<User> indexUser() {
        return housiiService.allUsers();
    }
    @RequestMapping(value="/api/users", method= RequestMethod.POST)
    public User createUser(@RequestParam(value="name") String name, @RequestParam(value="email") String email, @RequestParam(value="password") String password) {
        User user = new User(name, email, password);
        return housiiService.createUser(user);
    }
    @RequestMapping("/api/houses")
    public List<House> indexHouse() {
        return housiiService.allHouses();
    }
    @RequestMapping(value="/api/houses", method= RequestMethod.POST)
    public House createHouse(@RequestParam(value="name") String name, @RequestParam(value="location") String location, @RequestParam(value="description") String description,@RequestParam(value="price") double price) {
        House house = new House(name, location, description,price);
        return housiiService.createHouse(house);
    }

}
