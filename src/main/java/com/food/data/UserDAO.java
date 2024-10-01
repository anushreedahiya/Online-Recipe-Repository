package main.java.com.food.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.food.config.ConnectorDB;
import com.food.table.User;
import com.food.table.Ingredients;
import com.food.table.Recipe;
import com.food.table.Recipe_Ingredients;
import com.food.table.Feedback;
import com.food.data.UserDAO;
import com.food.data.UserInterface;

public class UserDAO implements UserInterface {

    private static UserDAO instance;

    private UserDAO() {
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT Password FROM User WHERE Email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createUser(String name, String email, String password) {
        if (checkUserExists(email)) {
            return false;
        }
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement pr = connection.prepareStatement("INSERT INTO User (Name, Email, Password) VALUES (?, ?, ?)")) {
            pr.setString(1, name);
            pr.setString(2, email);
            pr.setString(3, password);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkUserExists(String email) {
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement pr = connection.prepareStatement("SELECT COUNT(*) FROM User WHERE Email = ?")) {
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserDetails(String email, String phone, String preferences, String allergies) {
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement pr = connection.prepareStatement("UPDATE User SET Phone_Number = ?, Preferences = ?, Allergies = ? WHERE Email = ?")) {
            pr.setString(1, phone);
            pr.setString(2, preferences);
            pr.setString(3, allergies);
            pr.setString(4, email);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

  /*  @Override
    public List<String> findRecipesByIngredient(List<String> ingredients) {
        List<String> recipes = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT DISTINCT Recipe.Name FROM Recipe ");
        sql.append("JOIN Recipe_Ingredients ON Recipe.Recipe_ID = Recipe_Ingredients.Recipe_ID ");
        sql.append("JOIN Ingredients ON Recipe_Ingredients.Ingredients_ID = Ingredients.Ingredients_ID ");
        sql.append("WHERE Ingredients.Name IN (");

        String placeholders = ingredients.stream()
                .map(ingredient -> "?")
                .collect(Collectors.joining(", "));
        sql.append(placeholders);
        sql.append(")");

        try (Connection conn = ConnectorDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            for (String ingredient : ingredients) {
                pstmt.setString(index++, ingredient);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    recipes.add(rs.getString("Name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }*/
    public HashMap<String, String> getRecipeById(String recipename) {
        HashMap<String, String> recipeData = new HashMap<>();
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Recipe WHERE Name = ?")) {
            pstmt.setString(1, recipename);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                recipeData.put("Recipe_ID", rs.getString("Recipe_ID"));
                recipeData.put("name", rs.getString("name"));
                recipeData.put("Skill_Level", rs.getString("Skill_Level"));
                recipeData.put("Prep_Time", rs.getString("Prep_Time"));

                recipeData.put("instructions", rs.getString("instructions"));

            }
        } catch (SQLException e) {
            System.out.println("Error fetching recipe: " + e.getMessage());
            e.printStackTrace();
        }
        return recipeData;
    }
    @Override
    public List<String> findRecipesByStoredProc(String ingredientList) {
    List<String> recipes = new ArrayList<>();
    try (Connection conn = ConnectorDB.getConnection(); CallableStatement cstmt = conn.prepareCall("{CALL FindRecipesByIngredient(?)}")) {
        cstmt.setString(1, ingredientList);
        try (ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                recipes.add(rs.getString("Name"));
            }
        }
    } catch (SQLException e) {
        System.out.println("Error executing stored procedure: " + e.getMessage());
        e.printStackTrace();
    }
    return recipes;
}


}