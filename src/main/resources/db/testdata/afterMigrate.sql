set foreign_key_checks = 0;

delete from tb_user;
delete from tb_user_roles;
delete from tb_role;
delete from tb_player;
delete from tb_guild;
delete from tb_classe;

set foreign_key_checks = 1;

INSERT INTO tb_user (id,username,password,email) VALUES (1,"ever","$2a$12$tczO53KncDf6Y/hefR3Vw.X1Vny7IqsnklDpX2WioEHDFxiVJnWWm","ever@gmail.com");
INSERT INTO tb_user (id,username,password,email) VALUES (2,"ever1","$2a$12$tczO53KncDf6Y/hefR3Vw.X1Vny7IqsnklDpX2WioEHDFxiVJnWWm","ever@gmail.com");

INSERT INTO tb_role (id,role_name) VALUE (1,"ROLE_ADMIN");
INSERT INTO tb_role (id,role_name) VALUE (2,"ROLE_USER");

INSERT INTO tb_user_roles(user_id,role_id) VALUES (1,1);
INSERT INTO tb_user_roles(user_id,role_id) VALUES (2,2);

INSERT INTO tb_classe (id,name) VALUES (1,"Guerreiro");
INSERT INTO tb_classe (id,name) VALUES (2,"Feiticeiro");
INSERT INTO tb_classe (id,name) VALUES (3,"Taoísta");
INSERT INTO tb_classe (id,name) VALUES (4,"Lanceiro");
INSERT INTO tb_classe (id,name) VALUES (5,"Besteiro");
INSERT INTO tb_classe (id,name) VALUES (6,"Soturna");

INSERT INTO tb_guild (id,name,nivel) VALUES (1,"Teste guild 1",50);
INSERT INTO tb_guild (id,name,nivel) VALUES (2,"Teste guild 2",52);

INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (1,0,1,120,"player 1",155121,1,455,1,1,1);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (2,0,1,121,"player 2",165221,1,555,2,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (3,0,1,123,"player 3",175321,1,655,3,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (4,0,1,124,"player 4",185421,1,755,4,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (5,0,1,125,"player 5",195521,1,855,5,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (6,0,1,126,"player 6",200621,1,955,6,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (7,0,1,127,"player 7",205721,1,980,1,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (8,0,1,128,"player 8",210821,1,990,2,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (9,0,1,129,"player 9",215921,1,1000,3,1,2);
INSERT INTO tb_player (id,alt,ativo,nivel,nick,power,principal,qtd_codex,classe_id,guild_id,player_user)
VALUES (10,0,1,130,"player 10",220100,1,1030,4,1,2);



