/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.db;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author dell1
 */
public class ConexionSQLServer 
{
    Connection conn;
    String userName;
    String password;
    String host;
    int puerto;
    String nombreBD;
    String url;
    
    public ConexionSQLServer()
    {
        try 
        {      
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Este método sirve para abrir y devolver una conexion con SQL Server.
     * @return
     * @throws Exception 
     */
    
    public Connection abrir() throws Exception
    {
        userName = "sa";
        password = "123";
        host = "localhost";
        puerto = 1433;
        nombreBD = "pekesBD";
        url = "jdbc:sqlserver://"+host+":"+puerto+";databaseName="+nombreBD;
        conn = DriverManager.getConnection(url,userName,password);
        return conn;
    }
    
    /**
     * Este método cierra la conexión con la base de datos
     */
    public void cerrar()
    {
        //Validamos que existe una conexión:
        if (conn != null )
        {
            try 
            {
                conn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            
        }
    
    }
}
