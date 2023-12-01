package dians_project.storimak.model;

import lombok.Data;

import java.sql.Time;

@Data
public class Place {
    double lat;
    double lon;
    String name;
    String nameEnglish;
    String website;
    String description;
    String phone_number;
    String city;
    Time opening_hours;
}
