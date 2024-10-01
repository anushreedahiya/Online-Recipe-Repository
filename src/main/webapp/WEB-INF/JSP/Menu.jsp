<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Recipe Search</title>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <style>
    body {
      background-image: url('https://st3.depositphotos.com/1001069/15222/i/450/depositphotos_152220526-stock-photo-cooking-utensils-and-tomatoes.jpg');
      background-size: cover;
      background-attachment: fixed;
      background-repeat: no-repeat;
      font-family: 'Ubuntu', sans-serif;
    }

    .main {
      background-color: rgba(255, 255, 255, 0.8);
      width: 60%;
      margin: 2em auto;
      border-radius: 1.5em;
      box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
      padding: 20px;
    }

    form {
      text-align: center;
      margin-top: 50px;
    }

    input[type="text"], button {
      width: 90%;
      padding: 10px;
      margin: 10px auto;
      display: block;
      border-radius: 20px;
      border: none;
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    input[type="text"] {
      background: rgba(255, 255, 255, 0.9);
      font-size: 16px;
    }

    button {
      background: linear-gradient(to right, #43A047, #66BB6A);
      color: white;
      font-size: 16px;
      cursor: pointer;
      font-weight: bold;
    }

    button:hover {
      opacity: 0.9;
    }
  </style>
</head>
<body>
  <div class="main">
    <form action="Menu" method="POST">
        <h1>Enter Ingredients</h1>
      <input type="text" name="ingredient" placeholder="Enter ingredients, e.g., 'Rice, Chicken .....'" id="ingredientInput">
      <input type="hidden" name="action" value="searchRecipes">
      <button type="submit">Search</button>
    </form>
  </div>
</body>
</html>