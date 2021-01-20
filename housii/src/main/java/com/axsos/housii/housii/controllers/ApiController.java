package com.axsos.housii.housii.controllers;

import com.axsos.housii.housii.repositories.*;
import com.axsos.housii.housii.validator.UserValidator;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final RatingRepository ratingRepository;
    private final CategoryRepository categoryRepository;
    private final RoleRepository roleRepository;
    private final UserValidator userValidator;

    public ApiController(UserRepository userRepository, HouseRepository houseRepository, RatingRepository ratingRepository, CategoryRepository categoryRepository, RoleRepository roleRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
        this.ratingRepository = ratingRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
        this.userValidator = userValidator;
    }
}
