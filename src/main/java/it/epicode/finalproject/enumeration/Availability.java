package it.epicode.finalproject.enumeration;

public enum Availability {
    DISPONIBILE("Disponibile"),
    IN_PRESTITO("Attualmente in prestito"),
    NON_DISPONIBILE("Non disponibile"),
    SU_ORDINAZIONE("Ordinabile su richiesta");

    private final String label;
    Availability(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
