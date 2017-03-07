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
import tikape.runko.domain.Viesti;

public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE Aihe = ?;");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("Aihe");
        String teksti = rs.getString("Teksti");
        String nimimerkki = rs.getString("Nimimerkki");
        String paivays = rs.getString("Aika");

        Viesti o = new Viesti(id, teksti, nimimerkki, paivays);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Viesti> findAll(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE Viesti.Aihe = ?;");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("Aihe");
            String teksti = rs.getString("Teksti");
            String nimimerkki = rs.getString("Nimimerkki");
            String paivays = rs.getString("Aika");

            viestit.add(new Viesti(id, teksti, nimimerkki, paivays));
        }

        rs.close();
        stmt.close();
        connection.close();
        return viestit;
    }
    
    public void uusiViesti(Integer aiheid, String teksti, String nimimerkki) throws SQLException{
        if(nimimerkki.isEmpty()){
            nimimerkki = "Anonyymi";
        }
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Viesti (Aihe, Teksti, Nimimerkki) VALUES (?, ?, ?);");
        stmt.setObject(1, aiheid);
        stmt.setObject(2, teksti);
        stmt.setObject(3, nimimerkki);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
