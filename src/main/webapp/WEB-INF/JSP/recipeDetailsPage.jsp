<!DOCTYPE html>
<html>
    <head>
        <title>Recipe Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-image: url('https://t3.ftcdn.net/jpg/02/52/12/40/360_F_252124067_aCtp9ZD934RboKmjJzkXiwYDL7XkNjpn.jpg');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                color: #333;
                margin: 0;
                padding: 40px;
                height: 100vh; /* Use 100% of the viewport height */
                box-sizing: border-box;
            }
            .text-container {
                background-color: rgba(255, 255, 255, 0.9); /* Semi-transparent white background */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.2);
                margin: 20px auto;
                width: 80%;
                max-width: 600px;
            }
            h1, p {
                color: #444;
            }
            ul {
                background-color: transparent; /* Remove background from the list */
                padding: 20px;
                border-radius: 8px;
                list-style-type: none;
                margin-top: 20px;
            }
            li {
                margin-bottom: 10px;
                padding-left: 1.5em;
                text-indent: -1.5em;
            }
            li:before {
                content: counter(stepCounter) ". ";
                counter-increment: stepCounter;
            }
        </style>
    </head>
    <body>
        <div class="text-container">
            <h1>Recipe Details</h1>
            <c:if test="${not empty recipes}">
                <p><strong>Recipe Name:</strong> ${recipes.name}</p>
                <p><strong>Recipe ID:</strong> ${recipes.Recipe_ID}</p>
                <p><strong>Skill Level:</strong> ${recipes.Skill_Level}</p>
                <p><strong>Prep Time:</strong> ${recipes.Prep_Time}</p>
                <p><strong>Instructions:</strong></p>
                <ul id="instructionList"></ul>
            </c:if>
        </div>
        
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                var instructions = "${recipes.instructions}".trim();
                var steps = instructions.split(/(?=\d\.)/); // Splits the string at each number followed by a dot
                var listElement = document.getElementById('instructionList');

                steps.forEach(function(step) {
                    if (step.trim() !== '') {
                        var li = document.createElement('li');
                        li.textContent = step.trim();
                        listElement.appendChild(li);
                    }
                });
            });
        </script>
    </body>
</html>