package domain.factories;

import domain.model.Server;
import domain.services.ServerDB;

import java.io.IOException;

// https://github.com/DiUS/java-faker | https://www.baeldung.com/java-faker
// this should actually be implemented by a seeder - wont work right now
public class ServerFactory {
    public ServerFactory(ServerDB db, int amount) throws IOException {
        for (int i = 0; i < amount; i++) {
            Server server = new Server("Proximus Server", "Belgium", 0, 0, db);
            db.addServer(server);
        }
    }
}
