package DAO;

import model.Apostador;
import model.Premio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PremioDAO {
    private Connection connect() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public boolean save(Premio premio) {

        String sql = "INSERT INTO premio  (nome, valor_declarado, item_familia) values (?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, premio.getNome());
            stmt.setDouble(2, premio.getValorDeclarado());
            if(premio.isItemDeFamilia() == true){
                System.out.println(premio.isItemDeFamilia());
                stmt.setInt(3, 1);
            }else{
                stmt.setInt(3, 0);
            }

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
