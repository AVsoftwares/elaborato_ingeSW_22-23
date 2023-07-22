package it.unibs.core;

import java.time.Clock;
import java.time.LocalDate;

/**
 * Interfaccia fornisce metodi per la verifica della validità
 * di un oggetto con scadenza o non sempre valido
 */
public interface Expire {

    boolean isValid(Clock clock);

    boolean isValidAtDate(LocalDate date);
}