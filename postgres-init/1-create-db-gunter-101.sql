CREATE USER gunter_root WITH PASSWORD 'gunter_pass' CREATEDB;
CREATE DATABASE db_gunter-101
    WITH 
    OWNER = gunter_root
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
