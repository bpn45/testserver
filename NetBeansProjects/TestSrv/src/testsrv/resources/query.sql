CREATE ROLE worktest LOGIN
  ENCRYPTED PASSWORD '111'
  NOSUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;

-- Database: testwork

-- DROP DATABASE testwork;

CREATE DATABASE testwork
  WITH OWNER = worktest
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'ru_UA.UTF-8'
       LC_CTYPE = 'ru_UA.UTF-8'
       CONNECTION LIMIT = -1;


-- Table: entities

-- DROP TABLE entities;

CREATE TABLE entities
(
  id bigserial NOT NULL,
  content character varying(1024),
  created timestamp without time zone,
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE entities
  OWNER TO worktest;



-- Создание триггерной функции 
-- id типа long определяется триггером  
-- Function: trigger_entities_before_lns()

--DROP FUNCTION trigger_entities_before_lns() CASCADE;

CREATE FUNCTION trigger_entities_before_lns () RETURNS trigger AS '
BEGIN
NEW.id=nextval(''entities_id_seq'');
NEW.content=NEW.content;
NEW.created = NEW.created;

return NEW;
END;
' LANGUAGE  plpgsql;

-- Создание триггера
CREATE TRIGGER entities_bi
BEFORE INSERT ON entities FOR EACH ROW
EXECUTE PROCEDURE trigger_entities_before_lns ()
OWNER TO worktest;

-- Table: config

-- DROP TABLE config;

CREATE TABLE config
(
  id bigint NOT NULL,
  source character varying(120), -- Source path of xml files
  destination character varying(120), -- Path to moving extracted files
  delay integer, -- Delay time in sec
  wrong character varying(120), -- Path for moving don't converted files
  CONSTRAINT "primary" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE config
  OWNER TO worktest;
COMMENT ON COLUMN config.source IS 'Source path for xml files';
COMMENT ON COLUMN config.destination IS 'Path to moving extracted files';
COMMENT ON COLUMN config.delay IS 'Delay time in sec';
COMMENT ON COLUMN config.wrong IS 'Директорий для файлов, не прошедших конвертацию';



