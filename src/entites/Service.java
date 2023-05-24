package entites;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Service {
    private SimpleStringProperty codeService;
    private SimpleStringProperty nom;
    private SimpleStringProperty bloc;
    private SimpleIntegerProperty directeur;

    public Service(String codeService, String nom, String bloc, int directeur) {
        this.codeService = new SimpleStringProperty(codeService);
        this.nom = new SimpleStringProperty(nom);
        this.bloc = new SimpleStringProperty(bloc);
        this.directeur = new SimpleIntegerProperty(directeur);
    }

    public String getCodeService() {
        return codeService.get();
    }

    public void setCodeService(String codeService) {
        this.codeService.set(codeService);
    }

    public SimpleStringProperty codeServiceProperty() {
        return codeService;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public String getBloc() {
        return bloc.get();
    }

    public void setBloc(String bloc) {
        this.bloc.set(bloc);
    }

    public SimpleStringProperty blocProperty() {
        return bloc;
    }

    public int getDirecteur() {
        return directeur.get();
    }

    public void setDirecteur(int directeur) {
        this.directeur.set(directeur);
    }

    public SimpleIntegerProperty directeurProperty() {
        return directeur;
    }
}
