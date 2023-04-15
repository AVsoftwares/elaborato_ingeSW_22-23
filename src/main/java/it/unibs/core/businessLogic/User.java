package it.unibs.core.businessLogic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;
//import it.unibs.elabingesw.businesslogic.gestione.Manageable;

@Getter
@Setter
@RequiredArgsConstructor
/**
 * Classe Utente che rappresenta un generico utente. Questa classe
 * verrà estesa dalla classi Manager, Magazziniere e gestore prenotazioni.
 * <p>
 * Invariante di classe: assumo gli attributi immutabili,
 * dopo la creazione dell'oggetto.
 *
 */
public abstract class User implements Serializable {
    private final String username;
    private final String password;
    public boolean sameNickname(String nickname) {
        return this.username.equals(nickname);
    }

    /**
     * Metodo che controlla se la password inserita dall'utente
     * è corretta.
     * <p>
     * Precondizione: assumo parametro non nullo e
     * non coincidente a una stringa vuota.
     *
     * @param pwd la password dell'utente
     * @return TRUE se la password è corretta
     * FALSE se la password è errata
     */
    public boolean pswCheck(String psw) {
        return this.password.equals(psw);
    }
}