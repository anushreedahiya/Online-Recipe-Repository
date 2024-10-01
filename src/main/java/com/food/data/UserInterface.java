package main.java.com.food.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.food.config.ConnectorDB;
import com.food.table.User;
import com.food.table.Ingredients;
import com.food.table.Recipe;
import com.food.table.Recipe_Ingredients;
import com.food.table.Feedback;
import com.food.data.UserDAO;

public interface UserInterface {
   boolean authenticateUser(String email, String password);

    boolean createUser(String name, String email, String password);

    boolean checkUserExists(String email);

    boolean updateUserDetails(String email, String phone, String preferences, String allergies);
        
    List<String> findRecipesByStoredProc(String ingredientList);
}