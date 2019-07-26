/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author creoo
 */
public class Dbutils {

    private static final String USERNAME = "db@adm1n";
    private static final String PASS = "db@adm1n";
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/bootcamp?zeroDateTimeBehavior=CONVERT_TO_NULL"
            + "&useUnicode=true"
            + "                &useJDBCCompliantTimezoneShift=true"
            + "                &useLegacyDatetimeCode=false"
            + "                &serverTimezone=UTC"
            + "                &allowPublicKeyRetrieval=true"
            + "                &useSSL=false";

    public static Connection getconnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(Dbutils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Dbutils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void closeStatement(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(Dbutils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
