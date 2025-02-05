package mmh.jpamanytoone.model;

import jakarta.persistence.*;

@Entity
public class Kommune {

    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;

    @ManyToOne
    @JoinColumn(name = "regionfk",referencedColumnName = "kode", nullable = false) //nullable = false skal indsættes,
    // hvis vi vil sikre, at en kommune ikke kan gemmes medmindre der er tilknyttet en Region
    private Region region;

    public Kommune() {
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
