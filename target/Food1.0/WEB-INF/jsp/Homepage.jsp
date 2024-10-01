<!--<%@page contentType="text/html" pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to our project</title>
    <style>
        body {
            width: 100%;
            height: 100%;
            background-image: url('https://i.pinimg.com/564x/2c/c1/56/2cc15671ae9156431d8bcc36468f0bf6.jpg');
            background-size: cover;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
            font-family: 'Open Sans', sans-serif;
        }

        
        .center-content {
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white;
        }

        .center-content h1 {
            margin: 0.5em 0;
            font-size: 3em;
        }

        .link-button {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .link-button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
    <div class="center-content">
        <h1>Savour Swift</h1>
        <a href="/Login" class="link-button">Log In</a>
        <a href="/Signup" class="link-button">Sign Up</a>
        
        
    </div>
</body>
</html>