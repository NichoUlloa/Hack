<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 15-06-2024
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión Supervisor</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<header>
    <h1>Inicio de Sesión Supervisor</h1>
</header>
<main>
    <form action="supervisor" method="post">
        <input type="hidden" name="action" value="login">
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" required>
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>
        <button type="submit">Ingresar</button>
    </form>
</main>
<footer>
    <p>&copy; 2023 Hackathon</p>
</footer>
</body>
</html>
