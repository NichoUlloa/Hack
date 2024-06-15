<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 15-06-2024
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.hack.hack.model.Participante" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Participantes</title>
    <link rel="stylesheet" type="text/css" href="estilos.css">
</head>
<body>
<header>
    <h1>Gestión de Participantes</h1>
</header>
<main>
    <section>
        <h2>Lista de Participantes</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre Completo</th>
                <th>Correo</th>
                <th>Número de Contacto</th>
                <th>RUT</th>
                <th>Fecha Asignada</th>
                <th>Código de Entrada</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Participante> participantes = (List<Participante>) request.getAttribute("participantes");
                for (Participante participante : participantes) {
            %>
            <tr>
                <td><%= participante.getIdParticipante() %></td>
                <td><%= participante.getNombreCompleto() %></td>
                <td><%= participante.getCorreo() %></td>
                <td><%= participante.getNumeroContacto() %></td>
                <td><%= participante.getRut() %></td>
                <td><%= participante.getFechaAsignada() %></td>
                <td><%= participante.getCodigoEntrada() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>
</main>
<footer>
    <p>&copy; 2023 Hackathon</p>
</footer>
</body>
</html>
