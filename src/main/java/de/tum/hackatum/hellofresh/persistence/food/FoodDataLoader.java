package de.tum.hackatum.hellofresh.persistence.food;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FoodDataLoader {


    /*
        TODO Batuhan. Füge die JSON-Datei in die Datenbank hinzu. Hierfür das JSON auslesen und dann
        in die Datenbank mithilfe von save des Repositories. siehe ordner
     */

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        // add here ...
    }

}
