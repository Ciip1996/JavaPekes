/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.ciaccg.ce.db.ConexionSQLServer;
import org.ciaccg.ce.model.Calzado;
import org.ciaccg.ce.model.Fotografía;


/**
 *
 * @author Iván
 */
public class ControladorCalzadoBDD {
    /**
     * Método que inserta un registro de base de datos 
     * @param cliente 
     * @throws Exception 
     */
    public void insert(Calzado ca) throws Exception
    {
        String query = "EXEC dbo.pa_java_InsertarCalzado ?,?,?,?,?";
        ConexionSQLServer connSqlServer = new ConexionSQLServer();
        Connection conn = connSqlServer.abrir();
        PreparedStatement pstmt = conn.prepareStatement(query);
        //LLENAMOS LOS PARAMETROS DE LA CONSULTA:
        pstmt.setString(1, ca.getCodigo());
        pstmt.setString(2, ca.getEspecificaciones());
        pstmt.setString(3, ca.getMarca());
        pstmt.setString(4, ca.getPreciosXml());
        pstmt.setInt(5, ca.getEstatus());
        
       /* pstmt.setInt(6,ca.getFoto().getIdentificador());
        pstmt.setString(7,ca.getFoto().getCodigo());
        pstmt.setString(8,ca.getFoto().getFoto());
        pstmt.setString(9,ca.getFoto().getDescripcion());*/
        
        pstmt.executeUpdate();
        pstmt.close();
        connSqlServer.cerrar();
    }
    /**
     * Método que actualiza un registro de base de datos 
     * @param cliente 
     * @throws Exception 
     */
    public void update(Calzado ca) throws Exception
    {
        String query = "EXEC dbo.pa_java_ActualizarCalzado ?,?,?,?,?,?,?,?";
        ConexionSQLServer connSqlServer = new ConexionSQLServer();
        Connection conn = connSqlServer.abrir();
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //LLENAMOS LOS PARAMETROS DE LA CONSULTA:
        pstmt.setString(1, ca.getCodigo());
        pstmt.setString(2, ca.getEspecificaciones());
        pstmt.setString(3, ca.getMarca());
        pstmt.setString(4, ca.getPreciosXml());
        pstmt.setInt(5, ca.getEstatus());
        //pstmt.setInt(6,ca.getFotografia().getIdentificador());
       /* pstmt.setString(6,ca.getFotografia().getCodigo());
        pstmt.setString(7,ca.getFotografia().getFoto());
        pstmt.setString(8,ca.getFotografia().getDescripcion());
*/
        pstmt.executeUpdate();
        pstmt.close();
        connSqlServer.cerrar();
    }
    /**
     * Actualiza a un estatus de 0 a un cliente por su ID
     * @param cl
     * @throws Exception 
     */
    public void deleteLogical(Calzado ca) throws Exception
    {
        String query = "EXEC dbo.pa_java_BorrarCalzado ?";        
        //Generamos un objeto de conexion y nos conectamos con la BD en SQL Server
        ConexionSQLServer connSQLServer = new ConexionSQLServer();
        Connection conn = connSQLServer.abrir();
        PreparedStatement pstmt = conn.prepareStatement(query);
       
        pstmt.setInt(1,ca.getIdentificador());
        pstmt.executeUpdate();
        pstmt.close();
        connSQLServer.cerrar();
    }
    /**
     * Busca un cliente por su identificador en la base de datos.
     * @param cliente
     * @return
     * @throws Exception 
     */
    public Calzado findById(Calzado ca) throws Exception
    {
        //definimos consulta a ejecutar
        String query = "EXEC dbo.pa_ConsultaCalzado ?";        
        //Generamos un objeto de conexion y nos conectamos con la BD en SQL Server
        ConexionSQLServer connSQLServer = new ConexionSQLServer();
        Connection conn = connSQLServer.abrir();
        
        //Generamos un objeto PreparedStatement para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setInt(1,ca.getIdentificador());
        //Ejecutamos la consulta y guardamos el resultado
        ResultSet rs = pstmt.executeQuery();
        
        Calzado calzado = new Calzado();
        //Nos movemos al primer registro devuelto        
        calzado.setCodigo(rs.getString("Codigo"));
        calzado.setEspecificaciones(rs.getString("Especificaciones"));
        calzado.setMarca(rs.getString("Marca"));
        calzado.setEstatus(rs.getInt("fk_IdFoto"));
        calzado.setPreciosXml(rs.getString("PreciosXML"));
        calzado.setEstatus(rs.getInt("Estaus"));

        Fotografía foto = new Fotografía();        
        foto.setIdentificador(rs.getInt("pk_IdGaleria"));
        foto.setCodigo(rs.getString("Codigo"));
        foto.setFoto(rs.getString("Foto"));
        foto.setDescripcion(rs.getString("Descripcion"));
        //calzado.setFotografia(foto);
        //Cerramos los objetos de conexion con la BD
        rs.close();
        pstmt.close();
        connSQLServer.cerrar();
        return calzado;
    }
   
    /**
     * Consulta todos los clientes en la base de datos
     * @return List<Cliente>
     * @throws Exception 
     */
    public List<Calzado> getAll(String query) throws Exception
    {
        Calzado calzado;
        List<Calzado> lstCalzado = new ArrayList<>();
        //definimos consulta a ejecutar
        //String query = "SELECT * FROM V_Cliente";        
        //Generamos un objeto de conexion y nos conectamos con la BD en SQL Server
        ConexionSQLServer connSQLServer = new ConexionSQLServer();
        Connection conn = connSQLServer.abrir();
        
        //Generamos un objeto PreparedStatement para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Ejecutamos la consulta y guardamos el resultado
        ResultSet rs = pstmt.executeQuery();
        
        //Nos movemos al primer registro devuelto
        while(rs.next())
        {
            calzado = new Calzado();
             //Nos movemos al primer registro devuelto        
            calzado.setCodigo(rs.getString("Codigo"));
            calzado.setEspecificaciones(rs.getString("Especificaciones"));
            calzado.setMarca(rs.getString("Marca"));
            calzado.setEstatus(rs.getInt("fk_IdFoto"));
            calzado.setPreciosXml(rs.getString("PreciosXML"));
            calzado.setEstatus(rs.getInt("Estaus"));
            Fotografía foto = new Fotografía();        
            foto.setIdentificador(rs.getInt("pk_IdGaleria"));
            foto.setCodigo(rs.getString("Codigo"));
            foto.setFoto(rs.getString("Foto"));
            foto.setDescripcion(rs.getString("Descripcion"));
       //     calzado.setFotografia(foto);
            
            lstCalzado.add(calzado);
            
        }
        //Cerramos los objetos de conexion con la BD
        rs.close();
        pstmt.close();
        connSQLServer.cerrar();
        
        return lstCalzado;
    }
}
