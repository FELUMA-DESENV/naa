
CREATE TABLE vacina (
                cod_vac INT IDENTITY NOT NULL,
                nom_vac VARCHAR(200) NOT NULL,
                des_vac VARCHAR(400),
                car_vac INT,
                obr_vac BIT,
                per_val_vac INT NOT NULL,
                per_val_vac_fra NVARCHAR NOT NULL,
                CONSTRAINT PK__vacina__F208B9A8A27B97D4 PRIMARY KEY (cod_vac)
)

CREATE TABLE usuario (
                cod_usu INT IDENTITY NOT NULL,
                nom_usu VARCHAR(200) NOT NULL,
                ema_usu VARCHAR(100) NOT NULL,
                sen_usu VARCHAR(200) NOT NULL,
                sta_usu VARCHAR(50) NOT NULL,
                CONSTRAINT PK__usuario__F240E5054BA2BFCC PRIMARY KEY (cod_usu)
)

CREATE TABLE questao (
                cod_que INT IDENTITY NOT NULL,
                descricao VARCHAR(500),
                flg_vis_sn_que BIT NOT NULL,
                flg_txt_que BIT,
                CONSTRAINT PK__questao__FD48CA5A9649140D PRIMARY KEY (cod_que)
)

CREATE TABLE perfil (
                cod_per INT IDENTITY NOT NULL,
                nom_per VARCHAR(200) NOT NULL,
                des_per VARCHAR(400) NOT NULL,
                CONSTRAINT PK__perfil__FD883BA0271FBDC9 PRIMARY KEY (cod_per)
)

CREATE TABLE usuario_perfil (
                cod_usu_per INT IDENTITY NOT NULL,
                cod_per INT NOT NULL,
                cod_usu INT NOT NULL,
                CONSTRAINT PK__usuario___E5EEC3A498E8A6B7 PRIMARY KEY (cod_usu_per, cod_per, cod_usu)
)

CREATE TABLE curso (
                cod_cur INT IDENTITY NOT NULL,
                nom_cur VARCHAR(200),
                des_cur VARCHAR(400),
                CONSTRAINT PK__curso__FEA151FCE9E807B8 PRIMARY KEY (cod_cur)
)

CREATE TABLE estudante (
                cod_est INT IDENTITY NOT NULL,
                cod_cur INT NOT NULL,
                cpf_est CHAR(11) NOT NULL,
                nom_est VARCHAR(200) NOT NULL,
                tip_san_est VARCHAR(15),
                mat_est VARCHAR(50) NOT NULL,
                mae_est VARCHAR(200),
                pai_est VARCHAR(200),
                tel_mae_est VARCHAR(20),
                tel_pai_est VARCHAR(20),
                num_pla_sau_est VARCHAR(50),
                pla_sau_est VARCHAR(200),
                tel_pla_sau_est VARCHAR(20),
                hos_enc_est VARCHAR(200),
                dat_cad_est DATETIME,
                CONSTRAINT PK__estudant__FE21640FF6C3C768 PRIMARY KEY (cod_est)
)
CREATE UNIQUE  NONCLUSTERED INDEX UQ__estudant__99E203CFA42D2DF4
 ON estudante
 ( mat_est )


CREATE TABLE EstudantesComVacinasPendentes (
                cod_est INT NOT NULL,
                cod_cur INT NOT NULL,
                nom_est VARCHAR(200) NOT NULL,
                cpf_est CHAR(11) NOT NULL,
                mat_est VARCHAR(50) NOT NULL,
                CONSTRAINT EstudantesComVacinasPendentes_pk PRIMARY KEY (cod_est, cod_cur)
)

CREATE TABLE estudante_informacao (
                cod_est_inf INT IDENTITY NOT NULL,
                cod_est INT NOT NULL,
                cod_que INT NOT NULL,
                res_que_est_inf BIT,
                res_que_txt_est_inf VARCHAR(400),
                CONSTRAINT PK__estudant__3D7B08C4721DB2D7 PRIMARY KEY (cod_est_inf)
)

CREATE TABLE estudante_contato (
                cod_est_con INT IDENTITY NOT NULL,
                cod_est INT NOT NULL,
                nom_est_con VARCHAR(200) NOT NULL,
                tel_est_con CHAR(20),
                cel_est_con CHAR(20) NOT NULL,
                par_est_con VARCHAR(200) NOT NULL,
                CONSTRAINT PK__estudant__23FB9F0D31F84D1B PRIMARY KEY (cod_est_con)
)

