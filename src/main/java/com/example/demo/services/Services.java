package com.example.demo.services;

import com.example.demo.entities.WeatherEntity;
import com.example.demo.utils.Connection;
import com.jayway.jsonpath.JsonPath;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class Services {
    public void addWeather(WeatherEntity weather) throws Exception {
        System.out.println(weather.toString());
        Session session = Connection.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(weather);
        tx.commit();
        session.close();
    }

    public List<WeatherEntity> allWeather() throws Exception {
        return Connection.getSessionFactory().openSession().createQuery("from WeatherEntity ").list();
    }
    public WeatherEntity getWeather(String country, String city){
        WeatherEntity weather = new WeatherEntity();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q="+city+"%2C"+country+"&lat=0&lon=0&callback=test&id=2172797&lang=null&units=%22metric%22%20or%20%22imperial%22&mode=xml%2C%20html"))
                .header("x-rapidapi-key", "28b2cc1257msh34f780462e6eb49p1bb4bejsn2e00aafe8cf1")
                .header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body().substring(5,response.body().length()-1);
            weather.setMain(JsonPath.parse(json).read("$.weather[0].main").toString());
            weather.setDesc(JsonPath.parse(json).read("$.weather[0].description").toString());
            weather.setTemp(JsonPath.parse(json).read("$.main.temp").toString());
            weather.setWindSpeed(JsonPath.parse(json).read("$.wind.speed"));
            weather.setCountry(country);
            weather.setTown(city);
            return weather;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return weather;
    }
}
