DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS adresses CASCADE;
DROP TABLE IF EXISTS chats CASCADE;
DROP TABLE IF EXISTS helps CASCADE;
DROP TABLE IF EXISTS questions CASCADE;
DROP TABLE IF EXISTS answers CASCADE;
DROP TABLE IF EXISTS users_helps CASCADE;
DROP TABLE IF EXISTS helps_answers CASCADE;

CREATE TABLE adresses (
                          id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,

                          description VARCHAR(512) NOT NULL,
                          coordinate_x VARCHAR(20),
                          coordinate_y VARCHAR(20)
);
CREATE TABLE users (
                       id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       name VARCHAR(80) NOT NULL,
                       cpf VARCHAR(20) NOT NULL,
                       email VARCHAR(256) NOT NULL UNIQUE,
                       password VARCHAR(512) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       address_id BIGINT REFERENCES adresses (id)
);
CREATE TABLE chats(
                      id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
                      send_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      text VARCHAR(150) NOT NULL
);
CREATE TABLE answers (
                         id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                         name VARCHAR(25) NOT NULL,
                         type_question INT NOT NULL
);
CREATE TABLE helps (
                       id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
                       address_id BIGINT REFERENCES adresses (id),
                       description VARCHAR(300),
                       phone_number VARCHAR(20),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       urgency_type_id BIGINT REFERENCES answers (id),
                       help_type_id BIGINT REFERENCES answers (id),
                       expiration_time_id BIGINT REFERENCES answers (id),
                       number_volunteers_id BIGINT REFERENCES answers (id)
);
CREATE TABLE users_helps (
                             id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
                             help_id BIGINT REFERENCES helps (id) ON DELETE CASCADE
);

CREATE TABLE tools_types (
                             id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             name VARCHAR(100) NOT NULL
);
CREATE TABLE helps_tools_types(
                                  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                  tool_type_id BIGINT REFERENCES tools_types (id) ON DELETE CASCADE,
                                  help_id BIGINT REFERENCES helps (id) ON DELETE CASCADE
);


