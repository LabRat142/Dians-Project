package dians_project.storimak.web;

import dians_project.storimak.model.Place;
import dians_project.storimak.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/storimak")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping()
    public String homePage() {
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam(required = false) String search_str,@RequestParam(required = false) Long placeId, Model model){
        List<Place> places = placeService.findAll();
        if (search_str != null && !search_str.isEmpty() && !search_str.equals("null")){
            places = (List<Place>) places.stream()
                    .filter(place -> place.getName().contains(search_str) ||
                            place.getNameEnglish().contains(search_str) ||
                            place.getWebsite().contains(search_str) ||
                            place.getDescription().contains(search_str) ||
                            place.getPhone_number().contains(search_str) ||
                            place.getCity().contains(search_str))
                    .toList();
        }
        model.addAttribute("places",places);
        model.addAttribute("nearby",false);

        Place place = placeId == null ? null : placeService.findById(placeId);
        model.addAttribute("place",place);

        if (place == null){
            model.addAttribute("mapsrc", "https://maps.google.com/maps?z=15&output=embed");
        }
        else{
            model.addAttribute("mapsrc", "https://maps.google.com/maps?q=" + place.getLat() + "," + place.getLon() + "&z=15&output=embed");
        }

        model.addAttribute("searchstr", search_str);

        return "search";
    }

    @GetMapping("/nearsearch")
    public String nearSearch(@RequestParam(required = false) Double lat,@RequestParam(required = false) Double lon, @RequestParam(required = false) Long placeId, Model model){
        List<Place> places = placeService.findAll();
        if (lat != null && lon != null) {
            places = places.stream().sorted(Comparator.comparing(place -> {
                double deltaX = place.getLat() - lat;
                double deltaY = place.getLon() - lon;
                return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            })).toList();
        }
        model.addAttribute("places",places);
        model.addAttribute("nearby", true);
        model.addAttribute("lat",lat);
        model.addAttribute("lon",lon);

        Place place = placeId == null ? null : placeService.findById(placeId);
        model.addAttribute("place",place);
        if (place == null){
            model.addAttribute("mapsrc", "https://maps.google.com/maps?z=15&output=embed");
        }
        else{
            model.addAttribute("mapsrc", "https://maps.google.com/maps?q=" + place.getLat() + "," + place.getLon() + "&z=15&output=embed");
        }

        return "search";
    }

}
