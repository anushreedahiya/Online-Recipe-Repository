<!--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
-->
<!DOCTYPE html>
<html>
    <head>
        <title>Recipe Results</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                background-image: url('https://t3.ftcdn.net/jpg/06/72/45/22/360_F_672452282_37w8WlNXxxgY0hxCNKEMTTj03IQE5ong.jpg'); /* Add your background image URL here */
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                background-attachment: fixed;
                color: #333;
                text-align: center;
                padding: 20px;
            }
            h1 {
                color: #4A90E2;
            }
            ul {
                list-style-type: none;
                padding: 0;
                max-width: 600px; /* Set max-width for better alignment */
                margin: auto; /* Center the list */
            }
            li {
                background-color: #ffffff; /* Solid white background */
                border: 1px solid #ddd;
                margin-top: 8px;
                padding: 12px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                position: relative;
            }
            a {
                color: #5C9210;
                text-decoration: none;
                font-weight: bold;
                position: absolute;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
            }
            a:hover {
                text-decoration: underline;
            }
            p {
                font-size: 16px;
                color: #666;
            }
        </style>
    </head>
    <body>
        <h1 style="color:white;">Recipe Results</h1>
        <c:choose>
            <c:when test="${not empty recipes}">
                <ul>
                    <c:forEach var="recipe" items="${recipes}">
                        <li>${recipe} <a href="/Recipe?recipeName=${recipe}">View Details</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>No recipes found for the given ingredients.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>