package mmh.jpamanytoone.service;

import mmh.jpamanytoone.model.Region;
import mmh.jpamanytoone.repository.KommuneRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetRegioner{

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

    @Override
    public List<Region> getRegioner() {
        return List.of();
    }
}
