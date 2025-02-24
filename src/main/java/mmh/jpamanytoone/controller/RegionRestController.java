package mmh.jpamanytoone.controller;

import mmh.jpamanytoone.model.Kommune;
import mmh.jpamanytoone.model.Region;
import mmh.jpamanytoone.repository.RegionRepository;
import mmh.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("*") //Denne annotation er for at kunne arbejde med ekstern frontEnd
public class RegionRestController {

    private ApiServiceGetRegioner apiServiceGetRegioner;
    private RegionRepository regionRepository;



    public RegionRestController(ApiServiceGetRegioner apiServiceGetRegioner, RegionRepository regionRepository) {
        this.apiServiceGetRegioner = apiServiceGetRegioner;
        this.regionRepository = regionRepository;
    }

    //------ data fra API -----

    @GetMapping("/getfromapiregioner")
    public List<Region> getRegioner() {
        return apiServiceGetRegioner.getRegioner();
    }

    //-------------------------

    @GetMapping("/regioner")
    public List<Region> showAllRegioner() {
        return regionRepository.findAll();
    }

    @PostMapping("/regioner")
    @ResponseStatus(HttpStatus.CREATED)
    public Region saveRegion(@RequestBody Region region) {
        return regionRepository.save(region);
    }

    @GetMapping("/regioner/{kode}")
    public Region showSpecificRegion(@PathVariable String kode) {
        try {
            Optional<Region> regionToBeReturned = regionRepository.findById(kode);
            return regionToBeReturned.get();
        } catch (NoSuchElementException e) {
            Region noRegion = new Region();
            noRegion.setNavn("Region not found");
            return noRegion;
        }

    }



    @DeleteMapping("/regioner/{kode}")
    public ResponseEntity<String> deleteRegion(@PathVariable String kode) {
        Optional<Region> orgRegion = regionRepository.findById(kode);
        if(orgRegion.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        }
        try {
            regionRepository.deleteById(kode);
            return ResponseEntity.ok("Region deleted");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parent cannot be deleted");
        }
    }

    //___ træning med forms i projekt JsRegionerForm

    @PostMapping("/region")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        System.out.println(region);
        return regionRepository.save(region);
    }

    @PostMapping("/region2")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> postRegion2(@RequestParam String kode, @RequestParam String navn, @RequestParam String href) {
        return ResponseEntity.ok("Received kode: " + kode + ", navn: " + navn);
    }



}
