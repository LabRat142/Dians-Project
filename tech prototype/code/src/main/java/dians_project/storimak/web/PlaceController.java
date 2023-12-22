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
    public String searchPage(@RequestParam(required = false) String search_str, Model model){
        List<Place> places = new ArrayList<Place>(placeService.findAll().stream()
                .collect(Collectors.toMap(Place::getName, p -> p, (prev, curr) -> curr))
                .values());
        if (search_str != null && !search_str.isEmpty()){
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
        return "search";
    }

}
