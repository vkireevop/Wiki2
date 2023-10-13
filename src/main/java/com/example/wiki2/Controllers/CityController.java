package com.example.wiki2.Controllers;

import com.example.wiki2.Models.City;
import com.example.wiki2.Services.WikipediaScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CityController {
    @Autowired
    private WikipediaScraper wikipediaScraper;

    @GetMapping("/cities")
    public @ResponseBody List<City> getCitiesData() {
        return wikipediaScraper.run();
    }
}
