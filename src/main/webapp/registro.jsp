<!DOCTYPE html>
<html>
<head>
    <title>Registro de Participante</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<header>
    <h1>Registro de Participante</h1>
</header>
<main>
    <form action="participante" method="post">
        <input type="hidden" name="action" value="registrar">
        <label for="nombre">Nombre Completo:</label>
        <input type="text" id="nombre" name="nombre" required>
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" required>
        <label for="contacto">Número de Contacto:</label>
        <input type="text" id="contacto" name="contacto" required>
        <label for="rut">RUT:</label>
        <input type="text" id="rut" name="rut" required>
        <button type="submit">Registrarse</button>
    </form>
</main>
<footer>
    <p>&copy; 2023 Hackathon</p>
</footer>
</body>
</html>
