package it.unibs.core;

import java.time.Clock;
import java.time.LocalDate;

/**
 * Interfaccia fornisce metodi per la verifica della validità
 * di un oggetto con scadenza o non sempre valido
 */
public interface Perishable {

    boolean isExpired(Clock clock);

    boolean isExpiredAtDate(LocalDate date);
}