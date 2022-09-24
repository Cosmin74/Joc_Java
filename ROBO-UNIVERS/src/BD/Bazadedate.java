package BD;

import Exceptii.ExceptiiBazadeDate;
import main.Game;
import main.GameScreen;

import java.io.IOException;
import java.sql.*;

public class Bazadedate {
    GameScreen gp;

    public Bazadedate(GameScreen gp) {

        this.gp = gp;
    }

    public void tabela() throws ExceptiiBazadeDate {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JOC.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS JOC_TABLE " +
                    " (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                    "," +
                    " FLAG INT ," +
                    " MAP INT NOT NULL, " +
                    " VIATAJ INT NOT NULL) ";
            stmt.execute(sql);
            sql = "INSERT INTO JOC_TABLE (FLAG,MAP,VIATAJ) " +
                    "VALUES('%d','%d','%d');"
                            .formatted(gp.flag1, gp.currentMap, gp.player.life);
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
           throw new ExceptiiBazadeDate();

        }
    }

    public void tabela1() throws ExceptiiBazadeDate {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JOC.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM JOC_TABLE;");
            c.commit();

            while (rs.next()) {
                gp.flag1 = rs.getInt("FLAG");
                gp.currentMap = rs.getInt("MAP");
                gp.player.life = rs.getInt("VIATAJ");
            }
//            String sql = "DROP TABLE JOC_TABLE";
//            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        } catch (Exception e) {
            throw new ExceptiiBazadeDate();
        }
    }
}
