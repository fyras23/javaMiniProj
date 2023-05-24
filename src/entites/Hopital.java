package entites;



import java.util.ArrayList;

public class Hopital {
    private ArrayList<Employee> employees;
    private ArrayList<Service> services;
    private ArrayList<Malade> malades;
    
	public Hopital() {
		super();
		this.employees = new ArrayList<Employee>();
		this.services =  new ArrayList<Service>();
		this.malades =  new ArrayList<Malade>();
	}
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	public ArrayList<Service> getServices() {
		return services;
	}
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}
	public ArrayList<Malade> getmalades() {
		return malades;
	}
	public void setmalades(ArrayList<Malade> malades) {
		this.malades = malades;
	}
    

}
