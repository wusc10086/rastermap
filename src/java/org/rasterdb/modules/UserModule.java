package org.rasterdb.modules;

import javax.servlet.http.HttpSession;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;
import org.rasterdb.domain.User;
import org.rasterdb.service.UserService;

/**
 *  用户认证模块
 * @author gis
 */
@IocBean
@At("/user")
@Filters(@By(type=CheckSession.class, args={"user", "/login.jsp"}))
public class UserModule {
    
    @Inject
    private UserService userService; 
    
//    @At("/login")
//    @Filters()
//    public boolean login(@Param("name") String name,@Param("passwd") String passwd, HttpSession session){
//        
//        User  u = new User(1, name, passwd);
//        boolean res = userService.checkUser(u);
//         if (res ==false ) {
//            return false;
//        } else {
//            session.setAttribute("user", u.getUsername());           
//            return true;
//        }
//    }
    
    
    @At("/login")
    @Filters()
    public boolean loginNew(@Param("name") String name,@Param("passwd") String passwd, HttpSession session){
        
        User  u = new User(1, name, passwd);
        User cUser = userService.checkUserN(u);
         if (cUser ==null ) {
            return false;
        } else {
            session.setAttribute("user", cUser.getUsername());
            session.setAttribute("roles", cUser.getRoles());
            return true;
        }
    }
    
    @At("/logout")
    @Ok(">>:/")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
