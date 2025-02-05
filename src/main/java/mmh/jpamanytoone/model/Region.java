package mmh.jpamanytoone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {

    @Id
    @Column(length = 4)
    private String kode;
    @Column(unique = true)
    private String navn;
    private String href;

    //Nedenstående er egentligt ikke nødvendigt at have som attribut og kan være lidt farlig
    //hvis man har mange data (fx kunde der har rigtig mange ordre...)

    @OneToMany(mappedBy = "region")
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();
    //ovenstående bliver ikke en kolonne i tabellen, men fortæller at i klassen
    //Kommune findes der en Region attribut (foreignKey).
    //En Region kan have mange Kommuner, men den enkelte kommune er kun tilknyttet
    //én Region. Attributten er derfor et Set, så vi sikre at kombinationen af kommune og
    //region kun optræder én gang.
    //Region eksisterer først (Parent), og Kommune (Child) kan kun eksistere, hvis
    //den er tilknyttet en Region.


    public Region() {

    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Set<Kommune> getKommuner() {
        return kommuner;
    }

    public void setKommuner(Set<Kommune> kommuner) {
        this.kommuner = kommuner;
    }
}
