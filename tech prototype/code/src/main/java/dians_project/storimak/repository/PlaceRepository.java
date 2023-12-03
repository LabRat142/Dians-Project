package dians_project.storimak.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import dians_project.storimak.model.Place;
import dians_project.storimak.service.PlaceService;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PlaceRepository implements PlaceService {
    public static List<Place> placeList = new ArrayList<>();

    @Override
    public List<Place> readJsonFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        // Use Jackson to read JSON from file and map it to a list of Place objects
        return Arrays.asList(objectMapper.readValue(file, Place[].class));
    }
}
