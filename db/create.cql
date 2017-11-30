
---------------------
--  home_security  --
---------------------

CREATE KEYSPACE home_security WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE home_security;

CREATE TABLE home (
    home_id text PRIMARY KEY,
    address text,
    alt_phone text,
    city text,
    contact_name text,
    email text,
    guest_code text,
    main_code text,
    phone text,
    phone_password text,
    state text,
    zip text
);

COPY home (home_id, address, city, state, zip, contact_name, phone, alt_phone, phone_password, email, main_code, guest_code) FROM 'home.csv' WITH header = true AND delimiter = '|';

CREATE TABLE activity (
    home_id text,
    datetime timestamp,
    code_used text,
    event text,
    PRIMARY KEY (home_id, datetime)
) WITH CLUSTERING ORDER BY (datetime DESC);

COPY activity (home_id, datetime, event, code_used) FROM 'activity.csv' WITH header = true AND delimiter = '|';


-----------------------
--  vehicle_tracker  --
-----------------------

CREATE KEYSPACE vehicle_tracker WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE vehicle_tracker;

CREATE TABLE location (
    vehicle_id text,
    date text,
    time timestamp,
    latitude double,
    longitude double,
    PRIMARY KEY ((vehicle_id, date), time)
) WITH CLUSTERING ORDER BY (time DESC);

COPY location (vehicle_id, date, time, latitude, longitude) FROM 'location.csv' WITH header = true AND delimiter = '|';
