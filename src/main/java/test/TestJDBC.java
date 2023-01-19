package test;

import data.*;
import domain.User;
import java.sql.*;

/**
 * @author Facundo
 */
public class TestJDBC 
{
    public static void main(String[] args) 
    {
        Connection conn = null;
        
        try 
        {
            conn = Connect.getConnection();
            
            if ( conn.getAutoCommit() )
                conn.setAutoCommit(false);
            
            UserDAO userDAO = new UserDAO(conn);
            
            User changeUserInfo = new User(8);
            changeUserInfo.setUsername("Juan Manuel");
            changeUserInfo.setPassword("MySurname");
            
            userDAO.update(changeUserInfo);
            
            
            User addUser = new User();
            addUser.setUsername("Gino");
            //addUser.setPassword("H3LL0_W0RLD1111111111111111111111111111111111111111111111111111111111"); // Doesn't commit updates to Database
            addUser.setPassword("H3LL0_W0RLD"); // Commit ok
            
            userDAO.insert(addUser);
            
            conn.commit();
            System.out.println("Changes commited correctly.");
            conn.close();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace(System.out);
            
            try 
            {
                conn.rollback();
            } 
            catch (SQLException ex1) 
            {
                ex1.printStackTrace(System.out);
            }
        }
        
    }
}
