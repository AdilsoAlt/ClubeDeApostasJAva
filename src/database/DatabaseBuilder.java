package database;

import java.sql.*;

public class DatabaseBuilder {
    public static void main(String[] args) {
        buildTableApostador();
        buildTablePremio();
        buildTableAposta();
        populate();
    }

    public static Connection connect() {

        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public static void buildTableApostador(){
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS`apostador` (" +
                "`nome` TEXT,"+
                "`cpf` TEXT PRIMARY KEY,"+
                "`telefone` TEXT,"+
                "`idade` INTEGER,"+
                "`numero_divorcios` INTEGER"+
                ");";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void buildTableAposta(){
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS aposta (" +
                "`momento` TEXT,"+
                "`juiz` TEXT,"+
                "`apostador1` TEXT,"+
                "`apostador2` TEXT,"+
                "`jogada1` TEXT,"+
                "`jogada2` TEXT,"+
                "`ganhador` TEXT,"+
                "`premio` TEXT,"+
                " FOREIGN KEY(`apostador1`) REFERENCES `apostador`(`cpf`),"+
                " FOREIGN KEY(`apostador2`) REFERENCES `apostador`(`cpf`),"+
                " FOREIGN KEY(`ganhador`) REFERENCES `apostador`(`cpf`),"+
                " FOREIGN KEY(`premio`) REFERENCES `premio`(`nome`)"+
                ");";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void buildTablePremio(){
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS`premio` (" +
                "`nome` TEXT PRIMARY KEY,"+
                "`valor_declarado` REAL,"+
                "`item_familia` INTEGER "+
                ");";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void populate(){

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();){

            /* POPULANDO TABELA APOSTADOR */
            stmt.addBatch ("INSERT INTO apostador VALUES('MICHAEL SCOFIELD', '123', '16991769656', 27, 1);");
            stmt.addBatch ("INSERT INTO apostador VALUES('LINCOLN BURROWS', '456', '16991859654', 35, 2);");
            stmt.addBatch ("INSERT INTO apostador VALUES('BRADLEY BELLICK', '789', '16988069656', 29, 0);");
            stmt.addBatch ("INSERT INTO apostador VALUES('JOHN ABRUZZI', '741', '16991513151', 31, 4);");

            /* POPULANDO TABELA PREMIO */
            stmt.addBatch ("INSERT INTO premio VALUES('TELEVISÃO DE TUBO', 100.00, 1);");
            stmt.addBatch ("INSERT INTO premio VALUES('DOLLAR', 300.00, 0);");
            stmt.addBatch ("INSERT INTO premio VALUES('BARRIL DE HEINEKEN',70.00, 0);");
            stmt.addBatch ("INSERT INTO premio VALUES('CHAPEU DE PALHA',700.00, 1);");

            /* POPULANDO TABELA APOSTA */
            stmt.addBatch ("INSERT INTO aposta VALUES('2020-07-21','MANO BROW','123', '456', 'PEDRA', 'TESOURA', '123','TELEVISÃO DE TUBO');");
            stmt.addBatch ("INSERT INTO aposta VALUES('2020-07-29','MANO BROW','123', '789', 'PAPEL', 'TESOURA', '789','DOLLAR');");
            stmt.addBatch ("INSERT INTO aposta VALUES('2020-07-30','MANO BROW','789', '741', 'PEDRA', 'PAPEL', '741','BARRIL DE HEINEKEN');");
            stmt.addBatch ("INSERT INTO aposta VALUES('2020-08-01','MANO BROW','741', '123', 'TESOURA', 'PAPEL', '741', 'FARDO BAVARIA QUENTE');");
            stmt.addBatch ("INSERT INTO aposta VALUES('2020-08-08','MANO BROW','456', '741', 'PEDRA', 'TESOURA', '456','CHAPEU DE PALHA');");


            stmt.executeBatch();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
