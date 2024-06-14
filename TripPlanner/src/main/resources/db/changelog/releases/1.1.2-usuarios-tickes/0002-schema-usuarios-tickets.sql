--liquibase formatted sql

--changeset tripPlanner:0002-schema-Usuarios-Tickets
CREATE TABLE Usuarios_Tickets (
    id_usuario INTEGER NOT NULL,
    id_ticket INTEGER NOT NULL,
    PRIMARY KEY (id_usuario, id_ticket),
    CONSTRAINT fk_usuario_ticket_usuario FOREIGN KEY (id_usuario) REFERENCES Usuarios(id),
    CONSTRAINT fk_usuario_ticket_ticket FOREIGN KEY (id_ticket) REFERENCES Tickets(id)
);
