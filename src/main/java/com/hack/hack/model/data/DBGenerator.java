package com.hack.hack.model.data;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.constraint;
import static org.jooq.impl.DSL.primaryKey;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDatos(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaBloqueado(create);
        crearTablaParticipante(create);
        crearTablaSupervisor(create);
        DBConnector.closeConnection();
    }

    public static DSLContext conectarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection);
    }

    private static void crearBaseDatos(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection);
    }

    private static void crearTablaBloqueado(DSLContext create) {
        create.createTableIfNotExists("Bloqueado")
                .column("correo", VARCHAR(255).notNull())
                .constraint(primaryKey("correo"))
                .execute();
    }

    private static void crearTablaParticipante(DSLContext create) {
        create.createTableIfNotExists("Participante")
                .column("idParticipante", INTEGER.identity(true))
                .column("nombreCompleto", VARCHAR(255).notNull())
                .column("contrasena", VARCHAR(255).notNull())
                .column("correo", VARCHAR(255).notNull())
                .column("numeroContacto", VARCHAR(20).notNull())
                .column("Rut", VARCHAR(12).notNull())
                .column("fechaAsignada", DATE)
                .column("codigoEntrada", VARCHAR(10))
                .constraint(primaryKey("idParticipante"))
                .execute();

        // Agregar índice único para correo después de crear la tabla
        create.alterTable("Participante")
                .add(constraint("UNIQUE_CORREO").unique("correo"))
                .execute();
    }

    private static void crearTablaSupervisor(DSLContext create) {
        create.createTableIfNotExists("Supervisor")
                .column("idSupervisor", INTEGER.identity(true))
                .column("correo", VARCHAR(255).notNull())
                .column("contrasena", VARCHAR(255).notNull())
                .constraint(primaryKey("idSupervisor"))
                .execute();

        // Agregar índice único para correo después de crear la tabla
        create.alterTable("Supervisor")
                .add(constraint("UNIQUE_CORREO").unique("correo"))
                .execute();
    }
}
