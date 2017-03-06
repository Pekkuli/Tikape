/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Aihe;


public class AiheDao implements Dao<Aihe, Integer> {

    private Database database;

    public AiheDao(Database database) {
        this.database = database;
    }
    
    @Override
    public Aihe findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe WHERE Id_aihe = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("Id_aihe");
        Integer viestiMaara = 1;
        String nimi = rs.getString("Nimi");

        Aihe o = new Aihe(id, viestiMaara, nimi, "1991");

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Aihe> findAll(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe WHERE Alue = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<Aihe> aiheet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("Id_aihe");
            
            PreparedStatement stmt2 = connection.prepareStatement("SELECT COUNT(Aihe) AS Maara FROM Viesti WHERE Aihe = ?");
            stmt2.setObject(1, id);
            ResultSet rs2 = stmt2.executeQuery();
            Integer viestiMaara = rs2.getInt("Maara");
            String nimi = rs.getString("Nimi");
            
            PreparedStatement stmt3 = connection.prepareStatement("SELECT MAX(Aika) AS Aika FROM Viesti WHERE Aihe = ?");
            stmt3.setObject(1, id);
            ResultSet rs3 = stmt3.executeQuery();
            
            String paivays = rs3.getString("Aika");
            aiheet.add(new Aihe(id, viestiMaara, nimi, paivays));
            
            rs2.close();
            stmt2.close();
        }

        rs.close();
        stmt.close();
        connection.close();

        return aiheet;
    }
    
    public Aihe uusiAihe(Integer alueid, String otsikko, String teksti, String nimimerkki) throws SQLException {
        if(!teksti.isEmpty() && !otsikko.isEmpty()){
            if(nimimerkki.isEmpty()){
                nimimerkki = "Anonyymi";
            }
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Aihe (Alue, Nimi) VALUES (?, ?);");
            stmt.setObject(1, alueid);
            stmt.setObject(2, otsikko);
            stmt.executeUpdate();
            stmt.close();
            
            stmt = connection.prepareStatement("SELECT Id_aihe FROM Aihe WHERE Nimi = ?");
            stmt.setObject(1, otsikko);
            ResultSet rs = stmt.executeQuery();
            Integer aiheid = rs.getInt("Id_aihe");
            stmt.close();
            rs.close();
            ViestiDao viesti = new ViestiDao(database);
            viesti.uusiViesti(aiheid, teksti, nimimerkki);
            
            Aihe aihe = new Aihe(aiheid, otsikko);
            connection.close();
            return aihe;
        }
        Aihe aihe = new Aihe(1, otsikko);
        return aihe;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
