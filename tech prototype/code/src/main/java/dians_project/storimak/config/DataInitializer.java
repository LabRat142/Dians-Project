package dians_project.storimak.config;

import dians_project.storimak.model.Place;
import dians_project.storimak.service.PlaceService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer {
    private final PlaceService placeService;

    public DataInitializer(PlaceService placeService) {
        this.placeService = placeService;
    }
    @PostConstruct
    public void initData() throws IOException {
        List<Place> placeList = placeService.readJsonFromFile("static/base.json");
        placeList = new ArrayList<Place>(placeList.stream().collect(Collectors.toMap(Place::getName, p -> p, (prev, curr) -> curr)).values());
        for (int i = 0; i < placeList.size(); i++) {
            placeList.get(i).setID((long) i);
        }
        placeService.addAll(placeList);
    }
}
