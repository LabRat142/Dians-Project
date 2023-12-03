package dians_project.storimak.service;

import dians_project.storimak.model.Place;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PlaceService {
    public List<Place> readJsonFromFile(String filePath) throws IOException;
    public List<Place> findAll();
}
