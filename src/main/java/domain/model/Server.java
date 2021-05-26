package domain.model;

import domain.services.ServerDB;

public class Server {

    private int id;
    private String name;
    private String location;
    private int amountOfServices;
    private int ramInMB;

    public Server() {}

    public Server(String name) {
        this(name, "/", 0, 0);
    }

    public Server(String name, String location, int amountOfServices, int ramInMB, ServerDB db) {
        setName(name);
        setLocation(location);
        setAmountOfServices(amountOfServices);
        setRam(ramInMB);
        setId(db.generateId());
    }
    public Server(String name, String location, int amountOfServices, int ramInMB, int id) {
        setName(name);
        setLocation(location);
        setAmountOfServices(amountOfServices);
        setRam(ramInMB);
        setId(id);
    }
    public Server(String name, String location, int amountOfServices, int ramInMB) {
        setName(name);
        setLocation(location);
        setAmountOfServices(amountOfServices);
        setRam(ramInMB);
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Name cannot be empty!");
        }
        this.name = name;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        if (location == null || location.isEmpty()) {
            throw new DomainException("Location cannot be empty!");
        } else {
            this.location = location;
        }
    }
    public int getAmountOfServices() {
        return this.amountOfServices;
    }
    public void setAmountOfServices(int amountOfServices) {
        if (amountOfServices < 0 || amountOfServices > 1000) {
            throw new DomainException("The amount of services must be positive, and lower than 1000!");
        } else {
            this.amountOfServices = amountOfServices;
        }

    }
    public int getRamInMB() {
        return this.ramInMB;
    }
    public void setRam(int ramInMB) {
        if (ramInMB < 0 || ramInMB > 64000) {
            throw new DomainException("The number of ram must be positive, and lower than 64000!");
        } else {
            this.ramInMB = ramInMB;
        }
    }

    @Override
    public boolean equals(Object object) {
        boolean equal = false;
        if (object instanceof Server) {
            Server other = (Server) object;
            equal = this.getName().equals(other.getName());
        }
        return equal;
    }
    @Override
    public String toString() {
        return getName() + " deployed in " + getLocation() + " (" + getAmountOfServices() + " services, " + getRamInMB() + " mb RAM).";
    }
    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
