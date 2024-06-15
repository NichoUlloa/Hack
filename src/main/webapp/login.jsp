<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<header>
    <h1>Inicio de Sesión</h1>
</header>
<main>
    <form action="participante" method="post">
        <input type="hidden" name="action" value="ingresar">
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
