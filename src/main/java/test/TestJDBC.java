package test;

import data.*;
import domain.UserDTO;
import java.sql.*;
import java.util.List;

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
            
            IUserDAO userDAO = new UserDaoJDBC(conn);
            
            
            List<UserDTO> users = userDAO.select();
            
            users.forEach((UserDTO user) -> {
                System.out.println(user);
            });
            
            
            conn.commit();
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
