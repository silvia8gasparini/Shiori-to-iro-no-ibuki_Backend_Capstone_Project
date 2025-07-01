package it.epicode.finalproject.enumeration;

public enum TimeSlot {
    MATTINA("Mattina"),
    POMERIGGIO("Pomeriggio"),
    SERA("Sera");

    private final String label;
    TimeSlot(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
