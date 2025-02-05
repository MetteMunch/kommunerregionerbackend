package mmh.jpamanytoone.service;

import mmh.jpamanytoone.model.Kommune;
import mmh.jpamanytoone.model.Region;
import mmh.jpamanytoone.repository.KommuneRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner{

    private RestTemplate restTemplate;
    //Vi skal bruge repository til at gemme kommunerne i vores database
    private KommuneRepository kommuneRepository;
    //Her angiver hvilken url, som vi vil læse fra
    private String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    public ApiServiceGetKommunerImpl(RestTemplate restTemplate,
                                     KommuneRepository kommuneRepository) {
        this.restTemplate = restTemplate;
        this.kommuneRepository = kommuneRepository;
    }

    public void saveKommuner(List<Kommune> kommuner) {
        for(Kommune k:kommuner) {
            kommuneRepository.save(k);
            //kommuneRepository.saveAll(kommuner);   Kan også skrives på denne måde
        }
    }

    @Override
    public List<Kommune> getKommuner() {
        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Kommune>>() {
                        });
        List<Kommune> kommuner = kommuneResponse.getBody();
        saveKommuner(kommuner);
        return kommuner;
    }
}
