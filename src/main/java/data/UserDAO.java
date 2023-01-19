package data;

import domain.User;
import java.util.*;
import java.sql.*;

/**
 * @author Facundo
 */
public class UserDAO 
{
    private static final String SQL_SELECT = "SELECT `id_user`, `user`, `password` FROM `test`.`users`;";
    private static final String SQL_INSERT = "INSERT INTO `test`.`users` (`user`, `password`) VALUES (?, ?);";
    private static final String SQL_UPDATE = "UPDATE `test`.`users` SET `user` = ?, `password` = ? WHERE (`id_user` = ?);";
    private static final String SQL_DELETE = "DELETE FROM `test`.`users` WHERE `id_user` = ?;";
    private Connection transConnection = null;

    
    public UserDAO() {
    }
    
    public UserDAO(Connection transConnection) {
        this.transConnection = transConnection;
    }
    
    
    /**
     * select all users from a table and return them as a collection (List).
     * 
     * @return List   Users list.
     */
    public List<User> select() throws SQLException
    {
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
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
        finally
        {
            try 
            {
                Connect.close(rs);
                Connect.close(pstmt);
                if(this.transConnection == null)
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
    public int insert(User user) throws SQLException
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_INSERT);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            
            result = pstmt.executeUpdate();
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                if(this.transConnection == null)
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
    public int update(User user) throws SQLException
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_UPDATE);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getIdUser());
            
            result = pstmt.executeUpdate();
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                if(this.transConnection == null)
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
    public int delete(User user) throws SQLException
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_DELETE);
            
            pstmt.setInt(1, user.getIdUser());
            
            result = pstmt.executeUpdate();
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                if(this.transConnection == null)
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
