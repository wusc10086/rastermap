
--查询某个省份的空间范围
select p.code,p.name,pa.the_geom from prov_dist_area pa,province p where pa.distid=to_number(p.code,'999999') and p.code='210000';

create table thumbimage(
   id number not null primary key,
   sno varchar2(100) not null,
   thumbimg32 blob,
   thumbimg128 blob
);


create table t_user(
    id number not null,
    username varchar2(50) not null,
    passwd varchar2(50) not null,
    dept varchar2(100),
    roles varchar2(20),
    roledesc varchar2(200),
    age int,
    primary key(id)
);


INSERT INTO T_USER (ID, USERNAME, PASSWD, DEPT, ROLES, ROLEDESC, AGE) 
	VALUES (1, 'admin', 'admin', NULL, NULL, '管理员', 20);


create table rasterextent(
    id number not null,
    sno varchar2(50),
    extent sdo_geometry
);

INSERT INTO user_sdo_geom_metadata VALUES (
    'rasterextent',    ---表名
    'extent',    ---字段名
    MDSYS.SDO_DIM_ARRAY(   
        MDSYS.SDO_DIM_ELEMENT('X',-180, 180, 0.05),   ---X维最小，最大值和容忍度。
        MDSYS.SDO_DIM_ELEMENT('Y', -90, 90, 0.05)    --Y维最小，最大值和容忍度
    ),
    4326    ---坐标系，缺省为笛卡尔坐标系
);

drop index rasterextent_spidx;
CREATE INDEX rasterextent_spidx ON rasterextent(extent) 
    INDEXTYPE IS MDSYS.SPATIAL_INDEX;

create table cgq(
	id number not null,
	name varchar2(50),
	primary key(id)
);

create table satellite(
	id number not null,
	name varchar2(50),
	shortname varchar2(10),
	type varchar2(20),
	primary key(id)
);

INSERT INTO cgq (id, name) 
	VALUES (1, 'CCD');
INSERT INTO cgq (id, name) 
	VALUES (2, 'P10');
INSERT INTO cgq (id, name) 
	VALUES (3, 'MUX');
INSERT INTO cgq (id, name) 
	VALUES (4, 'IRS');
INSERT INTO cgq (id, name) 
	VALUES (5, 'WFI');
INSERT INTO cgq (id, name) 
	VALUES (6, 'PMS');
INSERT INTO cgq (id, name) 
	VALUES (7, 'WFV');
INSERT INTO cgq (id, name) 
	VALUES (8, 'NAD');
INSERT INTO cgq (id, name) 
	VALUES (9, 'TLC');
INSERT INTO cgq (id, name) 
	VALUES (10, 'HRC');
INSERT INTO cgq (id, name) 
	VALUES (11, 'P5M');
INSERT INTO cgq (id, name) 
	VALUES (12, 'CCD1');
INSERT INTO cgq (id, name) 
	VALUES (13, 'CCD2');
INSERT INTO cgq (id, name) 
	VALUES (14, 'HSI');
INSERT INTO cgq (id, name) 
	VALUES (15, 'HR');


