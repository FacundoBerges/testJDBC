package test;

import data.UserDAO;
import domain.User;
import java.util.List;

/**
 * @author Facundo
 */
public class TestJDBC {

    public static void main(String[] args) {
        UserDAO userDao = new UserDAO();
        
        //test select
        List<User> users = userDao.select();
        users.forEach(user -> {
            System.out.println(user);
        });
        System.out.println("");
        
        //test insert
        User userAdd = new User("Manuel", "123456");
        userDao.insert(userAdd);
        
        users = userDao.select();
        users.forEach(user -> {
            System.out.println(user);
        });
        System.out.println("");

        //test update
        User modifiedUser = new User(4, "Rick", "123456");
        userDao.update(modifiedUser);
        
        users = userDao.select();
        users.forEach(user -> {
            System.out.println(user);
        });
        System.out.println("");
        
        //test delete
        User toDeleteUser = new User(7);
        userDao.delete(toDeleteUser);
        
        users = userDao.select();
        users.forEach(user -> {
            System.out.println(user);
        });
        System.out.println("");
    }
}
