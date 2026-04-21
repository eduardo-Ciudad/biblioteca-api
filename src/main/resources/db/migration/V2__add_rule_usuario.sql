ALTER TABLE usuarios ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'USER';

-- Seed do primeiro admin (senha: admin123 encodada com BCrypt)
INSERT INTO usuarios (nome, email, senha, role)
VALUES (
    'Administrador',
    'admin@biblioteca.com',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh9S',
    'ADMIN'
);

