package dians_project.storimak.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dians_project.storimak.model.Place;
import dians_project.storimak.repository.PlaceRepository;
import dians_project.storimak.service.PlaceService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<Place> readJsonFromFile(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Place>>() {});
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Place> addAll(List<Place> list) {
        placeRepository.saveAll(list);
        return placeRepository.findAll();
    }
}
