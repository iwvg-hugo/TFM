--liquibase formatted sql

--changeset tripPlanner:0007-schema-Planes
CREATE TABLE Planes (
    id SERIAL PRIMARY KEY,
    id_viaje INTEGER NOT NULL,
    id_ubicacion INTEGER NOT NULL,
    id_horario INTEGER NOT NULL,
    id_pago INTEGER,
    nombre VARCHAR(255),
    importancia INTEGER,
    descripcion TEXT,
    CONSTRAINT fk_plan_viaje FOREIGN KEY (id_viaje) REFERENCES Viajes(id),
    CONSTRAINT fk_plan_ubicacion FOREIGN KEY (id_ubicacion) REFERENCES Ubicaciones(id),
    CONSTRAINT fk_plan_horario FOREIGN KEY (id_horario) REFERENCES Horarios(id)
);
