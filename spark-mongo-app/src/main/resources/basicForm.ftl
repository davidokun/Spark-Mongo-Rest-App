<html>
<head>

</head>

<body>

    <form action="/save_fruit" method="POST">

        <h3>Select your favorite fruit</h3>

        <#list fruits as fruit>
            <p>
                <input type="radio" name="fruit" value="${fruit}">${fruit}</input>
            </p>
        </#list>

        <input type="submit" value="Send" />

    </form>


</body>
</html>