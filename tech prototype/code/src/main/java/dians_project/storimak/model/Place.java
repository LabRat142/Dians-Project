package dians_project.storimak.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

@Data
@AllArgsConstructor
public class Place {
    double lat;
    double lon;
    String name;
    String nameEnglish;
    String website;
    String description;
    String phone_number;
    String city;
    String opening_hours;
}
