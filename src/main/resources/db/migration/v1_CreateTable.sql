-- Table: plant_detail
CREATE TABLE plant_detail (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    scientific_name VARCHAR(255),
    species VARCHAR(255),
    grow_environment VARCHAR(50),
    price DOUBLE PRECISION
);

-- Table: plant_care_log
CREATE TABLE plant_care_log (
    id SERIAL PRIMARY KEY,
    care_type VARCHAR(255),
    watering_frequency VARCHAR(255),
    fertilizer_schedule VARCHAR(255),
    notes TEXT,
    performed_by VARCHAR(255),
    plant_id INTEGER NOT NULL,
    CONSTRAINT fk_plant_detail
        FOREIGN KEY (plant_id)
        REFERENCES plant_detail (id)
        ON DELETE CASCADE
);
