package data;

import domain.UserDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Facundo
 */
public interface IUserDAO 
{
    /**
     * select all users from a table and return them as a collection (List).
     * 
     * @return List   Users list.
     * @throws java.sql.SQLException
     */
    public List<UserDTO> select() throws SQLException;
    
    /**
     * Inserts an user on a table.
     * 
     * @param user  user to be added.
     * @return      quantity of inserted users.
     * @throws java.sql.SQLException
     */    
    public int insert(UserDTO user) throws SQLException;
    
    /**
     * updates an user information on a table.
     * 
     * @param user  user to be updated.
     * @return      quantity of modified users.
     * @throws java.sql.SQLException
     */
    public int update(UserDTO user) throws SQLException;
    
    /**
     * Deletes an user from a table.
     * 
     * @param user  user to delete
     * @return      quantity of users deleted
     * @throws java.sql.SQLException
     */
    public int delete(UserDTO user) throws SQLException;
}
