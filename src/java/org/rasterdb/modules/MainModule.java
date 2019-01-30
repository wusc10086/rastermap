package org.rasterdb.modules;

import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 *
 * @author Administrator
 */
@Modules(scanPackage = true)
@Ok("json")
@Fail("json")
@IocBy(type = ComboIocProvider.class,
        args = {"*org.nutz.ioc.loader.json.JsonLoader",
            "dao.js",
            "*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
            "org.rasterdb.modules",
            "com.beyondb.bigmap.services",
            "org.rasterdb.dataprocess",
            "org.rasterdb.utils",
            "org.rasterdb.service",})
public class MainModule {

}
