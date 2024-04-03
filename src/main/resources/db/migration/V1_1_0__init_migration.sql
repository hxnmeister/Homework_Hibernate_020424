CREATE TABLE IF NOT EXISTS clients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    contact_phone VARCHAR(13)
);

CREATE TABLE IF NOT EXISTS landlords (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    contact_phone VARCHAR(13)
);

CREATE TABLE IF NOT EXISTS apartments (
    id SERIAL PRIMARY KEY,
    rooms_count INTEGER,
    district VARCHAR(25),
    price MONEY,
    landlord_id INTEGER,

    FOREIGN KEY (landlord_id) REFERENCES landlords(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS requests_for_renting_apartment (
    id SERIAL PRIMARY KEY,
    client_id INTEGER,
    apartment_id INTEGER,

    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (apartment_id) REFERENCES apartments(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS renting_history (
    id SERIAL PRIMARY KEY,
    rent_begin DATE,
    rent_end DATE,
    client_id INTEGER,
    apartment_id INTEGER,

    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (apartment_id) REFERENCES apartments(id) ON DELETE CASCADE
);