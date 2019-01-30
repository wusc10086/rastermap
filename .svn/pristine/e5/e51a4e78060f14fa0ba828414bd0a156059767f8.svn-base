var ds = {
    // 读取配置文件
    conf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["dao.properties"]
            //paths : ["custom/"]
        }
    },
    
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
//         type: "org.apache.commons.dbcp.BasicDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            // 请修改下面的数据库连接信息
//            url: 'jdbc:postgresql://localhost:5432/raster',
//            driverClassName: 'org.postgresql.Driver',
//            username: 'postgres',
//            password: '123456',
            url: 'jdbc:oracle:thin:@//192.168.1.102:1521/orcl',
            driverClassName: 'oracle.jdbc.OracleDriver',
            username: 'system',
            password: '123456',
            maxActive: 10,
            testWhileIdle: true,
            testOnBorrow: false,
            testOnReturn: false
        }
    },
    dao: {
        type: 'org.nutz.dao.impl.NutDao',
        args: [{
                refer: 'dataSource2'
            }]
    },
    dataSource2: {
        type: "com.alibaba.druid.pool.DruidDataSource",
//         type: "org.apache.commons.dbcp.BasicDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            // 请修改下面的数据库连接信息
            url: {java :"$conf.get('ds2-db-url')"},
            driverClassName: {java :"$conf.get('ds2-db-driver')"},
            username: {java :"$conf.get('ds2-db-username')"},
            //password: '123456',
            password: {java :"$conf.get('ds2-db-password')"},
            maxActive: 10,
            testWhileIdle: true,
            testOnBorrow: false,
            testOnReturn: false
        }
    },
    dao2: {
        type: 'org.nutz.dao.impl.NutDao',
        args: [{
                refer: 'dataSource2'
            }]
    },
//    dataSource3:{
//        type: "com.alibaba.druid.pool.DruidDataSource",
//        events:{
//            depose:'close'
//        },
//        fields:{
//            url: 'jdbc:oracle:thin:@//127.0.0.1:1521/rasterdb',
//            driverClassName: 'oracle.jdbc.OracleDriver',
//            username: 'system',
//            password: '123456',
//            maxActive: 10,
//            testWhileIdle: true,
//            testOnBorrow: false,
//            testOnReturn: false
//        }
//    },
//    dao3:{
//        type:'org.nutz.dao.impl.NutDao',
//        args:[{
//                refer:'dataSource3'
//        }]
//    },
    fileIndexService: {
        type: 'org.rasterdb.service.FileIndexService',
        fields: {
             //distpath: 'D:/JS/JSdata/suoluetu',
             distpath: 'D:/MZT500',
             mapdistpath:'D:/MapSLT',
             mosaicpath:'d:/mosaic',
             fileCommand:{refer: 'fileCommand'}
        }
    },
    fileDownLoadService:{
        type:'org.rasterdb.service.FileDownLoadService',
        fields:{
            filePath:'D:/downloadservice',
            imagePath:'D:/imagedownloadservice'
        }
    },
    dataProcessService: {
        type: 'org.rasterdb.service.DataProcessService',
        fields: {
             //mosaicpath: 'D:/RasterManager/mosaic'
             mosaicpath: 'd:/mosaic',
             dao:{refer:'dao2'}
        }
    },
    fileCommand :{
        type:'org.rasterdb.dataprocess.FileCommand',
         fields: {
             MERGECMD:'MapServerCmd',
             OVERVIEWCMD:'gdal_translate',
             FUSION:'MapServerCmd'
        }
    },
    tileService:{
        type:'com.beyondb.bigmap.services.TileService',
        args: [{
                refer: 'dao2'
            }]
    },
    
    projectInfo: {
       type:'org.rasterdb.utils.ProjectInfo', 
       fields: {
             provinceId: {java :"$conf.get('project-provinceId')"},
             exportTmpDir: {java :"$conf.get('project-exportTmpDir')"},
             cityService: {refer:'cityService'},
             sysParamService: {refer:'sysParamService'}
        }
    }
};