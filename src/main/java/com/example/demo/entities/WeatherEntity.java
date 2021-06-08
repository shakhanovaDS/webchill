package com.example.demo.entities;


import javax.persistence.*;

@Entity
@Table(name="WeatherEntity", uniqueConstraints = {@UniqueConstraint(columnNames = {"Country", "Town"})})
public class WeatherEntity {

    int weatherId;
    String country;
    String town;
    String main;
    String temp;
    String desc;
    Double windSpeed;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WeatherId")
    public int getWeatherId() { return weatherId; }
    public void setWeatherId(int weatherId) { this.weatherId = weatherId; }

    @Column(name = "Country", nullable = false)
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    @Column(name = "Town", nullable = false)
    public String getTown() { return town; }
    public void setTown(String town) { this.town = town; }

    @Column(name = "Main", nullable = false)
    public String getMain() { return main; }
    public void setMain(String main) { this.main = main; }

    @Column(name = "Temp", nullable = false)
    public String getTemp() { return temp; }
    public void setTemp(String temp) { this.temp = temp; }

    @Column(name = "Desc", nullable = false)
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    @Column(name = "WindSpeed", nullable = false)
    public Double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(Double windSpeed) { this.windSpeed = windSpeed; }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "weatherId=" + weatherId +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", main='" + main + '\'' +
                ", temp='" + temp + '\'' +
                ", desc='" + desc + '\'' +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
