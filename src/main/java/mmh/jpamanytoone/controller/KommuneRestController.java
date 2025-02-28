package mmh.jpamanytoone.controller;

import mmh.jpamanytoone.model.Kommune;
import mmh.jpamanytoone.repository.KommuneRepository;
import mmh.jpamanytoone.service.ApiServiceGetKommuner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    //--------- data fra API ------------

    @GetMapping("/getfromapikommuner")
    public List<Kommune> getKommuner() {
        return apiServiceGetKommuner.getKommuner();
    }

    //------

    @GetMapping("/kommuner")
    public List<Kommune> showAllKommuner() {
        return kommuneRepository.findAll();
    }

//    @PostMapping("/kommuner")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Kommune saveKommune(@RequestBody Kommune kommune) {
//        return kommuneRepository.save(kommune);
//    }

    @PostMapping("/kommuner")
    public ResponseEntity<Kommune> saveKommune(@RequestBody Kommune kommune) {
        try {
            Kommune savedKommune = kommuneRepository.save(kommune);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedKommune);
            //Hvis save går godt får klienten ok tilbage sammen med den gemte kommune objekt

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Kunne ikke oprette kommune", e);
        }

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

    @PutMapping("kommuner/{kode}")
    public ResponseEntity<Kommune> updateKommune(@PathVariable String kode, @RequestBody Kommune kommuneDetails) {
        Kommune kommuneToBeChanged = kommuneRepository.findById(kode).orElse(null);

        if (kommuneToBeChanged == null) {
            return ResponseEntity.notFound().build(); // Returnerer 404 hvis ikke fundet
        }

        kommuneToBeChanged.setNavn(kommuneDetails.getNavn());
        kommuneToBeChanged.setHref(kommuneDetails.getHref());
        kommuneToBeChanged.setRegion(kommuneDetails.getRegion());
        Kommune updatedKommune = kommuneRepository.save(kommuneToBeChanged);

        return ResponseEntity.ok(updatedKommune);
    }

    @DeleteMapping("/kommuner/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {
        Optional<Kommune> orgKommune = kommuneRepository.findById(kode);
        if (orgKommune.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune not found");
        }
        kommuneRepository.deleteById(kode);
        return ResponseEntity.ok("Kommune deleted");
    }


}
