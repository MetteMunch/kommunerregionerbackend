package mmh.jpamanytoone.service;

import mmh.jpamanytoone.model.Kommune;

import java.util.List;

public interface ApiServiceGetKommuner {
    List<Kommune> getKommuner();   //Metode signatur, som sikrer at denne metode skal indarbejdes i impl klasse
}
