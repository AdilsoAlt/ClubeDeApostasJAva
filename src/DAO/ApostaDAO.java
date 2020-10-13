package DAO;

import model.Aposta;
import model.Apostador;
import model.PedraPapelTesoura;
import model.Premio;
import org.w3c.dom.ls.LSOutput;
import views.RowApostaTable;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApostaDAO {


    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<RowApostaTable> findAll() {
        String sql = "select a.momento, a.juiz, b1.nome apostador_1,b2.nome apostador_2, a.jogada1, a.jogada2, b3.nome ganhador, a.premio\n" +
                "from aposta a\n" +
                "inner join apostador b1 on a.apostador1=b1.cpf\n" +
                "inner join apostador b2 on a.apostador2=b2.cpf\n" +
                "inner join apostador b3 on a.ganhador=b3.cpf;";

        List<RowApostaTable> apostas = new ArrayList<>();

        RowApostaTable aposta =null;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                aposta = new RowApostaTable(

                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
              apostas.add(aposta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return apostas;

    }


    public boolean saveAposta(Aposta novaAposta) {
        String sql = "INSERT INTO aposta  (momento, juiz, apostador1, apostador2, jogada1, jogada2, ganhador, premio) values (?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1,novaAposta.getMomento().toString());
            stmt.setString(2, novaAposta.getJuiz());
            stmt.setString(3, novaAposta.getJogador1().getCpf());
            stmt.setString(4, novaAposta.getJogador2().getCpf());
            stmt.setString(5, novaAposta.getJogada1().toString());
            stmt.setString(6, novaAposta.getJogada2().toString());
            stmt.setString(7, novaAposta.getGanhador().getCpf());
            stmt.setString(8, novaAposta.getPremio().getNome());
            PremioDAO p = new PremioDAO();
            p.save(novaAposta.getPremio());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
}


