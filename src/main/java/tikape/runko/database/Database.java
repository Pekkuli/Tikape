package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
//        lista.add("DROP TABLE Alue;");
//        lista.add("DROP TABLE Aihe;");
//        lista.add("DROP TABLE Viesti;");
//
//        lista.add("CREATE TABLE Alue (Id_alue INTEGER NOT NULL PRIMARY KEY, Nimi VARCHAR(255));");
//        lista.add("CREATE TABLE Aihe (Id_aihe INTEGER NOT NULL PRIMARY KEY, Alue INTEGER, Nimi VARCHAR(255), FOREIGN KEY(Alue) REFERENCES Alue(Id_alue));");
//        lista.add("CREATE TABLE Viesti (Aihe INTEGER, Teksti VARCHAR(255), Nimimerkki VARCHAR(255), Aika DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY(Aihe) REFERENCES Aihe(Id_aihe));");
//        
//        lista.add("INSERT INTO Alue (Nimi) VALUES ('NahkaUutiset');");
//        lista.add("INSERT INTO Alue (Nimi) VALUES ('NahkaKeskustelut');");
//        lista.add("INSERT INTO Alue (Nimi) VALUES ('NahkaKauppa-alue');");
//        
//        lista.add("INSERT INTO Aihe (Alue, Nimi) VALUES (1, 'NahkaSäännöt');");
//        lista.add("INSERT INTO Aihe (Alue, Nimi) VALUES (1, 'NahkaIlmoitukset');");
//        lista.add("INSERT INTO Aihe (Alue, Nimi) VALUES (2, 'NahkaKeskusteluiden säännöt');");
//        lista.add("INSERT INTO Aihe (Alue, Nimi) VALUES (3, 'NahkaKauppa-alueen säännöt');");
//        
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (1, 'NahkaLuolassa ei hyväksytä toisten kiusaamista', 'NahkaKeisari');");
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (1, 'Tämä on hyvä', 'NahkaFani');");
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (1, '^', 'NahkaMies123');");
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (2, 'NahkaLuolassa saa keskustella vain aiheista joihin liittyy nahka', 'NahkaKeisari');");
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (3, 'Jos huijaa toisia niin joutuu vankilaan kaksoispiste deedee', 'NahkaKeisari');");
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (4, 'Muistakaa myydä ja ostaa vain nahkaa', 'NahkaKeisari');");
//
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (2, 'lähettäkää apua ;)', 'tietokone');");
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (4, 'koskeeko tämä myös nahasta tehtyja esineitä?', 'peruna');");
//        lista.add("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (4, 'kyllä', 'NahkaKeisari');");
		
        return lista;
    }
}
