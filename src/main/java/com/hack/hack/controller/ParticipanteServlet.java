package com.hack.hack.controller;

import com.hack.hack.model.Participante;
import com.hack.hack.model.data.DBGenerator;
import com.hack.hack.model.data.dao.ParticipanteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "participanteServlet", value = "/participante")
public class ParticipanteServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("hackathon");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error initializing database", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("correo") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        if ("perfil".equals(action)) {
            String correo = (String) session.getAttribute("correo");
            try {
                ParticipanteDAO participanteDAO = new ParticipanteDAO();
                Participante participante = participanteDAO.obtenerParticipantePorCorreo(correo);
                session.setAttribute("participante", participante);
                RequestDispatcher dispatcher = req.getRequestDispatcher("perfil.jsp");
                dispatcher.forward(req, resp);
            } catch (ClassNotFoundException e) {
                throw new ServletException("Error obtaining participant profile", e);
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/index.jsp");
        String action = req.getParameter("action");
        ParticipanteDAO participanteDAO = new ParticipanteDAO();

        if ("registrar".equals(action)) {
            String nombre = req.getParameter("nombre");
            String contrasena = req.getParameter("contrasena");
            String correo = req.getParameter("correo");
            String contacto = req.getParameter("contacto");
            String rut = req.getParameter("rut");

            Participante participante = new Participante(0, nombre, contrasena, correo, contacto, rut, null, null);
            try {
                participanteDAO.agregarParticipante(participante);
                respuesta= req.getRequestDispatcher("/index.jsp");
            } catch (ClassNotFoundException e) {
                throw new ServletException("Error registering participant", e);
            }
        } else if ("ingresar".equals(action)) {
            System.out.println("Ingresando");
            String correo = req.getParameter("correo");
            String contrasena = req.getParameter("contrasena");

            try {
                boolean esValido = participanteDAO.validarParticipante(correo, contrasena);
                if (esValido) {
                    System.out.println("es valido");
                    //HttpSession session = req.getSession();
                    //session.setAttribute("correo", correo);
                    req.setAttribute("participante",participanteDAO.obtenerParticipantePorCorreo(correo));
                    respuesta = req.getRequestDispatcher("/perfil.jsp");
                    //resp.sendRedirect("perfil.jsp?action=perfil");
                } //else {
                    //resp.sendRedirect("login.jsp?error=1");
                //}
            } catch (ClassNotFoundException e) {
                throw new ServletException("Error logging in participant", e);
            }

        }
        respuesta.forward(req,resp);
    }
}
