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
import org.ciaccg.ce.model.Cliente;
import org.ciaccg.ce.model.Persona;
/**
 *
 * @author dell1
 */
public class ControladorClienteBDD {
    
    /**
     * Inserta los datos de un cliente en base de datos.
     * @param Cliente
     * @throws Exception 
     */
    public void insert(Cliente cl) throws Exception
    {
        String query = "EXEC dbo.pa_InsertarCliente ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        ConexionSQLServer connSqlServer = new ConexionSQLServer();
        Connection conn = connSqlServer.abrir();
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //LLENAMOS LOS PARAMETROS DE LA CONSULTA:
       
    
        pstmt.setString(1, cl.getPersona().getNombre());
        pstmt.setString(2, cl.getPersona().getApellidoPaterno());
        pstmt.setString(3, cl.getPersona().getApellidoMaterno());
        pstmt.setString(4, cl.getPersona().getRfc());
        pstmt.setString(5, cl.getPersona().getCurp());
        pstmt.setString(6, cl.getPersona().getDomicilio());
        pstmt.setString(7,cl.getPersona().getCiudad());
        pstmt.setString(8, cl.getPersona().getCodigoPostal());
        pstmt.setString(9, cl.getPersona().getEmail());
        pstmt.setString(10, cl.getPersona().getTelMovil());
        pstmt.setString(11, cl.getPersona().getFotografía());
        pstmt.setDate(12, /*Formato.FECHA.format(*/cl.getPersona().getFechaNacimiento()/*)*/);
        pstmt.setString(13, cl.getFolioCliente());
        pstmt.setString(14,cl.getNombreContacto() );
        pstmt.setString(15,cl.getCategoria());
        pstmt.executeUpdate();
        pstmt.close();
        connSqlServer.cerrar();
    }
    /**
     * Método que actualiza un registro de base de datos 
     * @param cliente 
     * @throws Exception 
     */
    public void update(Cliente cl) throws Exception
    {
        String query = "EXEC dbo.pa_java_ActualizarCliente ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        ConexionSQLServer connSqlServer = new ConexionSQLServer();
        Connection conn = connSqlServer.abrir();
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //LLENAMOS LOS PARAMETROS DE LA CONSULTA:
        
        pstmt.setString(1,cl.getPersona().getNombre());
        pstmt.setString(2,cl.getPersona().getApellidoPaterno());
        pstmt.setString(3,cl.getPersona().getApellidoMaterno());
        pstmt.setString(4,cl.getPersona().getRfc());
        pstmt.setString(5,cl.getPersona().getCurp());
        pstmt.setString(6,cl.getPersona().getDomicilio());
        pstmt.setString(7,cl.getPersona().getCiudad());
        pstmt.setString(8,cl.getPersona().getCodigoPostal());
        pstmt.setString(9,cl.getPersona().getEmail());
        pstmt.setString(10,cl.getPersona().getTelMovil());
        pstmt.setString(11,cl.getPersona().getFotografía());
        pstmt.setDate(12, /*Formato.FECHA.format(*/cl.getPersona().getFechaNacimiento()/*)*/);
        pstmt.setInt(13,cl.getPersona().getIdentificador());
        
        pstmt.setString(14,cl.getFolioCliente() );
        pstmt.setString(15,cl.getNombreContacto() );
        pstmt.setString(16,cl.getCategoria());
        //pstmt.setInt(17, cl.getEstatus());
        pstmt.executeUpdate();
        pstmt.close();
        connSqlServer.cerrar();
    }
    /**
     * Actualiza a un estatus de 0 a un cliente por su ID
     * @param cl
     * @throws Exception 
     */
    public void deleteLogical(Cliente cl) throws Exception
    {
        String query = "EXEC dbo.pa_java_BorrarCliente ?";        
        //Generamos un objeto de conexion y nos conectamos con la BD en SQL Server
        ConexionSQLServer connSQLServer = new ConexionSQLServer();
        Connection conn = connSQLServer.abrir();
        PreparedStatement pstmt = conn.prepareStatement(query);
       
        pstmt.setInt(1,cl.getIdentificador());
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
    public Cliente findById(Cliente cl) throws Exception
    {
        //definimos consulta a ejecutar
        String query = "EXEC dbo.pa_ConsultaCliente ?";        
        //Generamos un objeto de conexion y nos conectamos con la BD en SQL Server
        ConexionSQLServer connSQLServer = new ConexionSQLServer();
        Connection conn = connSQLServer.abrir();
        
        //Generamos un objeto PreparedStatement para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setInt(1,cl.getIdentificador());
        //Ejecutamos la consulta y guardamos el resultado
        ResultSet rs = pstmt.executeQuery();
        
        Cliente cliente = new Cliente();
        //Nos movemos al primer registro devuelto
       
            cliente.setPersona(new Persona());
            cliente.getPersona().setNombre(rs.getString("Nombre"));
            cliente.getPersona().setApellidoMaterno(rs.getString("ApellidoMaterno"));
            cliente.getPersona().setApellidoPaterno(rs.getString("ApellidoPaterno"));
            cliente.getPersona().setRfc(rs.getString("Rfc"));
            cliente.getPersona().setRfc(rs.getString("Curp"));
            cliente.getPersona().setDomicilio(rs.getString("Domicilio"));
            cliente.getPersona().setCiudad(rs.getString("Ciudad"));
            cliente.getPersona().setCodigoPostal(rs.getString("CodigoPostal"));
            cliente.getPersona().setEmail(rs.getString("Email")); 
            cliente.getPersona().setTelMovil(rs.getString("TelMovil"));
            cliente.getPersona().setFechaNacimiento(/*Formato.FECHA.parse(*/rs.getDate("FechaNacimiento")/*)*/);
            
            cliente.setFolioCliente(rs.getString("FolioCliente"));
            cliente.setNombreContacto(rs.getString("NombreContacto"));
            cliente.setCategoria(rs.getString("Categoria"));
         
        
        
        //Cerramos los objetos de conexion con la BD
        rs.close();
        pstmt.close();
        connSQLServer.cerrar();
        return cl;
    }
   
    /**
     * Consulta todos los clientes en la base de datos
     * @return List<Cliente>
     * @throws Exception 
     */
    public List<Cliente> getAll(String query) throws Exception
    {
        Cliente cliente = new Cliente();
        List<Cliente> clientes = new ArrayList<>();
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
            Persona persona = new Persona();
            cliente = new Cliente();
            persona.setIdentificador(Integer.parseInt(rs.getString("pk_IdPersona")));
            persona.setNombre(rs.getString("Nombre"));
            persona.setApellidoMaterno(rs.getString("ApellidoMaterno"));
            persona.setApellidoPaterno(rs.getString("ApellidoPaterno"));
            persona.setRfc(rs.getString("Rfc"));
            persona.setCurp(rs.getString("Curp"));
            persona.setDomicilio(rs.getString("Domicilio"));
            persona.setCiudad(rs.getString("Ciudad"));
            persona.setCodigoPostal(rs.getString("CodigoPostal"));
            persona.setEmail(rs.getString("Email")); 
            persona.setTelMovil(rs.getString("TelMovil"));
            persona.setFotografía(rs.getString("Fotografia"));
            persona.setFechaNacimiento(/*Formato.FECHA.parse(*/rs.getDate("FechaNacimiento")/*)*/);
            cliente.setPersona(persona);
            cliente.setIdentificador(Integer.parseInt(rs.getString("pk_IdCliente")));
            cliente.setFolioCliente(rs.getString("FolioCliente"));
            cliente.setNombreContacto(rs.getString("NombreContacto"));
            cliente.setCategoria(rs.getString("Categoria"));
            cliente.setEstatus(rs.getInt("Estatus"));
            clientes.add(cliente);
        }
        //Cerramos los objetos de conexion con la BD
        rs.close();
        pstmt.close();
        connSQLServer.cerrar();
        
        return clientes;
    }
}
