CREATE USER test_root WITH PASSWORD 'test!' CREATEDB;
CREATE DATABASE db_chuck
    WITH 
    OWNER = test_root
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
