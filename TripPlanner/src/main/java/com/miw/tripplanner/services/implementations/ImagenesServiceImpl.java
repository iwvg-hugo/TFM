package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.services.ImagenesService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class ImagenesServiceImpl implements ImagenesService {

    @Override
    public String getRandomImage(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.pexels.com/v1/search?query=" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "REZYg1xZfHSh78RBxcVDociICJcez2sLptRRwL0VHii7Ez5JeEidl37r");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Parsear el cuerpo de la respuesta JSON
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray photos = jsonResponse.getJSONArray("photos");

        if (photos.isEmpty()) {
            return "{\"error\": \"No photos found\"}";
        }

        // Seleccionar una foto aleatoria
        Random random = new Random();
        JSONObject randomPhoto = photos.getJSONObject(random.nextInt(photos.length()));
        String imageUrl = randomPhoto.getJSONObject("src").getString("large");

        return "{\"imageUrl\": \"" + imageUrl + "\"}";
    }
}