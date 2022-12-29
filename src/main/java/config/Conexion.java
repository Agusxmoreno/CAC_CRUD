/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.*;

/**
 *
 * @author agust
 */
public class Conexion {
    
    public String driver="com.mysql.jdbc.Driver";
    
    public Connection getConnection() throws SQLException{
        Connection conexion = null;
        
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud22566","root","root");
        }catch(ClassNotFoundException|SQLDataException e){
            System.out.println(e);
        }
        
        return conexion;
    }
 
    
}
