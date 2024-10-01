<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2e9df; /* Light brown background */
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff9f2; /* Creamy white container */
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #5c3a1c; /* Dark brown text */
        }
        form {
            margin-top: 30px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        label {
            margin-bottom: 10px;
            font-weight: bold;
            color: #5c3a1c; /* Dark brown text */
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #b1814d; /* Light brown border */
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 50%;
            padding: 10px;
            background-color: #8c6239; /* Dark brown submit button */
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #714725; /* Darker brown on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Enter Your Details</h1>
        <form action="/Details" method="POST">
            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" placeholder="Enter your phone number">
            <label for="preferences">Preferences:</label>
            <input type="text" id="preferences" name="preferences" placeholder="Enter your preferences">
            <label for="allergies">Allergies:</label>
            <input type="text" id="allergies" name="allergies" placeholder="Enter your allergies">
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>