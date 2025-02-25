package mmh.jpamanytoone.controller;

import mmh.jpamanytoone.model.Kommune;
import mmh.jpamanytoone.repository.KommuneRepository;
import mmh.jpamanytoone.service.ApiServiceGetKommuner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("*") //Denne annotation er for at kunne arbejde med ekstern frontEnd
public class KommuneRestController {

    private ApiServiceGetKommuner apiServiceGetKommuner;
    private KommuneRepository kommuneRepository;

    public KommuneRestController(ApiServiceGetKommuner apiServiceGetKommuner, KommuneRepository kommuneRepository) {
        this.apiServiceGetKommuner = apiServiceGetKommuner;
        this.kommuneRepository = kommuneRepository;
    }

    //---------------

    @GetMapping("/getfromapikommuner")
    public List<Kommune> getKommuner() {
        return apiServiceGetKommuner.getKommuner();
    }

    //------ data fra API ------------

    @GetMapping("/kommuner")
    public List<Kommune> showAllKommuner() {
        return kommuneRepository.findAll();
    }

    @PostMapping("/kommuner")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune saveKommune(@RequestBody Kommune kommune) {
        return kommuneRepository.save(kommune);
    }

    @GetMapping("/kommuner/{kode}")
    public Kommune showSpecificKommune(@PathVariable String kode) {
        try {
            Optional<Kommune> kommuneToBeReturned = kommuneRepository.findById(kode);
            return kommuneToBeReturned.get();
        } catch (NoSuchElementException e) {
            Kommune noKommune = new Kommune();
            noKommune.setNavn("Kommune not found");
            return noKommune;
        }

    }

    @DeleteMapping("/kommuner/{kode}")
    public ResponseEntity<String> deleteRegion(@PathVariable String kode) {
        Optional<Kommune> orgKommune = kommuneRepository.findById(kode);
        if(orgKommune.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune not found");
        }
        kommuneRepository.deleteById(kode);
        return ResponseEntity.ok("Kommune deleted");
    }






}
