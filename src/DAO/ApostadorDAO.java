package DAO;

import model.Aposta;
import model.ApostaDTO;
import model.Apostador;
import model.ApostadorDTO;
import sun.security.krb5.internal.APOptions;
import views.RowApostaTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class ApostadorDAO {


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


    public boolean save(Apostador apostador) {

        String sql = "INSERT INTO apostador  (nome, cpf, telefone, idade, numero_divorcios) values (?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, apostador.getNome());
            stmt.setString(2, apostador.getTelefone());
            stmt.setString(3, apostador.getCpf());
            stmt.setInt(4, apostador.getIdade());
            stmt.setInt(5, apostador.getNumeroDivorcios());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public Apostador find(String cpf) {

        String sql = "SELECT * from apostador  where  cpf = ?";
        Apostador a = new Apostador();
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {

                a.setNome(rs.getString(1));
                a.setCpf(rs.getString(2));
                a.setTelefone(rs.getString(3));
                a.setIdade(rs.getInt(4));
                a.setNumeroDivorcios(rs.getInt(5));
                return a;

            } else {
                return null;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public boolean update(Apostador apostador) {
        String sql = "UPDATE  apostador set nome = ?,telefone=?, idade=?, numero_divorcios=? where cpf=?";

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, apostador.getNome());
            stmt.setString(2, apostador.getTelefone());
            stmt.setInt(3, apostador.getIdade());
            stmt.setInt(4, apostador.getNumeroDivorcios());
            stmt.setString(5, apostador.getCpf());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean delete(String cpf) {

        String sql = "DELETE FROM apostador  WHERE cpf = ?";

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.execute();


            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public List<ApostadorDTO> findApostadores() {

        String sql = "select * from apostador";

        List<ApostadorDTO> listaApostadores = new ArrayList<>();


        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {

                ApostadorDTO apostador = new ApostadorDTO();
                apostador.setNome(rs.getString(1));
                apostador.setCpf(rs.getString(2));
                apostador.setTelefone(rs.getString(3));
                apostador.setIdade(rs.getInt(4));
                apostador.setNumeroDivorcios(rs.getInt(5));

                apostador.add(findApostas(rs.getString(2)));

                listaApostadores.add(apostador);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaApostadores;

    }

    private List<ApostaDTO> findApostas(String cpf) {
        String sql = "SELECT * from aposta  where  apostador1 = ? or apostador2 =?";
        List<ApostaDTO> lista = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    ApostaDTO ap = new ApostaDTO();

                    ap.setMomento(rs.getString(1));
                    ap.setJuiz(rs.getString(2));
                    ap.setJogador1(rs.getString(3));
                    ap.setJogador2(rs.getString(4));
                    ap.setJogada1(rs.getString(5));
                    ap.setJogador1(rs.getString(6));
                    ap.setGanhador(rs.getString(7));
                    ap.setPremio(rs.getString(8));

                    lista.add(ap);

                }
                return lista;

            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
