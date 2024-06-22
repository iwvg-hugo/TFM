package com.miw.tripplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.miw.tripplanner.services.ImagenesService;

@RestController
@RequestMapping("/imagenes")
public class ImageApiController {

    @Autowired
    ImagenesService imagenesService;

    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRandomImage(@PathVariable String id) {
        return imagenesService.getRandomImage(id);
    }
}
