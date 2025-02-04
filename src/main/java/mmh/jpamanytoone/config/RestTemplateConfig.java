package mmh.jpamanytoone.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*Configuration annotationen angiver, at denne klasse er en konfigurationsklasse
i Spring, hvilket betyder, at den kan definere beans, som Spring kan administrere og
injicere i andre komponenter.
Så denne klasse sikrer, at vi har én instans af RestTemplate klar i Spring Containeren
når applikationen starter op, som kan injeceres de nødvendige steder (dependency injection).
 */


@Configuration
public class RestTemplateConfig {

    /*
    Denne klasse RestTemplateConfig er en konfigurationsklasse i Spring,
    der bruges til at oprette og konfigurere en instans af RestTemplate,
    som er en klient til at lave HTTP-anmodninger i Spring Boot-applikationer.
     */

    public RestTemplateConfig() {
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        System.out.println("Builder en rest");
        return builder.build();
    }
    /* Denne metode tager en RestTemplateBuilder som parameter. Den returnerer
    et RestTemplate-objekt ved at bruge builder.build(), hvilket skaber en ny
    instans af RestTemplate med standardkonfiguration.
     */

}