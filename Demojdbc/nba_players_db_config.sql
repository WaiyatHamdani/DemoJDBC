DROP TABLE nba_players;

DROP SEQUENCE nba_player_id_seq;

DROP PROCEDURE create_nba_player;

DROP PROCEDURE update_nba_player;

CREATE TABLE nba_players (
  ID NUMBER(4) CONSTRAINT nba_players_pk PRIMARY KEY,
  first_name VARCHAR2(15),
  last_name VARCHAR2(15),
  career_points NUMBER(5)
);

CREATE SEQUENCE nba_player_id_seq
   INCREMENT BY 1
   START WITH 1001
;

INSERT INTO nba_players (ID, first_name, last_name, career_points) VALUES (nba_player_id_seq.nextval, 'Karl', 'Malone', 36928);
INSERT INTO nba_players (ID, first_name, last_name, career_points) VALUES (nba_player_id_seq.nextval, 'Lebron', 'James', 33878);
INSERT INTO nba_players (ID, first_name, last_name ,career_points) VALUES (nba_player_id_seq.nextval, 'Kobe', 'Bryant', 33643);
INSERT INTO nba_players (ID, first_name, last_name, career_points) VALUES (nba_player_id_seq.nextval, 'Michael', 'Jordan', 32292);
INSERT INTO nba_players (ID, first_name, last_name, career_points) VALUES (nba_player_id_seq.nextval, 'Curly', 'Neal', 22);

COMMIT;

CREATE OR REPLACE PROCEDURE create_nba_player (
  p_first_name IN nba_players.first_name%TYPE,
  p_last_name IN nba_players.last_name%TYPE,
  p_career_points IN nba_players.career_points%TYPE
)
AS
BEGIN
  INSERT INTO nba_players (
    ID, 
    first_name,
    last_name,
    career_points) 
  VALUES (
    nba_player_id_seq.nextval,
    p_first_name,
    p_last_name,
    p_career_points);
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE update_nba_player (
  p_first_name IN nba_players.first_name%TYPE,
  p_last_name IN nba_players.last_name%TYPE,
  p_career_points IN nba_players.career_points%TYPE,
  p_id IN nba_players.ID%TYPE
)
AS
BEGIN
  UPDATE nba_players
    SET 
      first_name = p_first_name,
      last_name = p_last_name,
      career_points = p_career_points 
   WHERE ID = p_id;
  COMMIT;
END;
/


