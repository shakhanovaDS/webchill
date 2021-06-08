package com.example.demo.controllers;

import com.example.demo.entities.WeatherEntity;
import com.example.demo.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class Controllers {

    private Services service;
    private WeatherEntity weatherEntity;
    @Autowired
    public void setService(Services service) { this.service = service; }

    private static void setModel(Model model, WeatherEntity weather){
        model.addAttribute("country", weather.getCountry());
        model.addAttribute("town", weather.getTown());
        model.addAttribute("speed", weather.getWindSpeed());
        model.addAttribute("main", weather.getMain());
        model.addAttribute("desc", weather.getDesc());
        model.addAttribute("temp", weather.getTemp());
    }

    @RequestMapping(value = "/getWeather", method = RequestMethod.GET)
    public String findWeather(Model model, @RequestParam(value = "town") String town, @RequestParam(value = "country") String country){
        WeatherEntity weatherEntity = service.getWeather(country, town);
        setModel(model, weatherEntity);
        this.weatherEntity = weatherEntity;
        System.out.println(weatherEntity.toString());
        return "weather.html";
    }

    @RequestMapping(value="/addWeather")
    @ResponseBody
    public String getWeather(Model model, @ModelAttribute("weather") WeatherEntity weather) throws Exception {
        service.addWeather(weatherEntity);
        return "Added";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(Model model) throws Exception {
        model.addAttribute("list", service.allWeather());
        return "addedWeather.html";
    }

}
