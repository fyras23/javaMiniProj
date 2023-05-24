package entites;

import javafx.beans.property.SimpleIntegerProperty;

public class Salle {
    private final SimpleIntegerProperty numeroSalle;
    private final SimpleIntegerProperty surveillant;
    private final SimpleIntegerProperty nombreLits;

    public Salle(int numeroSalle, int surveillant, int nombreLits) {
        this.numeroSalle = new SimpleIntegerProperty(numeroSalle);
        this.surveillant = new SimpleIntegerProperty(surveillant);
        this.nombreLits = new SimpleIntegerProperty(nombreLits);
    }

    public int getNumeroSalle() {
        return numeroSalle.get();
    }

    public SimpleIntegerProperty numeroSalleProperty() {
        return numeroSalle;
    }

    public void setNumeroSalle(int numeroSalle) {
        this.numeroSalle.set(numeroSalle);
    }

    public int getSurveillant() {
        return surveillant.get();
    }

    public SimpleIntegerProperty surveillantProperty() {
        return surveillant;
    }

    public void setSurveillant(int surveillant) {
        this.surveillant.set(surveillant);
    }

    public int getNombreLits() {
        return nombreLits.get();
    }

    public SimpleIntegerProperty nombreLitsProperty() {
        return nombreLits;
    }

    public void setNombreLits(int nombreLits) {
        this.nombreLits.set(nombreLits);
    }
}