CREATE TABLE vacina_estudante (
                cod_vac_est INT IDENTITY NOT NULL,
                cod_vac INT NOT NULL,
                cod_est INT NOT NULL,
                obs_vac_est VARCHAR(400),
                dat_dos_vac_est DATETIME NOT NULL,
                exa_sor_con_vac_est BIT,
                res_exa_sor_con_vac_est VARCHAR(400),
                dat_val_vac_est DATETIME,
                CONSTRAINT PK__vacina_e__FF20E945A4F54E5A PRIMARY KEY (cod_vac_est)
)

CREATE TABLE atendimento (
                cod_ate INT IDENTITY NOT NULL,
                cod_est INT NOT NULL,
                cod_usu INT NOT NULL,
                dat_ate DATETIME NOT NULL,
                que_pri_ate TEXT,
                con_ate TEXT,
                CONSTRAINT PK__atendime__F92AEDDC6E51B924 PRIMARY KEY (cod_ate)
)

/*
Warning: Microsoft SQL Server 2005 does not support this relationship's delete action (RESTRICT).
Warning: Microsoft SQL Server 2005 does not support this relationship's update action (RESTRICT).
*/
ALTER TABLE vacina_estudante ADD CONSTRAINT FK__vacina_es__cod_v__276EDEB3
FOREIGN KEY (cod_vac)
REFERENCES vacina (cod_vac)

/*
Warning: Microsoft SQL Server 2005 does not support this relationship's delete action (RESTRICT).
Warning: Microsoft SQL Server 2005 does not support this relationship's update action (RESTRICT).
*/
ALTER TABLE atendimento ADD CONSTRAINT FK__atendimen__cod_u__25869641
FOREIGN KEY (cod_usu)
REFERENCES usuario (cod_usu)

ALTER TABLE usuario_perfil ADD CONSTRAINT usuario_usuario_perfil_fk
FOREIGN KEY (cod_usu)
REFERENCES usuario (cod_usu)
ON DELETE NO ACTION
ON UPDATE NO ACTION

/*
Warning: Microsoft SQL Server 2005 does not support this relationship's delete action (RESTRICT).
Warning: Microsoft SQL Server 2005 does not support this relationship's update action (RESTRICT).
*/
ALTER TABLE estudante_informacao ADD CONSTRAINT FK__estudante__cod_q__2C3393D0
FOREIGN KEY (cod_que)
REFERENCES questao (cod_que)

/*
Warning: Microsoft SQL Server 2005 does not support this relationship's delete action (RESTRICT).
Warning: Microsoft SQL Server 2005 does not support this relationship's update action (RESTRICT).
*/
ALTER TABLE usuario_perfil ADD CONSTRAINT FK__usuario_p__cod_p__267ABA7A
FOREIGN KEY (cod_per)
REFERENCES perfil (cod_per)

/*
Warning: Microsoft SQL Server 2005 does not support this relationship's delete action (RESTRICT).
Warning: Microsoft SQL Server 2005 does not support this relationship's update action (RESTRICT).
*/
ALTER TABLE estudante ADD CONSTRAINT FK__estudante__cod_c__2D27B809
FOREIGN KEY (cod_cur)
REFERENCES curso (cod_cur)

/*
Warning: Microsoft SQL Server 2005 does not support this relationship's delete action (RESTRICT).
Warning: Microsoft SQL Server 2005 does not support this relationship's update action (RESTRICT).
*/
ALTER TABLE atendimento ADD CONSTRAINT FK__atendimen__cod_e__2A4B4B5E
FOREIGN KEY (cod_est)
REFERENCES estudante (cod_est)

ALTER TABLE vacina_estudante ADD CONSTRAINT estudante_vacina_estudante_fk
FOREIGN KEY (cod_est)
REFERENCES estudante (cod_est)
ON DELETE NO ACTION
ON UPDATE NO ACTION

ALTER TABLE estudante_contato ADD CONSTRAINT estudante_estudante_contato_fk
FOREIGN KEY (cod_est)
REFERENCES estudante (cod_est)
ON DELETE NO ACTION
ON UPDATE NO ACTION

ALTER TABLE estudante_informacao ADD CONSTRAINT estudante_estudante_informacao_fk
FOREIGN KEY (cod_est)
REFERENCES estudante (cod_est)
ON DELETE NO ACTION
ON UPDATE NO ACTION

ALTER TABLE EstudantesComVacinasPendentes ADD CONSTRAINT estudante_EstudantesComVacinasPendentes_fk
FOREIGN KEY (cod_est)
REFERENCES estudante (cod_est)
ON DELETE NO ACTION
ON UPDATE NO ACTION
