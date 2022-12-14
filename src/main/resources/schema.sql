CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    tg_id     BIGINT                                  NOT NULL,
    auth_date DATE                                    NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS meals
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    type         VARCHAR(16)                             NOT NULL,
    name         VARCHAR(64)                             NOT NULL,
    protein      DOUBLE PRECISION DEFAULT 0,
    carbohydrate DOUBLE PRECISION DEFAULT 0,
    fat          DOUBLE PRECISION DEFAULT 0,
    fiber        DOUBLE PRECISION DEFAULT 0,
    alcohol      DOUBLE PRECISION DEFAULT 0,
    calories     BIGINT DEFAULT 0,
    user_id      BIGINT                                  NOT NULL,
    added        DATE                                    NOT NULL,
    CONSTRAINT pk_events PRIMARY KEY (id),
    CONSTRAINT fk_meals_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS fk_meals_index_user_id ON meals (user_id);


CREATE TABLE IF NOT EXISTS complaints
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    description VARCHAR(1024)                           NOT NULL,
    initiator   BIGINT                                  NOT NULL,
    CONSTRAINT pk_complaints PRIMARY KEY (id),
    CONSTRAINT fk_complaints_initiator FOREIGN KEY (initiator) REFERENCES users (id) ON DELETE CASCADE

);

CREATE INDEX IF NOT EXISTS fk_complaints_index_initiator ON complaints (initiator);

