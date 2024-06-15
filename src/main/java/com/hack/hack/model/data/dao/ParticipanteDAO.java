package com.hack.hack.model.data.dao;

import com.hack.hack.model.Participante;
import com.hack.hack.model.data.DBConnector;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class ParticipanteDAO {

    public void agregarParticipante(Participante participante) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            create.insertInto(table(name("Participante")),
                            DSL.field(name("nombreCompleto")),
                            DSL.field(name("contrasena")),
                            DSL.field(name("correo")),
                            DSL.field(name("numeroContacto")),
                            DSL.field(name("Rut")),
                            DSL.field(name("fechaAsignada")),
                            DSL.field(name("codigoEntrada")))
                    .values(participante.getNombreCompleto(), participante.getContrasena(), participante.getCorreo(), participante.getNumeroContacto(), participante.getRut(), participante.getFechaAsignada(), participante.getCodigoEntrada())
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Participante obtenerParticipantePorCorreo(String correo) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            Record record = create.select()
                    .from(table(name("Participante")))
                    .where(DSL.field(name("correo")).eq(correo))
                    .fetchOne();

            if (record != null) {
                return new Participante(
                        record.get("idParticipante", Integer.class),
                        record.get("nombreCompleto", String.class),
                        record.get("contrasena", String.class),
                        record.get("correo", String.class),
                        record.get("numeroContacto", String.class),
                        record.get("Rut", String.class),
                        record.get("fechaAsignada", java.sql.Date.class),
                        record.get("codigoEntrada", String.class)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Participante> obtenerTodosLosParticipantes() throws ClassNotFoundException {
        List<Participante> participantes = new ArrayList<>();
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            Result<Record> result = create.select().from(table(name("Participante"))).fetch();

            for (Record record : result) {
                participantes.add(new Participante(
                        record.get("idParticipante", Integer.class),
                        record.get("nombreCompleto", String.class),
                        record.get("contrasena", String.class),
                        record.get("correo", String.class),
                        record.get("numeroContacto", String.class),
                        record.get("Rut", String.class),
                        record.get("fechaAsignada", java.sql.Date.class),
                        record.get("codigoEntrada", String.class)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return participantes;
    }

    public void actualizarParticipante(Participante participante) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            create.update(table(name("Participante")))
                    .set(DSL.field(name("nombreCompleto")), participante.getNombreCompleto())
                    .set(DSL.field(name("contrasena")), participante.getContrasena())
                    .set(DSL.field(name("correo")), participante.getCorreo())
                    .set(DSL.field(name("numeroContacto")), participante.getNumeroContacto())
                    .set(DSL.field(name("Rut")), participante.getRut())
                    .set(DSL.field(name("fechaAsignada")), participante.getFechaAsignada())
                    .set(DSL.field(name("codigoEntrada")), participante.getCodigoEntrada())
                    .where(DSL.field(name("idParticipante")).eq(participante.getIdParticipante()))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarParticipante(int idParticipante) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            create.delete(table(name("Participante")))
                    .where(DSL.field(name("idParticipante")).eq(idParticipante))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validarParticipante(String correo, String contrasena) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            Record record = create.select()
                    .from(table(name("Participante")))
                    .where(DSL.field(name("correo")).eq(correo))
                    .and(DSL.field(name("contrasena")).eq(contrasena))
                    .fetchOne();

            return record != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
