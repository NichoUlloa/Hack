
<%@ page import="com.hack.hack.model.Participante" %>
<%
    Participante participante = (Participante) session.getAttribute("participante");
    if (participante == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil de Participante</title>
</head>
<body>
<h2>Perfil de Participante</h2>
<p>Nombre Completo: <%= participante.getNombreCompleto() %></p>
<p>Correo: <%= participante.getCorreo() %></p>
<p>Número de Contacto: <%= participante.getNumeroContacto() %></p>
<p>RUT: <%= participante.getRut() %></p>
<p>Fecha Asignada: <%= participante.getFechaAsignada() != null ? participante.getFechaAsignada() : "No asignada" %></p>
<p>Código de Entrada: <%= participante.getCodigoEntrada() != null ? participante.getCodigoEntrada() : "No generado" %></p>
<form action="participante" method="get">
    <input type="hidden" name="action" value="modificarContacto">
    <label for="nuevoContacto">Nuevo Número de Contacto:</label>
    <input type="text" id="nuevoContacto" name="nuevoContacto" required><br>
    <button type="submit">Modificar Contacto</button>
</form>
</body>
</html>
