package dians_project.storimak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

@Data
@AllArgsConstructor
public class Place {
    double lat;
    double lon;
    String name;
    @JsonProperty("name:en")
    String nameEnglish;
    String website;
    String description;
    @JsonProperty("contact:phone")
    String phone_number;
    @JsonProperty("addr:city")
    String city;
    String opening_hours;

    public Place() {
        name="";nameEnglish="";website="";description="";phone_number="";city="";opening_hours="";
    }
}
