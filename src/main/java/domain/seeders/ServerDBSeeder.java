package domain.seeders;

import domain.model.Server;
import domain.services.ServerDB;

import java.io.IOException;

public class ServerDBSeeder {
    public ServerDBSeeder(ServerDB db) throws IOException {
        if (db == null) throw new IOException("Database Seeder needs database.");
        // actual values should be 'imported' from serverfactory
        db.addServer(new Server("Olympus Server", "Belgium", 3, 1024, db));
        db.addServer(new Server("Server-Service", "Belgium", 0, 1024, db));
        db.addServer(new Server("Server-5", "Finland", 9, 2048, db));
        db.addServer(new Server("Server1", "Germany", 2, 1024, db));
        db.addServer(new Server("Server2", "Germany", 7, 4096, db));
    }
}
