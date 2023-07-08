package it.unibs.core;

import java.time.LocalDate;

public interface Expire {

    boolean isValid();

    boolean isValidAtDate(LocalDate date);
}