INSERT INTO satellite (id, name, shortname, type) 
	VALUES (1, '资源三号卫星', 'ZY-3', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (2, 'WorldView-1全色（0.5米）', 'WV1', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (3, 'WorldView-2全色（0.5米）+多光谱（2米）', 'WV2', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (4, 'WorldView-2全色（0.5米）+多光谱（2米）', 'WV2', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (5, 'GeoEye-1全色（0.5米）+多光谱（2.0）', 'GE1', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (6, 'QUICKBIRD全色（0.6米）+多光谱（2.4米）', 'QB0', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (7, 'pléiade-1A/1B全色（0.7米）+ 多光谱（2.8米）', 'PL0', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (8, 'IKONOS全色（1.0米）+ 多光谱（4.0米）', 'IK0', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (9, '天绘一号（TH-1）全色（2.0米）+ 多光谱（10.0米）', 'TH1', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (10, '法国SPOT5全色（2.5米）+ 多光谱（10米）', 'SP5', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (11, '法国SPOT6全色（1.5米）+ 多光谱（6.0米）', 'SP6', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (12, '印度P5全色（2.5米）', 'P50', NULL);
INSERT INTO satellite (id, name, shortname, type) 
	VALUES (13, '日本ALOS立体测图影像（2.5米）+ 多光谱（10米）', 'AL0', NULL);


INSERT INTO satellite (id, "name", shortname, "type") 
	VALUES (13, 'WorldView-3', 'WV3', NULL);

  CREATE TABLE R_DATASOURCEINFO
   (	"ID" NUMBER, 
	"SN" VARCHAR2(50 BYTE), 
	"SATENAME" VARCHAR2(50 BYTE), 
	"PBANDSENSORTYPE" VARCHAR2(50 BYTE), 
	"SATERESOLUTION" NUMBER, 
	"PBANDORBITCODE" VARCHAR2(50 BYTE), 
	"PBANDDATE" VARCHAR2(50 BYTE), 
	"MULTIBANDSENSORTYPE" VARCHAR2(50 BYTE), 
	"MULTIBANDNUM" NUMBER, 
	"MULTIBANDNAME" VARCHAR2(50 BYTE), 
	"MULTIBANDRESOLUTION" NUMBER, 
	"MULTIBANDORBITCODE" VARCHAR2(50 BYTE), 
	"MULTIBANDDATE" VARCHAR2(50 BYTE), 
	"SATEIMGQUALITY" VARCHAR2(50 BYTE), 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
 

   COMMENT ON COLUMN "SATENAME" IS '卫星名称';
 
   COMMENT ON COLUMN "PBANDSENSORTYPE" IS '全色影像传感器类型';
 
   COMMENT ON COLUMN "SATERESOLUTION" IS '全色卫星影像分辨率';
 
   COMMENT ON COLUMN "PBANDORBITCODE" IS '全色卫星影像轨道号';
 
   COMMENT ON COLUMN "PBANDDATE" IS '全色卫星影像获取时间';
 
   COMMENT ON COLUMN "MULTIBANDSENSORTYPE" IS '多光谱影像传感器类型';
 
   COMMENT ON COLUMN "MULTIBANDNUM" IS '多光谱波段数量';
 
   COMMENT ON COLUMN "MULTIBANDNAME" IS '多光谱波段名称';
 
   COMMENT ON COLUMN "MULTIBANDRESOLUTION" IS '多光谱卫星影像分辨率';
 
   COMMENT ON COLUMN "MULTIBANDORBITCODE" IS '多光谱卫星影像轨道号';
 
   COMMENT ON COLUMN "MULTIBANDDATE" IS '多光谱卫星影像获取时间';
 
   COMMENT ON COLUMN "SATEIMGQUALITY" IS '卫星影像数据质量评价';
 
   COMMENT ON TABLE "R_DATASOURCEINFO"  IS '数据源情况';



  CREATE TABLE "R_DATAPRODUCTIONINFO" 
   (	"ID" NUMBER, 
	"SN" VARCHAR2(50 BYTE), 
	"GRIDINTERVAL" NUMBER, 
	"DEMPRECISION" VARCHAR2(50 BYTE), 
	"CONTROLSOURCE" VARCHAR2(50 BYTE), 
	"SATEORIXRMS" VARCHAR2(50 BYTE), 
	"SATEORIYRMS" VARCHAR2(50 BYTE), 
	"SATEORIZRMS" VARCHAR2(50 BYTE), 
	"ATPRODUCERNAME" VARCHAR2(15 BYTE), 
	"ATCHECKERNAME" VARCHAR2(15 BYTE), 
	"MANUFACTURETYPE" VARCHAR2(50 BYTE), 
	"STEROEDITQUALITY" VARCHAR2(50 BYTE), 
	"ORTHORECTIFYSOFTWARE" VARCHAR2(50 BYTE), 
	"RESAMPLEMETHOD" VARCHAR2(50 BYTE), 
	"ORTHORECTIFYQUALITY" VARCHAR2(20 BYTE), 
	"ORTHORECTIFYNAME" VARCHAR2(20 BYTE), 
	"ORTHOCHECKNAME" VARCHAR2(20 BYTE), 
	"WESTMOSAICMAXERROR" NUMBER, 
	"NORTHMOSAICMAXERROR" NUMBER, 
	"EASTMOSAICMAXERROR" NUMBER, 
	"SOUTHMOSAICMAXERROR" NUMBER, 
	"MOSAICQUALITY" VARCHAR2(20 BYTE), 
	"MOSAICPRODUCERNAME" VARCHAR2(10 BYTE), 
	"MOSAICCHECKERNAME" VARCHAR2(10 BYTE), 
	"MULTIBRECTIFYXRMS" VARCHAR2(10 BYTE), 
	"MULTIBRECTIFYYRMS" VARCHAR2(10 BYTE), 
	"CHECKPOINTNUM" NUMBER, 
	"CHECKRMS" NUMBER, 
	"CHECKMAXERR" NUMBER, 
	"CONCLUSIONINSTITUTE" VARCHAR2(20 BYTE), 
	"INSTITUTECHECKUNIT" VARCHAR2(50 BYTE), 
	"INSTITUTECHECKNAME" VARCHAR2(20 BYTE), 
	"INSTITUTECHECKDATE" VARCHAR2(20 BYTE), 
	"BUREAUCHECKNAME" VARCHAR2(20 BYTE), 
	"BUREAUCHECKUNIT" VARCHAR2(50 BYTE), 
	"CONCLUSIONBUREAU" VARCHAR2(20 BYTE), 
	"BUREAUCHECKDATE" VARCHAR2(20 BYTE), 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
 

   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."GRIDINTERVAL" IS 'DEM格网间距';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."DEMPRECISION" IS 'DEM精度情况';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."CONTROLSOURCE" IS '控制资料来源';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."SATEORIXRMS" IS '外参数解算平面中误差(X)';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."SATEORIYRMS" IS '外参数解算平面中误差(Y)';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."SATEORIZRMS" IS '外参数解算高程中误差';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."ATPRODUCERNAME" IS '参数解算作业员';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."ATCHECKERNAME" IS '参数解算检查员';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."MANUFACTURETYPE" IS '数据生产方式';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."STEROEDITQUALITY" IS '立体模型编辑情况';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."ORTHORECTIFYSOFTWARE" IS '正射纠正软件';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."RESAMPLEMETHOD" IS '重采样方法';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."ORTHORECTIFYQUALITY" IS '正射纠正总结';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."ORTHORECTIFYNAME" IS '正射纠正作业员';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."ORTHOCHECKNAME" IS '正射纠正检查员';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."WESTMOSAICMAXERROR" IS '西边最大接边差';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."NORTHMOSAICMAXERROR" IS '北边最大接边差';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."EASTMOSAICMAXERROR" IS '东边最大接边差';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."SOUTHMOSAICMAXERROR" IS '南边最大接边差';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."MOSAICQUALITY" IS '接边质量评价';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."MOSAICPRODUCERNAME" IS '接边作业员';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."MOSAICCHECKERNAME" IS '接边检查员';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."MULTIBRECTIFYXRMS" IS '多光谱配准纠正中误差(X)';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."MULTIBRECTIFYYRMS" IS '多光谱配准纠正中误差(Y)';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."CHECKPOINTNUM" IS '检查点个数';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."CHECKRMS" IS '检查点平面中误差';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."CHECKMAXERR" IS '检查点最大误差';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."CONCLUSIONINSTITUTE" IS '院级检查结论';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."INSTITUTECHECKUNIT" IS '院级检查单位';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."INSTITUTECHECKNAME" IS '院级检查人';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."INSTITUTECHECKDATE" IS '院级检查时间';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."BUREAUCHECKNAME" IS '局级验收人';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."BUREAUCHECKUNIT" IS '局级验收单位';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."CONCLUSIONBUREAU" IS '局级验收意见';
 
   COMMENT ON COLUMN "R_DATAPRODUCTIONINFO"."BUREAUCHECKDATE" IS '局级验收时间';
 
   COMMENT ON TABLE "R_DATAPRODUCTIONINFO"  IS '数据生产过程信息';
 

  CREATE TABLE "R_PRODUCTINFO" 
   (	"ID" NUMBER, 
	"SN" VARCHAR2(50 BYTE), 
	"METADATAFILENAME" VARCHAR2(50 BYTE), 
	"PRODUCTNAME" VARCHAR2(50 BYTE), 
	"OWNER" VARCHAR2(32 BYTE), 
	"PRODUCER" VARCHAR2(32 BYTE), 
	"PUBLISHER" VARCHAR2(32 BYTE), 
	"PRODUCEDATE" VARCHAR2(8 BYTE), 
	"CONFIDENTIALLEVEL" VARCHAR2(12 BYTE), 
	"GROUNDRESOLUTION" NUMBER, 
	"IMGCOLORMODEL" VARCHAR2(8 BYTE), 
	"PIXELBITS" NUMBER, 
	"IMGSIZE" NUMBER, 
	"DATAFORMAT" VARCHAR2(50 BYTE), 
	"SOUTHWESTABS" NUMBER, 
	"SOUTHWESTORD" NUMBER, 
	"NORTHWESTABS" NUMBER, 
	"NORTHWESTORD" NUMBER, 
	"NORTHEASTABS" NUMBER, 
	"NORTHEASTORD" NUMBER, 
	"SOUTHEASTABS" NUMBER, 
	"SOUTHEASTORD" NUMBER, 
	"LONGERRADIUS" NUMBER, 
	"OBLATUSRATIO" VARCHAR2(30 BYTE), 
	"GEODETICDATUM" VARCHAR2(30 BYTE), 
	"MAPPROJECTION" VARCHAR2(30 BYTE), 
	"CENTRALMEDERIAN" NUMBER, 
	"ZONEDIVISIONMODE" VARCHAR2(10 BYTE), 
	"GAUSSKRUGERZONENO" NUMBER, 
	"COORDINATIONUNIT" VARCHAR2(10 BYTE), 
	"HEIGHTSYSTEM" VARCHAR2(100 BYTE), 
	"HEIGHTDATUM" VARCHAR2(100 BYTE), 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
 

   COMMENT ON COLUMN "R_PRODUCTINFO"."METADATAFILENAME" IS '元数据文件名称';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."PRODUCTNAME" IS '产品名称';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."OWNER" IS '产品版权单位名';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."PRODUCER" IS '产品生产单位名';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."PUBLISHER" IS '产品出版单位名';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."PRODUCEDATE" IS '产品生产时间';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."CONFIDENTIALLEVEL" IS '密级';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."GROUNDRESOLUTION" IS '地面分辨率';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."IMGCOLORMODEL" IS '影像色彩模式';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."PIXELBITS" IS '像素位数';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."IMGSIZE" IS '整景数据量大小';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."DATAFORMAT" IS '数据格式';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."SOUTHWESTABS" IS '影像左下角X坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."SOUTHWESTORD" IS '影像左下角Y坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."NORTHWESTABS" IS '影像左上角X坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."NORTHWESTORD" IS '影像左上角Y坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."NORTHEASTABS" IS '影像右上角X坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."NORTHEASTORD" IS '影像右上角Y坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."SOUTHEASTABS" IS '影像右下角X坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."SOUTHEASTORD" IS '影像右下角Y坐标';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."LONGERRADIUS" IS '椭球长半径';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."OBLATUSRATIO" IS '椭球扁率';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."GEODETICDATUM" IS '所采用大地基准';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."MAPPROJECTION" IS '地图投影';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."CENTRALMEDERIAN" IS '中央子午线';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."ZONEDIVISIONMODE" IS '分带方式';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."GAUSSKRUGERZONENO" IS '高斯-克吕格投影带号';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."COORDINATIONUNIT" IS '坐标单位';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."HEIGHTSYSTEM" IS '高程系统名';
 
   COMMENT ON COLUMN "R_PRODUCTINFO"."HEIGHTDATUM" IS '高程基准';
 
   COMMENT ON TABLE "R_PRODUCTINFO"  IS '产品基本情况';
 

 ------------------------------------------
INSERT INTO BEIDOU.R_PRODUCTINFO (ID, SN, METADATAFILENAME, PRODUCTNAME, OWNER, PRODUCER, PUBLISHER, PRODUCEDATE, CONFIDENTIALLEVEL, GROUNDRESOLUTION, IMGCOLORMODEL, PIXELBITS, IMGSIZE, DATAFORMAT, SOUTHWESTABS, SOUTHWESTORD, NORTHWESTABS, NORTHWESTORD, NORTHEASTABS, NORTHEASTORD, SOUTHEASTABS, SOUTHEASTORD, LONGERRADIUS, OBLATUSRATIO, GEODETICDATUM, MAPPROJECTION, CENTRALMEDERIAN, ZONEDIVISIONMODE, GAUSSKRUGERZONENO, COORDINATIONUNIT, HEIGHTSYSTEM, HEIGHTDATUM) 
	VALUES (1, 'WV3053762685040_01_P00120141007Y', 'WV3053762685040_01_P00120141004Y', '地理国情普查数字正射影像', '国家测绘地理信息局', '浙江省测绘与地理信息局', '国家测绘地理信息局', '201411', '秘密', 0.5, '多光谱', 64, 3851.3, '非压缩 TIFF', 764319.13, 3401171.54, 780776.24, 3401171.54, 764319.13, 3380400.09, 780776.24, 3380400.09, 6378137, '1/298.257222101', '2000国家大地坐标系', '高斯-克吕格投影', 117, '6度带', 20, '米', '正常高', '1985国家高程基准');
INSERT INTO BEIDOU.R_PRODUCTINFO (ID, SN, METADATAFILENAME, PRODUCTNAME, OWNER, PRODUCER, PUBLISHER, PRODUCEDATE, CONFIDENTIALLEVEL, GROUNDRESOLUTION, IMGCOLORMODEL, PIXELBITS, IMGSIZE, DATAFORMAT, SOUTHWESTABS, SOUTHWESTORD, NORTHWESTABS, NORTHWESTORD, NORTHEASTABS, NORTHEASTORD, SOUTHEASTABS, SOUTHEASTORD, LONGERRADIUS, OBLATUSRATIO, GEODETICDATUM, MAPPROJECTION, CENTRALMEDERIAN, ZONEDIVISIONMODE, GAUSSKRUGERZONENO, COORDINATIONUNIT, HEIGHTSYSTEM, HEIGHTDATUM) 
	VALUES (2, 'WV3053762685040_01_P00120141008Y', 'WV3053762685040_01_P00120141004Y', '地理国情普查数字正射影像', '国家测绘地理信息局', '浙江省测绘与地理信息局', '国家测绘地理信息局', '201411', '秘密', 0.5, '多光谱', 64, 3851.3, '非压缩 TIFF', 764319.13, 3401171.54, 780776.24, 3401171.54, 764319.13, 3380400.09, 780776.24, 3380400.09, 6378137, '1/298.257222101', '2000国家大地坐标系', '高斯-克吕格投影', 117, '6度带', 20, '米', '正常高', '1985国家高程基准');
INSERT INTO BEIDOU.R_PRODUCTINFO (ID, SN, METADATAFILENAME, PRODUCTNAME, OWNER, PRODUCER, PUBLISHER, PRODUCEDATE, CONFIDENTIALLEVEL, GROUNDRESOLUTION, IMGCOLORMODEL, PIXELBITS, IMGSIZE, DATAFORMAT, SOUTHWESTABS, SOUTHWESTORD, NORTHWESTABS, NORTHWESTORD, NORTHEASTABS, NORTHEASTORD, SOUTHEASTABS, SOUTHEASTORD, LONGERRADIUS, OBLATUSRATIO, GEODETICDATUM, MAPPROJECTION, CENTRALMEDERIAN, ZONEDIVISIONMODE, GAUSSKRUGERZONENO, COORDINATIONUNIT, HEIGHTSYSTEM, HEIGHTDATUM) 
	VALUES (3, '15MAY30022117-P2AS_R3C1-054355342160_01_P001', 'ZY385810320150427M.XML', '地理国情普查标准时点核准数字正射影像', '国家测绘地理信息局', '黑龙江测绘地理信息局', '国家测绘地理信息局', '201505', '秘密', 6, '多光谱', 64, 229.7, '非压缩 TIFF', 5349063, 487621, 116.08695, 39.3109, 5375217.62, 519523.43, 116.94154, 40.69405, 6378137, '1/298.257222101', '2000国家大地坐标系', '高斯-克吕格投影', 135, '6度带', 23, '米', '正常高', '1985国家高程基准');
INSERT INTO BEIDOU.R_PRODUCTINFO (ID, SN, METADATAFILENAME, PRODUCTNAME, OWNER, PRODUCER, PUBLISHER, PRODUCEDATE, CONFIDENTIALLEVEL, GROUNDRESOLUTION, IMGCOLORMODEL, PIXELBITS, IMGSIZE, DATAFORMAT, SOUTHWESTABS, SOUTHWESTORD, NORTHWESTABS, NORTHWESTORD, NORTHEASTABS, NORTHEASTORD, SOUTHEASTABS, SOUTHEASTORD, LONGERRADIUS, OBLATUSRATIO, GEODETICDATUM, MAPPROJECTION, CENTRALMEDERIAN, ZONEDIVISIONMODE, GAUSSKRUGERZONENO, COORDINATIONUNIT, HEIGHTSYSTEM, HEIGHTDATUM) 
	VALUES (4, '15MAY30022117-P2AS_R1C2-054355342160_01_P001', 'ZY385810320150427P.XML', '地理国情普查标准时点核准数字正射影像', '国家测绘地理信息局', '黑龙江测绘地理信息局', '国家测绘地理信息局', '201505', '秘密', 2, '全色', 16, 518.8, '非压缩 TIFF', 5348937, 487595, 116.90539, 39.09141, 5375221, 519525.06, 117.5435, 40.90142, 6378137, '1/298.257222101', '2000国家大地坐标系', '高斯-克吕格投影', 135, '6度带', 23, '米', '正常高', '1985国家高程基准');
INSERT INTO BEIDOU.R_PRODUCTINFO (ID, SN, METADATAFILENAME, PRODUCTNAME, OWNER, PRODUCER, PUBLISHER, PRODUCEDATE, CONFIDENTIALLEVEL, GROUNDRESOLUTION, IMGCOLORMODEL, PIXELBITS, IMGSIZE, DATAFORMAT, SOUTHWESTABS, SOUTHWESTORD, NORTHWESTABS, NORTHWESTORD, NORTHEASTABS, NORTHEASTORD, SOUTHEASTABS, SOUTHEASTORD, LONGERRADIUS, OBLATUSRATIO, GEODETICDATUM, MAPPROJECTION, CENTRALMEDERIAN, ZONEDIVISIONMODE, GAUSSKRUGERZONENO, COORDINATIONUNIT, HEIGHTSYSTEM, HEIGHTDATUM) 
	VALUES (5, 'WV3053762685040_01_P00120141005Y', 'WV3053762685040_01_P00120141004Y', '地理国情普查数字正射影像', '国家测绘地理信息局', '浙江省测绘与地理信息局', '国家测绘地理信息局', '201411', '秘密', 0.5, '多光谱', 64, 3851.3, '非压缩 TIFF', 764319.13, 3401171.54, 780776.24, 3401171.54, 764319.13, 3380400.09, 780776.24, 3380400.09, 6378137, '1/298.257222101', '2000国家大地坐标系', '高斯-克吕格投影', 117, '6度带', 20, '米', '正常高', '1985国家高程基准');
INSERT INTO BEIDOU.R_PRODUCTINFO (ID, SN, METADATAFILENAME, PRODUCTNAME, OWNER, PRODUCER, PUBLISHER, PRODUCEDATE, CONFIDENTIALLEVEL, GROUNDRESOLUTION, IMGCOLORMODEL, PIXELBITS, IMGSIZE, DATAFORMAT, SOUTHWESTABS, SOUTHWESTORD, NORTHWESTABS, NORTHWESTORD, NORTHEASTABS, NORTHEASTORD, SOUTHEASTABS, SOUTHEASTORD, LONGERRADIUS, OBLATUSRATIO, GEODETICDATUM, MAPPROJECTION, CENTRALMEDERIAN, ZONEDIVISIONMODE, GAUSSKRUGERZONENO, COORDINATIONUNIT, HEIGHTSYSTEM, HEIGHTDATUM) 
	VALUES (6, 'WV3053762685040_01_P00120141006Y', 'WV3053762685040_01_P00120141004Y', '地理国情普查数字正射影像', '国家测绘地理信息局', '浙江省测绘与地理信息局', '国家测绘地理信息局', '201411', '秘密', 0.5, '多光谱', 64, 3851.3, '非压缩 TIFF', 764319.13, 3401171.54, 780776.24, 3401171.54, 764319.13, 3380400.09, 780776.24, 3380400.09, 6378137, '1/298.257222101', '2000国家大地坐标系', '高斯-克吕格投影', 117, '6度带', 20, '米', '正常高', '1985国家高程基准');
INSERT INTO BEIDOU.R_PRODUCTINFO (ID, SN, METADATAFILENAME, PRODUCTNAME, OWNER, PRODUCER, PUBLISHER, PRODUCEDATE, CONFIDENTIALLEVEL, GROUNDRESOLUTION, IMGCOLORMODEL, PIXELBITS, IMGSIZE, DATAFORMAT, SOUTHWESTABS, SOUTHWESTORD, NORTHWESTABS, NORTHWESTORD, NORTHEASTABS, NORTHEASTORD, SOUTHEASTABS, SOUTHEASTORD, LONGERRADIUS, OBLATUSRATIO, GEODETICDATUM, MAPPROJECTION, CENTRALMEDERIAN, ZONEDIVISIONMODE, GAUSSKRUGERZONENO, COORDINATIONUNIT, HEIGHTSYSTEM, HEIGHTDATUM) 
	VALUES (7, 'WV3053762685040_01_P00120141004Y', 'WV3053762685040_01_P00120141004Y', '地理国情普查数字正射影像', '国家测绘地理信息局', '浙江省测绘与地理信息局', '国家测绘地理信息局', '201411', '秘密', 0.5, '多光谱', 64, 3851.3, '非压缩 TIFF', 764319.13, 3401171.54, 780776.24, 3401171.54, 764319.13, 3380400.09, 780776.24, 3380400.09, 6378137, '1/298.257222101', '2000国家大地坐标系', '高斯-克吕格投影', 117, '6度带', 20, '米', '正常高', '1985国家高程基准');

----------------------------------
INSERT INTO BEIDOU.R_DATASOURCEINFO (ID, SN, SATENAME, PBANDSENSORTYPE, SATERESOLUTION, PBANDORBITCODE, PBANDDATE, MULTIBANDSENSORTYPE, MULTIBANDNUM, MULTIBANDNAME, MULTIBANDRESOLUTION, MULTIBANDORBITCODE, MULTIBANDDATE, SATEIMGQUALITY) 
	VALUES (1, 'WV3053762685040_01_P00120141007Y', 'WorldView-3', 'CCD', 0.5, '053762685040_01_P001', '20141004', 'CCD', 4, 'B/G/R/NIR', 2, '053762685040_01_P001', '20141004', '合格');
INSERT INTO BEIDOU.R_DATASOURCEINFO (ID, SN, SATENAME, PBANDSENSORTYPE, SATERESOLUTION, PBANDORBITCODE, PBANDDATE, MULTIBANDSENSORTYPE, MULTIBANDNUM, MULTIBANDNAME, MULTIBANDRESOLUTION, MULTIBANDORBITCODE, MULTIBANDDATE, SATEIMGQUALITY) 
	VALUES (2, 'WV3053762685040_01_P00120141008Y', 'WorldView-3', 'CCD', 0.5, '053762685040_01_P001', '20141004', 'CCD', 4, 'B/G/R/NIR', 2, '053762685040_01_P001', '20141004', '合格');
INSERT INTO BEIDOU.R_DATASOURCEINFO (ID, SN, SATENAME, PBANDSENSORTYPE, SATERESOLUTION, PBANDORBITCODE, PBANDDATE, MULTIBANDSENSORTYPE, MULTIBANDNUM, MULTIBANDNAME, MULTIBANDRESOLUTION, MULTIBANDORBITCODE, MULTIBANDDATE, SATEIMGQUALITY) 
	VALUES (3, 'WV3053762685040_01_P00120141005Y', 'WorldView-3', 'CCD', 0.3, '053762685040_01_P001', '20141004', 'CCD', 4, 'B/G/R/NIR', 2, '053762685040_01_P001', '20141004', '合格');
INSERT INTO BEIDOU.R_DATASOURCEINFO (ID, SN, SATENAME, PBANDSENSORTYPE, SATERESOLUTION, PBANDORBITCODE, PBANDDATE, MULTIBANDSENSORTYPE, MULTIBANDNUM, MULTIBANDNAME, MULTIBANDRESOLUTION, MULTIBANDORBITCODE, MULTIBANDDATE, SATEIMGQUALITY) 
	VALUES (4, 'WV3053762685040_01_P00120141006Y', 'WorldView-3', 'CCD', 0.4, '053762685040_01_P001', '20141004', 'CCD', 4, 'B/G/R/NIR', 2, '053762685040_01_P001', '20141004', '合格');
INSERT INTO BEIDOU.R_DATASOURCEINFO (ID, SN, SATENAME, PBANDSENSORTYPE, SATERESOLUTION, PBANDORBITCODE, PBANDDATE, MULTIBANDSENSORTYPE, MULTIBANDNUM, MULTIBANDNAME, MULTIBANDRESOLUTION, MULTIBANDORBITCODE, MULTIBANDDATE, SATEIMGQUALITY) 
	VALUES (5, '15MAY30022117-P2AS_R3C1-054355342160_01_P001', 'ZY-3', NULL, 0, '858103', '20150427', 'CCD', 4, 'R/G/B/NIR', 6, '858103', '20150427', '合格');
INSERT INTO BEIDOU.R_DATASOURCEINFO (ID, SN, SATENAME, PBANDSENSORTYPE, SATERESOLUTION, PBANDORBITCODE, PBANDDATE, MULTIBANDSENSORTYPE, MULTIBANDNUM, MULTIBANDNAME, MULTIBANDRESOLUTION, MULTIBANDORBITCODE, MULTIBANDDATE, SATEIMGQUALITY) 
	VALUES (6, '15MAY30022117-P2AS_R1C2-054355342160_01_P001', 'ZY-3', 'CCD', 2.1, '858103', '20150427', NULL, 0, NULL, 0, NULL, NULL, '合格');
INSERT INTO BEIDOU.R_DATASOURCEINFO (ID, SN, SATENAME, PBANDSENSORTYPE, SATERESOLUTION, PBANDORBITCODE, PBANDDATE, MULTIBANDSENSORTYPE, MULTIBANDNUM, MULTIBANDNAME, MULTIBANDRESOLUTION, MULTIBANDORBITCODE, MULTIBANDDATE, SATEIMGQUALITY) 
	VALUES (7, 'WV3053762685040_01_P00120141004Y', 'WorldView-3', 'CCD', 0.5, '053762685040_01_P001', '20141004', 'CCD', 4, 'B/G/R/NIR', 2, '053762685040_01_P001', '20141004', '合格');


INSERT INTO BEIDOU.R_DATAPRODUCTIONINFO (ID, SN, GRIDINTERVAL, DEMPRECISION, CONTROLSOURCE, SATEORIXRMS, SATEORIYRMS, SATEORIZRMS, ATPRODUCERNAME, ATCHECKERNAME, MANUFACTURETYPE, STEROEDITQUALITY, ORTHORECTIFYSOFTWARE, RESAMPLEMETHOD, ORTHORECTIFYQUALITY, ORTHORECTIFYNAME, ORTHOCHECKNAME, WESTMOSAICMAXERROR, NORTHMOSAICMAXERROR, EASTMOSAICMAXERROR, SOUTHMOSAICMAXERROR, MOSAICQUALITY, MOSAICPRODUCERNAME, MOSAICCHECKERNAME, MULTIBRECTIFYXRMS, MULTIBRECTIFYYRMS, CHECKPOINTNUM, CHECKRMS, CHECKMAXERR, CONCLUSIONINSTITUTE, INSTITUTECHECKUNIT, INSTITUTECHECKNAME, INSTITUTECHECKDATE, BUREAUCHECKNAME, BUREAUCHECKUNIT, CONCLUSIONBUREAU, BUREAUCHECKDATE) 
	VALUES (1, 'WV3053762685040_01_P00120141007Y', 5, '1:10000DEM', '0.5米DOM', '1.45', '0.87', NULL, '张晨航', '卢佳', '单片纠正', NULL, 'ERDAS2011', '卷积立方', '合格', '张晨航', '卢佳', 7.5, 7.5, 7.5, 7.5, '接边符合精度要求', '陈鹏', '夏家田', '0.2', '0.2', 51, 0.93, 0.59, '合格', '浙江省地理信息中心', '赵军', '201411', '冯里涛', '浙江省测绘与地理信息局', '合格', '201412');
INSERT INTO BEIDOU.R_DATAPRODUCTIONINFO (ID, SN, GRIDINTERVAL, DEMPRECISION, CONTROLSOURCE, SATEORIXRMS, SATEORIYRMS, SATEORIZRMS, ATPRODUCERNAME, ATCHECKERNAME, MANUFACTURETYPE, STEROEDITQUALITY, ORTHORECTIFYSOFTWARE, RESAMPLEMETHOD, ORTHORECTIFYQUALITY, ORTHORECTIFYNAME, ORTHOCHECKNAME, WESTMOSAICMAXERROR, NORTHMOSAICMAXERROR, EASTMOSAICMAXERROR, SOUTHMOSAICMAXERROR, MOSAICQUALITY, MOSAICPRODUCERNAME, MOSAICCHECKERNAME, MULTIBRECTIFYXRMS, MULTIBRECTIFYYRMS, CHECKPOINTNUM, CHECKRMS, CHECKMAXERR, CONCLUSIONINSTITUTE, INSTITUTECHECKUNIT, INSTITUTECHECKNAME, INSTITUTECHECKDATE, BUREAUCHECKNAME, BUREAUCHECKUNIT, CONCLUSIONBUREAU, BUREAUCHECKDATE) 
	VALUES (2, 'WV3053762685040_01_P00120141008Y', 5, '1:10000DEM', '0.5米DOM', '1.45', '0.87', NULL, '张晨航', '卢佳', '单片纠正', NULL, 'ERDAS2011', '卷积立方', '合格', '张晨航', '卢佳', 7.5, 7.5, 7.5, 7.5, '接边符合精度要求', '陈鹏', '夏家田', '0.2', '0.2', 51, 0.93, 0.59, '合格', '浙江省地理信息中心', '赵军', '201411', '冯里涛', '浙江省测绘与地理信息局', '合格', '201412');
INSERT INTO BEIDOU.R_DATAPRODUCTIONINFO (ID, SN, GRIDINTERVAL, DEMPRECISION, CONTROLSOURCE, SATEORIXRMS, SATEORIYRMS, SATEORIZRMS, ATPRODUCERNAME, ATCHECKERNAME, MANUFACTURETYPE, STEROEDITQUALITY, ORTHORECTIFYSOFTWARE, RESAMPLEMETHOD, ORTHORECTIFYQUALITY, ORTHORECTIFYNAME, ORTHOCHECKNAME, WESTMOSAICMAXERROR, NORTHMOSAICMAXERROR, EASTMOSAICMAXERROR, SOUTHMOSAICMAXERROR, MOSAICQUALITY, MOSAICPRODUCERNAME, MOSAICCHECKERNAME, MULTIBRECTIFYXRMS, MULTIBRECTIFYYRMS, CHECKPOINTNUM, CHECKRMS, CHECKMAXERR, CONCLUSIONINSTITUTE, INSTITUTECHECKUNIT, INSTITUTECHECKNAME, INSTITUTECHECKDATE, BUREAUCHECKNAME, BUREAUCHECKUNIT, CONCLUSIONBUREAU, BUREAUCHECKDATE) 
	VALUES (3, '15MAY30022117-P2AS_R3C1-054355342160_01_P001', 25, '1:50000DEM', '地理国情普查数字正射影像', '0.67', '0.54', NULL, '贾杰', '万昀昕', '单片纠正', NULL, 'PCI GXL', '卷积立方', '合格', '贾杰', '万昀昕', -99, -99, -99, -99, '接边符合精度要求', '贾杰', '万昀昕', '0.23', '0.31', 10, 2.31, 4.11, '合格', '黑龙江地理信息工程院', '林智鸣', '201506', '冯海波', '国家测绘地理信息局黑龙江测绘产品质量监督检验站', '合格', '201507');
INSERT INTO BEIDOU.R_DATAPRODUCTIONINFO (ID, SN, GRIDINTERVAL, DEMPRECISION, CONTROLSOURCE, SATEORIXRMS, SATEORIYRMS, SATEORIZRMS, ATPRODUCERNAME, ATCHECKERNAME, MANUFACTURETYPE, STEROEDITQUALITY, ORTHORECTIFYSOFTWARE, RESAMPLEMETHOD, ORTHORECTIFYQUALITY, ORTHORECTIFYNAME, ORTHOCHECKNAME, WESTMOSAICMAXERROR, NORTHMOSAICMAXERROR, EASTMOSAICMAXERROR, SOUTHMOSAICMAXERROR, MOSAICQUALITY, MOSAICPRODUCERNAME, MOSAICCHECKERNAME, MULTIBRECTIFYXRMS, MULTIBRECTIFYYRMS, CHECKPOINTNUM, CHECKRMS, CHECKMAXERR, CONCLUSIONINSTITUTE, INSTITUTECHECKUNIT, INSTITUTECHECKNAME, INSTITUTECHECKDATE, BUREAUCHECKNAME, BUREAUCHECKUNIT, CONCLUSIONBUREAU, BUREAUCHECKDATE) 
	VALUES (4, '15MAY30022117-P2AS_R1C2-054355342160_01_P001', 55, '1:50000DEM', '地理国情普查数字正', '0.67', '0.54', NULL, '贾杰', '万昀昕', '单片纠正', NULL, 'PCI GXL', '卷积立方', '合格', '贾杰', '万昀昕', -99, -99, -99, -99, '接边符合精度要求', '贾杰', '万昀昕', NULL, NULL, 10, 2.31, 4.11, '合格', '黑龙江地理信息工程院', '林智鸣', '201506', '冯海波', '国家测绘地理信息局黑龙江测绘产品质量监督检验站', '合格', '201507');
INSERT INTO BEIDOU.R_DATAPRODUCTIONINFO (ID, SN, GRIDINTERVAL, DEMPRECISION, CONTROLSOURCE, SATEORIXRMS, SATEORIYRMS, SATEORIZRMS, ATPRODUCERNAME, ATCHECKERNAME, MANUFACTURETYPE, STEROEDITQUALITY, ORTHORECTIFYSOFTWARE, RESAMPLEMETHOD, ORTHORECTIFYQUALITY, ORTHORECTIFYNAME, ORTHOCHECKNAME, WESTMOSAICMAXERROR, NORTHMOSAICMAXERROR, EASTMOSAICMAXERROR, SOUTHMOSAICMAXERROR, MOSAICQUALITY, MOSAICPRODUCERNAME, MOSAICCHECKERNAME, MULTIBRECTIFYXRMS, MULTIBRECTIFYYRMS, CHECKPOINTNUM, CHECKRMS, CHECKMAXERR, CONCLUSIONINSTITUTE, INSTITUTECHECKUNIT, INSTITUTECHECKNAME, INSTITUTECHECKDATE, BUREAUCHECKNAME, BUREAUCHECKUNIT, CONCLUSIONBUREAU, BUREAUCHECKDATE) 
	VALUES (5, 'WV3053762685040_01_P00120141005Y', 5, '1:10000DEM', '0.5米DOM', '1.45', '0.87', NULL, '张晨航', '卢佳', '单片纠正', NULL, 'ERDAS2011', '卷积立方', '合格', '张晨航', '卢佳', 7.5, 7.5, 7.5, 7.5, '接边符合精度要求', '陈鹏', '夏家田', '0.2', '0.2', 51, 0.93, 0.59, '合格', '浙江省地理信息中心', '赵军', '201411', '冯里涛', '浙江省测绘与地理信息局', '合格', '201412');
INSERT INTO BEIDOU.R_DATAPRODUCTIONINFO (ID, SN, GRIDINTERVAL, DEMPRECISION, CONTROLSOURCE, SATEORIXRMS, SATEORIYRMS, SATEORIZRMS, ATPRODUCERNAME, ATCHECKERNAME, MANUFACTURETYPE, STEROEDITQUALITY, ORTHORECTIFYSOFTWARE, RESAMPLEMETHOD, ORTHORECTIFYQUALITY, ORTHORECTIFYNAME, ORTHOCHECKNAME, WESTMOSAICMAXERROR, NORTHMOSAICMAXERROR, EASTMOSAICMAXERROR, SOUTHMOSAICMAXERROR, MOSAICQUALITY, MOSAICPRODUCERNAME, MOSAICCHECKERNAME, MULTIBRECTIFYXRMS, MULTIBRECTIFYYRMS, CHECKPOINTNUM, CHECKRMS, CHECKMAXERR, CONCLUSIONINSTITUTE, INSTITUTECHECKUNIT, INSTITUTECHECKNAME, INSTITUTECHECKDATE, BUREAUCHECKNAME, BUREAUCHECKUNIT, CONCLUSIONBUREAU, BUREAUCHECKDATE) 
	VALUES (6, 'WV3053762685040_01_P00120141006Y', 5, '1:10000DEM', '0.5米DOM', '1.45', '0.87', NULL, '张晨航', '卢佳', '单片纠正', NULL, 'ERDAS2011', '卷积立方', '合格', '张晨航', '卢佳', 7.5, 7.5, 7.5, 7.5, '接边符合精度要求', '陈鹏', '夏家田', '0.2', '0.2', 51, 0.93, 0.59, '合格', '浙江省地理信息中心', '赵军', '201411', '冯里涛', '浙江省测绘与地理信息局', '合格', '201412');
INSERT INTO BEIDOU.R_DATAPRODUCTIONINFO (ID, SN, GRIDINTERVAL, DEMPRECISION, CONTROLSOURCE, SATEORIXRMS, SATEORIYRMS, SATEORIZRMS, ATPRODUCERNAME, ATCHECKERNAME, MANUFACTURETYPE, STEROEDITQUALITY, ORTHORECTIFYSOFTWARE, RESAMPLEMETHOD, ORTHORECTIFYQUALITY, ORTHORECTIFYNAME, ORTHOCHECKNAME, WESTMOSAICMAXERROR, NORTHMOSAICMAXERROR, EASTMOSAICMAXERROR, SOUTHMOSAICMAXERROR, MOSAICQUALITY, MOSAICPRODUCERNAME, MOSAICCHECKERNAME, MULTIBRECTIFYXRMS, MULTIBRECTIFYYRMS, CHECKPOINTNUM, CHECKRMS, CHECKMAXERR, CONCLUSIONINSTITUTE, INSTITUTECHECKUNIT, INSTITUTECHECKNAME, INSTITUTECHECKDATE, BUREAUCHECKNAME, BUREAUCHECKUNIT, CONCLUSIONBUREAU, BUREAUCHECKDATE) 
	VALUES (7, 'WV3053762685040_01_P00120141004Y', 5, '1:10000DEM', '0.5米DOM', '1.45', '0.87', NULL, '张晨航', '卢佳', '单片纠正', NULL, 'ERDAS2011', '卷积立方', '合格', '张晨航', '卢佳', 7.5, 7.5, 7.5, 7.5, '接边符合精度要求', '陈鹏', '夏家田', '0.2', '0.2', 51, 0.93, 0.59, '合格', '浙江省地理信息中心', '赵军', '201411', '冯里涛', '浙江省测绘与地理信息局', '合格', '201412');
