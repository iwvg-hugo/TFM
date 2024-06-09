--liquibase formatted sql

--changeset tripPlanner:0010-schema-RelacionUsuariosViajes

CREATE TABLE RelacionUsuariosViajes (
    id_usuario INTEGER NOT NULL,
    id_viaje INTEGER NOT NULL,
    PRIMARY KEY (id_usuario, id_viaje),
    CONSTRAINT fk_usuario_viaje_usuario FOREIGN KEY (id_usuario) REFERENCES Usuarios(id),
    CONSTRAINT fk_usuario_viaje_viaje FOREIGN KEY (id_viaje) REFERENCES Viajes(id)
);
