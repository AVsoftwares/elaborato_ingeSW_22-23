package it.unibs.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    private int seats = 0;
    /**
     * Map che associa il nome della bevanda al corrispettivo ammontare di consumo tipico procapite
     */
    private final Map<String, Integer> avgDrinkAmount = new HashMap<>();
    /**
     * Map che associa il nome del genere extra al corrispettivo ammontare di consumo tipico procapite
     */
    private final Map<String, Integer> avgExtraAmount = new HashMap<>();
    private int individualWorkload;
    private final String name;
    private static final float SUSTAINABLE_WORKLOAD_MULTIPLIER = 1.2f;

    public Restaurant(File file) throws IOException {
        // TODO questa è solo un'idea

        //oppure ServizioFile.caricaSingoloOggetto(File configData);?
        System.getProperties().load(new FileInputStream(file));
        this.name = System.getProperty("name");
    }

    private float getSustainableWorkload() {
        return (individualWorkload * seats) * SUSTAINABLE_WORKLOAD_MULTIPLIER;
    }

    public void showDataConfig() {
    }
}
