package it.unibs.core;

import java.time.LocalDate;

public interface Expire {

    boolean isExpired();

    boolean isExpiredAtDate(LocalDate date);
}