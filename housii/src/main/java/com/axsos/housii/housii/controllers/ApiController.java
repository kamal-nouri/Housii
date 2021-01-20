package com.axsos.housii.housii.controllers;

import com.axsos.housii.housii.services.HousiiService;
import com.axsos.housii.housii.validator.UserValidator;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final HousiiService housiiService;
    private final UserValidator userValidator;

    public ApiController(HousiiService housiiService, UserValidator userValidator) {
        this.housiiService = housiiService;
        this.userValidator = userValidator;
    }
}
