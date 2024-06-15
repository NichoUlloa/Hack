package com.hack.hack.controller;

import com.hack.hack.model.Supervisor;
import com.hack.hack.model.data.dao.ParticipanteDAO;
import com.hack.hack.model.data.dao.SupervisorDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "supervisorServlet", value = "/supervisor")
public class SupervisorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("correoSupervisor") == null) {
            resp.sendRedirect("loginSupervisor.jsp");
            return;
        }

        if ("gestionar".equals(action)) {
            ParticipanteDAO participanteDAO = new ParticipanteDAO();
            try {
                req.setAttribute("participantes", participanteDAO.obtenerTodosLosParticipantes());
                RequestDispatcher dispatcher = req.getRequestDispatcher("gestionarParticipantes.jsp");
                dispatcher.forward(req, resp);
            } catch (ClassNotFoundException e) {
                throw new ServletException("Error managing participants", e);
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        SupervisorDAO supervisorDAO = new SupervisorDAO();

        if ("login".equals(action)) {
            String correo = req.getParameter("correo");
            String contrasena = req.getParameter("contrasena");

            try {
                boolean esValido = supervisorDAO.validarSupervisor(correo, contrasena);
                if (esValido) {
                    HttpSession session = req.getSession();
                    session.setAttribute("correoSupervisor", correo);
                    resp.sendRedirect("gestionarParticipantes.jsp");
                } else {
                    resp.sendRedirect("loginSupervisor.jsp?error=1");
                }
            } catch (ClassNotFoundException e) {
                throw new ServletException("Error logging in supervisor", e);
            }
        } else if ("agregarFecha".equals(action)) {
            int idParticipante = Integer.parseInt(req.getParameter("idParticipante"));
            String fechaAsignada = req.getParameter("fechaAsignada");

            try {
                supervisorDAO.agregarFechaParticipante(idParticipante, fechaAsignada);
                resp.sendRedirect("gestionarParticipantes.jsp");
            } catch (ClassNotFoundException e) {
                throw new ServletException("Error adding date to participant", e);
            }
        }
    }
}
