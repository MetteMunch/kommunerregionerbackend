package mmh.jpamanytoone.service;

import mmh.jpamanytoone.model.Region;
import mmh.jpamanytoone.repository.RegionRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    private RestTemplate restTemplate;
    //Vi skal bruge repository til at gemme regionerne i vores egen database
    private RegionRepository regionRepository;
    //Her angiver hvilken url vi vil læse fra
    private String regionUrl = "https://api.dataforsyningen.dk/regioner";


    public ApiServiceGetRegionerImpl(RestTemplate restTemplate, RegionRepository regionRepository) {
        this.restTemplate = restTemplate;
        this.regionRepository = regionRepository;
    }

    public void saveRegioner(List<Region> regioner) {
        for (Region r : regioner) {
            regionRepository.save(r);
        }
        //eller Lambda regioner.forEach(reg -> regionRepository.save(reg));
    }

    @Override
    public List<Region> getRegioner() {
        ResponseEntity<List<Region>> regionResponse =
                restTemplate.exchange(regionUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Region>>() {
                    //new Para... viser at den skal tage JSON og lave om til Array med Regioner
                        });
        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
        return regioner;
    }
    /*
    Forklaring til ovenstående...
    ResponseEntity<List<Region>> , er det object som restTemplate.exchange() giver tilbage.
    restTemplate.exchange() tager følgende parametre:
    regionUrl, hvor skal den gå ud og læse fra.
    HttpMethod.GET, hvilket http verbum skal den bruge
    new ParameterizedTypeReference<List<Region>>(), betyder vores JSon starter med et array tegn ([),
    og vi får et array af regioner.
     */
}
