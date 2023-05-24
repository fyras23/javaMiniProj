package entites;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Surveillant {
    private IntegerProperty infirmierNum;
    private StringProperty dateDebut;
    private StringProperty heureFin;

    public Surveillant(int infirmierNum, String dateDebut, String heureFin) {
        this.infirmierNum = new SimpleIntegerProperty(infirmierNum);
        this.dateDebut = new SimpleStringProperty(dateDebut);
        this.heureFin = new SimpleStringProperty(heureFin);
    }

    public int getInfirmierNum() {
        return infirmierNum.get();
    }

    public IntegerProperty infirmierNumProperty() {
        return infirmierNum;
    }

    public void setInfirmierNum(int infirmierNum) {
        this.infirmierNum.set(infirmierNum);
    }

    public String getDateDebut() {
        return dateDebut.get();
    }

    public StringProperty dateDebutProperty() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut.set(dateDebut);
    }

    public String getHeureFin() {
        return heureFin.get();
    }

    public StringProperty heureFinProperty() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin.set(heureFin);
    }
}
