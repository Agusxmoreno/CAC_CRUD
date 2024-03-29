/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agust
 */
public class SociosDAO {

    Connection conexion;

    public SociosDAO() throws SQLException {
        Conexion con = new Conexion();
        conexion = con.getConnection();
    }

    public List<Socios> listarSocios() {
        PreparedStatement ps;
        ResultSet rs;

        List<Socios> lista = new ArrayList<>();

        try {
            ps = conexion.prepareStatement("Select * from Socios where activo=1");
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idSocio");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                LocalDate fnac = rs.getDate("fnac").toLocalDate();
                String mail = rs.getString("email");
                String telefono = rs.getString("telefono");
                boolean activo = rs.getBoolean("activo");

                Socios socio = new Socios(id, nombre, apellido, direccion, localidad, fnac, mail, telefono, activo);
                lista.add(socio);
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return lista;
    }

public Socios mostrarSocio(int _id)
    {
        PreparedStatement ps;
        ResultSet rs;
        Socios s1=null;
        try
        {
            ps=conexion.prepareStatement("select * from socios where idSocio=?");
            ps.setInt(1, _id);
            rs=ps.executeQuery();
            while(rs.next())
            {
                int id=rs.getInt("idSocio");
                String nombre=rs.getString("nombre");
                String apellido=rs.getString("apellido");
                String direccion=rs.getString("direccion");
                String localidad=rs.getString("localidad");
                LocalDate fnac = rs.getDate("fnac").toLocalDate();
                String email=rs.getString("email");
                String telefono=rs.getString("telefono");
                boolean activo=rs.getBoolean("activo");                 
                s1=new Socios(id,nombre,apellido,direccion,localidad,fnac,email,telefono,activo);
            }
            
            
            return s1;
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return null;
        }
        
    }
    public Boolean agregarSocio(Socios socio) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("Insert into Socios (nombre, apellido, direccion, localidad, fnac, email, telefono, activo) values (?,?,?,?,?,?,?,?)");
            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getApellido());
            ps.setString(3, socio.getDireccion());
            ps.setString(4, socio.getLocalidad());
            ps.setObject(5, socio.getFnac());
            ps.setString(6, socio.getMail());
            ps.setString(7, socio.getTelefono());
            ps.setBoolean(8, true);

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean eliminarSocio(int _id) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("update socios set activo=0 where idSocio = ?");
            ps.setInt(1, _id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    
    public boolean ActualizarSocios(Socios s1)
    {
        PreparedStatement ps;        
        try
        {
            ps=conexion.prepareStatement("update socios set nombre=?,apellido=?,direccion=?,localidad=?,fnac=?,email=?,telefono=?,activo=? where idSocio=?");
            ps.setString(1, s1.getNombre());
            ps.setString(2, s1.getApellido());
            ps.setString(3, s1.getDireccion());
            ps.setString(4, s1.getLocalidad());
            ps.setObject(5, s1.getFnac()); 
            ps.setString(6, s1.getMail());
            ps.setString(7, s1.getTelefono());
            ps.setBoolean(8, s1.isActivo());
            ps.setInt(9,s1.getId());
            ps.execute();
            return true;          
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
    

}