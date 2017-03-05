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
import tikape.runko.domain.Alue;


public class AlueDao implements Dao<Alue, Integer> {

    private Database database;

    public AlueDao(Database database) {
        this.database = database;
    }

    @Override
    public Alue findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Alue WHERE Id_alue = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("Id_alue");
        Integer viestiMaara = 1;
        String nimi = rs.getString("Nimi");

        Alue o = new Alue(id, viestiMaara, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Alue> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Alue");

        ResultSet rs = stmt.executeQuery();
        List<Alue> alueet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("Id_alue");
            
            PreparedStatement stmt2 = connection.prepareStatement("SELECT COUNT(Aihe) AS Maara FROM Viesti WHERE (SELECT Alue FROM Aihe JOIN Viesti ON Viesti.Aihe=Aihe.Id_aihe) = ?");
            stmt2.setObject(1, id);
            ResultSet rs2 = stmt2.executeQuery();
            Integer viestiMaara = rs2.getInt("Maara");
            rs2.close();
            stmt2.close();
            
            String nimi = rs.getString("Nimi");
            
//            PreparedStatement stmt3 = connection.prepareStatement("SELECT Aihe, Aika FROM Viesti JOIN Aihe ON Viesti.Aihe=Aihe.Id_aihe ORDER BY max(Aika)");
//            ResultSet rs3 = stmt3.executeQuery();
//            String paivays = rs3.getString("Aika");
//            rs3.close();
//            stmt3.close();

            alueet.add(new Alue(id, viestiMaara, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return alueet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
