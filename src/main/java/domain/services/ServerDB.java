package domain.services;

import domain.model.DomainException;
import domain.model.Server;
import domain.seeders.ServerDBSeeder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class ServerDB {

    private final ArrayList<Server> servers;

    public ServerDB () {
        this.servers = new ArrayList<>();
    }

    public void addServer(Server server) {
        this.servers.add(server);
    }
    public int addServer(String name, String location, String amountOfServicesParam, String amountOfRAMParam) throws IOException {
        int amountOfServices;
        int amountOfRAM;
        try {
            amountOfServices = Integer.parseInt(amountOfServicesParam);
        } catch (NumberFormatException e) {
            throw new DomainException("The amount of services must be positive, and lower than 1000!");
        }
        try {
            amountOfRAM = Integer.parseInt(amountOfRAMParam);
        } catch (NumberFormatException e) {
            throw new DomainException("The number of ram must be positive!");
        }
        // using this.getByName(name).size() != this.servers.size() below, because of initial size of zero
        if (this.getByName(name).size() > 0 && this.getByName(name).size() != this.servers.size()) throw new IOException("The server with this name already exists.");
        int generated_id = this.generateId();
        return ( this.servers.add(new Server(name, location, amountOfServices, amountOfRAM, generated_id)) ) ? generated_id : -1;
    }

    public ArrayList<Server> getServers() {
        return this.servers;
    }

    //note
    public double getMeanMBOverServers() {
        /* original instead of getMBInUse(): double gemiddelde = 0;
        for (Server server: this.servers) {
            gemiddelde += server.getRamInMB();
        }*/
        return Double.parseDouble(String.valueOf(this.getMBInUse())) / this.servers.size();
    }
    public long getMBInUse() {
        long mb_in_use = 0;
        for (Server server: this.servers) {
            mb_in_use += server.getRamInMB();
        }
        return mb_in_use;
    }
    public Server getByID(String id) {
        Server found = null;
        for (Server server : this.servers) {
            if ( Integer.parseInt(id) == server.getId() ) {
                found = server;
                break;
            }
        }
        return found;
    }

    public ArrayList<Server> getByName(String name) {
        ArrayList<Server> _servers = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) return _servers;
        name = name.toLowerCase().trim();

        for (Server _server: this.servers) {
            if (_server.getName().toLowerCase().equals(name)) _servers.add(_server);
        }
        return _servers;
    }

    public ArrayList<Server> search(String query) {
        ArrayList<Server> _servers = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) return _servers;
        query = query.toLowerCase().trim();

        for (Server _server: this.servers) {
            if (_server.getName().toLowerCase().matches(".*" + query + ".*")) _servers.add(_server);
            else if (_server.getLocation().toLowerCase().matches(".*" + query + ".*")) _servers.add(_server);
            else if (String.valueOf(_server.getAmountOfServices()).equals(query)) _servers.add(_server);
            else if (String.valueOf(_server.getRamInMB()).equals(query)) _servers.add(_server);
        }
        return _servers;
    }

    public void updateServer(HttpServletRequest request) {
        // PUT method
        String serverid = request.getParameter("serverid");
        Server server = this.getByID(serverid);
        server.setName( request.getParameter("serverName") );
        server.setLocation( request.getParameter("serverLocation") );
        server.setAmountOfServices( Integer.parseInt(request.getParameter("amountOfServices")) );
        server.setRam( Integer.parseInt(request.getParameter("amountOfRAM")) );
        this.servers.set(this.servers.indexOf(server), server);
    }

    public void removeByID(String id) throws IOException {
        if ( this.getByID(id) == null ) throw new IOException("Server by ID was not found.");
        this.servers.remove(this.getByID(id));
    }
    public int generateId() {
        int lastId = 0;

        // there might be a method for this, but i don't know
        for (Server server: this.servers) {
            lastId = server.getId();
        }
        return lastId + 1;
    }

    /* Helper functions */
    public int indexOf(Server server) {
        return this.servers.indexOf(server);
    }
    public void test() {
        System.out.println(this.servers);
    }
    public void reload() throws IOException {
        this.servers.clear();
        new ServerDBSeeder(this);
    }
    public void clear() {
        this.servers.clear();
    }
}
