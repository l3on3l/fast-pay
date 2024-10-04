# Sistema de Pagos de Servicios

Este proyecto es un API REST desarrollado con Spring Boot que permite gestionar los pagos de servicios públicos y privados. Los usuarios pueden realizar pagos, consultar el estado de sus facturas y llevar un historial de sus transacciones.

![fast-pay](https://github.com/l3on3l/fast-pay/blob/main/diagram.jpg)

## Tecnologías
- **Java**: Versión SDK 17.
- **Maven**: Gestor de dependencias.
- **Spring Boot**: Framework para construir la API REST.
- **PostgreSQL**: Para la persisntencia de datos.
- **Hibernate**: ORM para manejar la persistencia de datos.
   
## Configuración de la Base de Datos
Asegúrate de tener PostgreSQL en funcionamiento. 
Crea una base de datos:
```bash
CREATE DATABASE <nombre_de_tu_base_de_datos>;
```

Conéctate a la base de datos recién creada:
```bash
\c <nombre_de_tu_base_de_datos>;
```

Crear las relaciones de las tablas:
```
DROP TYPE IF EXISTS service_type;
DROP TYPE IF EXISTS invoice_status;
DROP TYPE IF EXISTS payment_option;
DROP TYPE IF EXISTS payment_status;

-- Tabla de usuarios
CREATE TABLE users (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name text NOT NULL,
    last_name text NOT NULL,
    email text UNIQUE NOT NULL,
    password_hash text NOT NULL,
    balance numeric DEFAULT 0 NOT NULL,
    username text UNIQUE NOT NULL
);

-- Tabla de proveedores de servicios
CREATE TYPE service_type AS ENUM ('PRIVATE', 'PUBLIC');

CREATE TABLE service_providers (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL,
    type service_type,
    service_code text UNIQUE NOT NULL
);

-- Tabla de facturas
CREATE TYPE invoice_status AS ENUM ('PENDING', 'COMPLETED');
CREATE TYPE payment_option AS ENUM ('INSTALLMENT', 'FULL');
CREATE TYPE payment_status AS ENUM ('PENDING', 'COMPLETED');

CREATE TABLE invoices (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint REFERENCES users(id),
    service_id bigint REFERENCES service_providers(id),
    amount numeric NOT NULL,
    due_date date NOT NULL,
    remaining_amount numeric DEFAULT 0 NOT NULL,
    reference text NOT NULL,
    status payment_status DEFAULT 'PENDING',
    payment_option payment_option DEFAULT 'FULL',
    created_at date NOT NULL
);

-- Tabla de pagos
CREATE TABLE payments (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    invoice_id bigint REFERENCES invoices(id),
    payment_date date DEFAULT CURRENT_DATE NOT NULL,
    total_amount numeric NOT NULL,
    paid_amount numeric NOT NULL
);

-- Tabla de sesiones
CREATE TABLE sessions (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint REFERENCES users(id),
    session_token text UNIQUE NOT NULL,
    created_at timestamp with time zone DEFAULT now(),
    expires_at timestamp with time zone NOT NULL
);

-- Tabla de cuotas
CREATE TABLE installments (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    invoice_id bigint REFERENCES invoices(id),
    installment_number integer NOT NULL,
    due_date date NOT NULL,
    amount numeric NOT NULL,
    status invoice_status DEFAULT 'PENDING',
    created_at timestamp with time zone DEFAULT now()
);

```

Insertar los datos:
```
-- Insertar filas en la tabla users
insert into
  users (
    first_name,
    last_name,
    email,
    password_hash,
    balance,
    username
  )
values
  (
    'John',
    'Doe',
    'john.doe@example.com',
    'hashed_password_1',
    1000000.00,
    'johndoe'
  ),
  (
    'Jane',
    'Smith',
    'jane.smith@example.com',
    'hashed_password_2',
    500000.00,
    'janesmith'
  ),
  (
    'Alice',
    'Johnson',
    'alice.johnson@example.com',
    'hashed_password_3',
    200000.00,
    'alicejohnson'
  );

-- Insertar filas en la tabla service_providers
insert into
  service_providers (name, type, service_code)
values
  ('ANDE', 'PUBLIC','code-1'),
  ('Tigo', 'PRIVATE', 'code-2'),
  ('Personal Flow e Internet Hogar', 'PRIVATE', 'code-3'),
  ('Essap', 'PUBLIC', 'code-4'),
  ('Claro', 'PRIVATE', 'code-5'),
  ('IPS Prestamo', 'PUBLIC', 'code-6');

-- Insertar filas en la tabla invoices
insert into
  invoices (
    user_id,
    service_id,
    amount,
    due_date,
    remaining_amount,
    reference,
    status,
    payment_option,
    created_at
  )
values
  (
    1,
    1,
    250000.00,
    now(),
    0.00,
    '16204512',
    'COMPLETED',
    'FULL',
    '2024-07-02'
  ),
  (
    2,
    2,
    75000.00,
    now(),
    0.00,
    '0981222333',
    'COMPLETED',
    'FULL',
   '2024-07-21'
  ),
  (
    3,
    3,
    125000.00,
    now(),
    0.00,
    '5991233',
    'COMPLETED',
    'FULL',
    '2024-07-12'
  ),
  (
    2,
    2,
    75000.00,
    now(),
    75000.00,
    '122239',
    'PENDING',
    'FULL',
    now() - interval '10 days'
  ),
  (
    2,
    4,
    40000.00,
    now() + interval '20 days',
    40000.00,
    '122239',
    'PENDING',
    'FULL',
    '2024-03-09'
  ),
  (
    3,
    5,
    70000.00,
    now() + interval '20 days',
    70000.00,
    '0991222333',
    'PENDING',
    'FULL',
    '2024-07-19'
  ),
  (
    2,
    4,
    45000.00,
    now() + interval '20 days',
    45000.00,
    '122239',
    'PENDING',
    'FULL',
    '2024-10-04'
  ),
  (
    3,
    5,
    700000.00,
    now() + interval '20 days',
    700000.00,
    '0991222333',
    'PENDING',
    'FULL',
    '2024-10-04'
  ),
  (
    1,
    1,
    250000.00,
    now() + interval '20 days',
    250000.00,
    '16204512',
    'PENDING',
    'FULL',
    '2024-04-14'
  );
 
-- Insertar filas en la tabla payments
insert into
  payments (
    invoice_id,
    payment_date,
    total_amount,
    paid_amount
  )
values
  (1, now() - interval '7 days', 250000, 250000),
  (2, now() - interval '3 days', 75000, 75000),
  (3, now() - interval '5 days', 125000.00, 125000.00);
  
```

Configura las propiedades en application.properties:
```
  datasource:
    url: jdbc:postgresql://<host>:<puerto>/<nombre_de_tu_base_de_datos>
    username: <tu_usuario>
    password: <tu_contraseña>
```
Ejemplo con la configuración actual:
```
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: fabian
    password: pass1234
```

## Ejecutar la API:
Desde la raiz del proyecto ejecutar
```
./mvnw spring-boot:run
```

## Colección de Postman
Ir a la descarga. [Enlace](https://drive.google.com/file/d/1bU2Zz9CNl0HdaimiRX_SAjhl2tPpEHzP/view?usp=share_link)
