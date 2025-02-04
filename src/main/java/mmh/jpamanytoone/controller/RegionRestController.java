package mmh.jpamanytoone.controller;

import mmh.jpamanytoone.model.Region;
import mmh.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestController {

    private ApiServiceGetRegioner apiServiceGetRegioner;



    public RegionRestController(ApiServiceGetRegioner apiServiceGetRegioner) {
        this.apiServiceGetRegioner = apiServiceGetRegioner;
    }

    @GetMapping("/regioner")
    public List<Region> getRegioner() {
        return apiServiceGetRegioner.getRegioner();
    }
}
