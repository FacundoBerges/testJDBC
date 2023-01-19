/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import domain.User;
import java.util.*;
import java.sql.*;

/**
 *
 * @author facup
 */
public class UserDAO 
{
    private static final String SQL_SELECT = "SELECT `id_user`, `user`, `password` FROM `test`.`users`;";
    private static final String SQL_INSERT = "INSERT INTO `test`.`users` (`user`, `password`) VALUES (?, ?);";
    private static final String SQL_UPDATE = "UPDATE `test`.`users` SET `user` = ?, `password` = ? WHERE (`id_user` = ?);";
    private static final String SQL_DELETE = "DELETE FROM `test`.`users` WHERE `id_user` = ?;";
    
    /**
     * select all users from a table and return them as a collection (List).
     * 
     * @return List   Users list.
     */
    public List<User> select()
    {
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        
        try
        {
            conn = Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                int idUser = rs.getInt("id_user");
                String username = rs.getString("user");
                String password = rs.getString("password");
                
                user = new User(idUser, username, password);
                
                userList.add(user);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally
        {
            try 
            {
                if(rs != null)
                    Connect.close(rs);
                if(rs != null)
                    Connect.close(pstmt);
                if(rs != null)
                    Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return userList;
    }
    
    /**
     * Inserts an user on a table.
     * 
     * @param user  user to be added.
     * @return      quantity of inserted users.
     */
    public int insert(User user)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_INSERT);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    /**
     * updates an user information on a table.
     * 
     * @param user  user to be updated.
     * @return      quantity of modified users.
     */
    public int update(User user)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_UPDATE);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getIdUser());
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    /**
     * Deletes an user from a table.
     * 
     * @param user  user to delete
     * @return      quantity of users deleted
     */
    public int delete(User user)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_DELETE);
            
            pstmt.setInt(1, user.getIdUser());
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
}
