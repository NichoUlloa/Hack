<%@ page import="com.hack.hack.model.Participante" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("correo") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Participante participante = (Participante) request.getAttribute("participante");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil del Participante</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<header>
    <h1>Perfil del Participante</h1>
</header>
<main>
    <section>
        <h2>Información Personal</h2>
        <p><strong>Nombre Completo:</strong> <%= participante.getNombreCompleto() %></p>
        <p><strong>Correo:</strong> <%= participante.getCorreo() %></p>
        <p><strong>Número de Contacto:</strong> <%= participante.getNumeroContacto() %></p>
        <p><strong>RUT:</strong> <%= participante.getRut() %></p>
        <p><strong>Fecha Asignada:</strong> <%= participante.getFechaAsignada() %></p>
        <p><strong>Código de Entrada:</strong> <%= participante.getCodigoEntrada() %></p>
    </section>
</main>
<footer>
    <p>&copy; 2023 Hackathon</p>
</footer>
</body>
</html>
