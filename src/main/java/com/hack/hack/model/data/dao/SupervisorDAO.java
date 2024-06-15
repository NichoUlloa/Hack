package com.hack.hack.model.data.dao;

import com.hack.hack.model.Supervisor;
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

public class SupervisorDAO {

    public void agregarSupervisor(Supervisor supervisor) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            create.insertInto(table(name("Supervisor")),
                            DSL.field(name("correo")),
                            DSL.field(name("contrasena")))
                    .values(supervisor.getCorreo(), supervisor.getContrasena())
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Supervisor obtenerSupervisorPorCorreo(String correo) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            Record record = create.select()
                    .from(table(name("Supervisor")))
                    .where(DSL.field(name("correo")).eq(correo))
                    .fetchOne();

            if (record != null) {
                return new Supervisor(
                        record.get("idSupervisor", Integer.class),
                        record.get("correo", String.class),
                        record.get("contrasena", String.class)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Supervisor> obtenerTodosLosSupervisores() throws ClassNotFoundException {
        List<Supervisor> supervisores = new ArrayList<>();
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            Result<Record> result = create.select().from(table(name("Supervisor"))).fetch();

            for (Record record : result) {
                supervisores.add(new Supervisor(
                        record.get("idSupervisor", Integer.class),
                        record.get("correo", String.class),
                        record.get("contrasena", String.class)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supervisores;
    }

    public void actualizarSupervisor(Supervisor supervisor) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            create.update(table(name("Supervisor")))
                    .set(DSL.field(name("correo")), supervisor.getCorreo())
                    .set(DSL.field(name("contrasena")), supervisor.getContrasena())
                    .where(DSL.field(name("idSupervisor")).eq(supervisor.getIdSupervisor()))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarSupervisor(int idSupervisor) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            create.delete(table(name("Supervisor")))
                    .where(DSL.field(name("idSupervisor")).eq(idSupervisor))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validarSupervisor(String correo, String contrasena) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            Record record = create.select()
                    .from(table(name("Supervisor")))
                    .where(DSL.field(name("correo")).eq(correo))
                    .and(DSL.field(name("contrasena")).eq(contrasena))
                    .fetchOne();

            return record != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarFechaParticipante(int idParticipante, String fechaAsignada) throws ClassNotFoundException {
        try (Connection connection = DBConnector.connection("hackathon", "root", "")) {
            DSLContext create = DSL.using(connection);
            create.update(table(name("Participante")))
                    .set(DSL.field(name("fechaAsignada")), java.sql.Date.valueOf(fechaAsignada))
                    .where(DSL.field(name("idParticipante")).eq(idParticipante))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
