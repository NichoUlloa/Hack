package com.hack.hack.model.data;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaParticipante(create);
        crearTablaSupervisor(create);
        crearTablaBloqueado(create);
        DBConnector.closeConnection();
    }

    public static DSLContext conectarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection);
    }

    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection);
    }

    private static void crearTablaParticipante(DSLContext create) {
        create.createTableIfNotExists("Participante")
                .column("id", INTEGER.identity(true))
                .column("nombreCompleto", VARCHAR(100).notNull())
                .column("contrasena", VARCHAR(100).notNull())
                .column("correo", VARCHAR(100).notNull())
                .column("numeroContacto", VARCHAR(15).notNull())
                .column("rut", VARCHAR(15).notNull())
                .column("fechaAsignada", VARCHAR(10))
                .column("codigoEntrada", VARCHAR(10))
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaSupervisor(DSLContext create) {
        create.createTableIfNotExists("Supervisor")
                .column("idSupervisor", INTEGER.identity(true))
                .column("correo", VARCHAR(100).notNull())
                .column("contrasena", VARCHAR(100).notNull())
                .constraint(primaryKey("idSupervisor"))
                .execute();
    }

    private static void crearTablaBloqueado(DSLContext create) {
        create.createTableIfNotExists("Bloqueado")
                .column("correo", VARCHAR(100).notNull())
                .constraint(primaryKey("correo"))
                .execute();
    }
}
