package dians_project.storimak.repository;

import com.fasterxml.jackson.core.type.TypeReference;
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

    public PlaceRepository() throws IOException {
        placeList = readJsonFromFile("C:\\Users\\Rat xd\\OneDrive\\Desktop\\UniOneDrive\\Semester 5\\Dians\\HWs\\Project\\hw 2\\code\\storimak\\src\\main\\resources\\static\\base.json");
    }
    @Override
    public List<Place> readJsonFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<List<Place>>() {});
        // Read JSON from file and convert to List<Place>
    }

    @Override
    public List<Place> findAll() {
        return placeList;
    }
}
