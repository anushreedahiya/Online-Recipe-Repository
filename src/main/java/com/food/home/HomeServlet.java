package main.java.com.food.home;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.food.config.ConnectorDB;
import com.food.table.User;
import com.food.table.Ingredients;
import com.food.table.Recipe;
import com.food.table.Recipe_Ingredients;
import com.food.table.Feedback;
import com.food.data.UserDAO;
import com.food.data.UserInterface;
import jakarta.servlet.RequestDispatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@WebServlet(name = "HomeServlet", urlPatterns = {"/Homepage", "/Signup", "/Login", "/Menu", "/Details", "/Recipe"})
public class HomeServlet extends HttpServlet {

    private UserDAO userDAO = UserDAO.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        switch (path) {
            case "/Homepage":
            case "/":
                request.getRequestDispatcher("/WEB-INF/JSP/Homepage.jsp").forward(request, response);
                break;
            case "/Signup":
                if ("GET".equalsIgnoreCase(request.getMethod())) {
                    request.getRequestDispatcher("/WEB-INF/JSP/Signup.jsp").forward(request, response);
                } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                    int i = doSignup(request, response);
                    if (i == 1) {
                        response.sendRedirect(request.getContextPath() + "/Details"); // Corrected the redirect path
                    } else {
                        request.getRequestDispatcher("/WEB-INF/JSP/Signup.jsp").forward(request, response);
                    }
                }
                break;
            case "/Login":
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    doLogin(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/JSP/Login.jsp").forward(request, response);
                }
                break;
            case "/Details":
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    int i = saveUserDetails(request, response);
                    if (i == 0) {
                        response.sendRedirect(request.getContextPath() + "/Menu");
                    } else {
                        request.getRequestDispatcher("/WEB-INF/JSP/Details.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/WEB-INF/JSP/Details.jsp").forward(request, response);
                }
                break;

            case "/Menu":
                if ("POST".equalsIgnoreCase(request.getMethod()) && "searchRecipes".equals(request.getParameter("action"))) {
                    int i = findRecipesByIngredient(request, response);
                    if (i == 0) {
                    }
                } else {
                    request.getRequestDispatcher("/WEB-INF/JSP/Menu.jsp").forward(request, response);
                }
                break;

            case "/Recipe":
                String dta=request.getParameter("recipeName");
                System.out.println(dta);
                HashMap<String, String> output= UserDAO.getInstance().getRecipeById(dta);
                request.setAttribute("recipes", output);
                request.getRequestDispatcher("/WEB-INF/JSP/recipeDetailsPage.jsp").forward(request, response);

            default:
                request.getRequestDispatcher("/WEB-INF/JSP/Homepage.jsp").forward(request, response);
                break;
        }
    }

    protected int doSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        System.out.println("Received email: " + email + ", password: " + password);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            return 0;
        }

       

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            return 0;
        }

        if (userDAO.checkUserExists(email)) {
            request.setAttribute("errorMessage", "User with this email already exists.");
            return 0;
        }

        if (userDAO.createUser(name, email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            return 1;
        } else {
            request.setAttribute("errorMessage", "Failed to create user. Please try again.");
            return 0;
        }
    }

    protected int doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userDAO.authenticateUser(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath() + "/Menu");

            return 0;
        } else {
            request.setAttribute("errorMessage", "Invalid credentials. Please try again.");
            request.getRequestDispatcher("/WEB-INF/JSP/Login.jsp").forward(request, response);

            return 1;
        }
    }

    protected int saveUserDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        String phone = request.getParameter("phone");
        String preferences = request.getParameter("preferences");
        String allergies = request.getParameter("allergies");

        if (userDAO.updateUserDetails(email, phone, preferences, allergies)) {
            return 0;
        } else {
            request.setAttribute("errorMessage", "Unable to update details. Please try again.");
            return 1;
        }
    }

    protected int findRecipesByIngredient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String ingredientInput = request.getParameter("ingredient");
    if (ingredientInput != null && !ingredientInput.trim().isEmpty()) {
        // Split the ingredient input by commas and trim any whitespace
        String cleanedIngredients = ingredientInput.trim().replaceAll("\\s*,\\s*", ",");

        // Call the stored procedure through UserDAO
        List<String> recipes = userDAO.findRecipesByStoredProc(cleanedIngredients);

        if (!recipes.isEmpty()) {
            request.setAttribute("recipes", recipes);
            request.getRequestDispatcher("/WEB-INF/JSP/Recipe.jsp").forward(request, response);
            return 0;
        } else {
            request.setAttribute("noRecipes", "There are no recipes for the given ingredients.");
            request.getRequestDispatcher("/WEB-INF/JSP/Recipe.jsp").forward(request, response);
            return 1;
        }
    } else {
        request.setAttribute("error", "Please provide one or more ingredients.");
        request.getRequestDispatcher("/WEB-INF/JSP/Menu.jsp").forward(request, response);
        return 1;
    }
}


    /*protected int findRecipesByIngredient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ingredientInput = request.getParameter("ingredient");
        if (ingredientInput != null && !ingredientInput.trim().isEmpty()) {
            List<String> ingredients = Arrays.asList(ingredientInput.split("\\s*,\\s*"));
            List<String> recipes = userDAO.findRecipesByIngredient(ingredients);

            if (!recipes.isEmpty()) {
                request.setAttribute("recipes", recipes);
                request.getRequestDispatcher("/WEB-INF/JSP/Recipe.jsp").forward(request, response);
                return 0;
            } else {
                request.setAttribute("noRecipes", "There are no recipes for the given ingredients.");
                request.getRequestDispatcher("/WEB-INF/JSP/Recipe.jsp").forward(request, response);
                return 1;
            }
        } else {
            request.setAttribute("error", "Please provide one or more ingredients.");
            request.getRequestDispatcher("/WEB-INF/JSP/Menu.jsp").forward(request, response);
            return 1;
        }
    }*/

    /*protected int fetchRecipes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String recipeId = request.getParameter("id");
    try {
        if (recipeId != null) {
            Recipe recipe = UserDAO.getRecipeById(recipeId);
            if (recipe != null) {
                request.setAttribute("recipe", recipe);
                request.getRequestDispatcher("/WEB-INF/jsp/RecipeDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("noRecipeFound.jsp"); // Redirect if no recipe is found
            }
        } else {
            response.sendRedirect("errorPage.jsp"); // Redirect if ID is not provided
        }
    } catch (Exception e) {
        response.sendRedirect("errorPage.jsp"); // Redirect on exception
    }
}*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "HomeServlet handles user authentication and redirection to appropriate views.";
    }
}