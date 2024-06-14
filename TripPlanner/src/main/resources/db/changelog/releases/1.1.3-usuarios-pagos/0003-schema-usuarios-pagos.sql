--liquibase formatted sql

--changeset tripPlanner:0011-schema-RelacionUsuariosPagos
CREATE TABLE Usuarios_Pagos (
    id_usuario INTEGER NOT NULL,
    id_pago INTEGER NOT NULL,
    pagador BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id_usuario, id_pago),
    CONSTRAINT fk_usuario_pago_usuario FOREIGN KEY (id_usuario) REFERENCES Usuarios(id),
    CONSTRAINT fk_usuario_pago_pago FOREIGN KEY (id_pago) REFERENCES Pagos(id)
);
