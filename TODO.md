# Idee per refactoring e pattern

- [ ] Molte classi sono interessate a variazioni di Restaurant, implementare Observer/Singleton
- [ ] Applicare MVC dove possibile (i controller hanno anche responsabilità di vista)
- [ ] Molte classi dipendono da variazioni del registro del magazzino e devono reagire diversamente
quando qualcosa varia, implementare Observer
- [ ] La classe Restaurant va semplificata, magari Facade o simili
- [ ] Praticamente tutte le stringhe possono essere rimosse e messe in file dove vengono
caricate on demand
- [ ] Validazione prenotazioni da muovere in Reservation (SRP, information expert)