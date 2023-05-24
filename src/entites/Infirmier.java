package entites;
import javafx.beans.property.*;

public class Infirmier {
    private final IntegerProperty numeroEmploye;
    private final DoubleProperty salaire;
    private final StringProperty grade;
    private final IntegerProperty docteurAttache;

    public Infirmier(int numeroEmploye, double salaire, String grade, int docteurAttache) {
        this.numeroEmploye = new SimpleIntegerProperty(numeroEmploye);
        this.salaire = new SimpleDoubleProperty(salaire);
        this.grade = new SimpleStringProperty(grade);
        this.docteurAttache = new SimpleIntegerProperty(docteurAttache);
    }

    public int getNumeroEmploye() {
        return numeroEmploye.get();
    }

    public IntegerProperty numeroEmployeProperty() {
        return numeroEmploye;
    }

    public double getSalaire() {
        return salaire.get();
    }

    public DoubleProperty salaireProperty() {
        return salaire;
    }

    public String getGrade() {
        return grade.get();
    }

    public StringProperty gradeProperty() {
        return grade;
    }

    public int getDocteurAttache() {
        return docteurAttache.get();
    }

    public IntegerProperty docteurAttacheProperty() {
        return docteurAttache;
    }
    public void setGrade(String grade) {
        this.grade.set(grade);
    }


}
