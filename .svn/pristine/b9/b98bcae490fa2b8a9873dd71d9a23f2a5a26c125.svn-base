package org.rasterdb.service;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.rasterdb.domain.User;

/**
 *
 * @author gis
 */
@IocBean( name = "userService")
public class UserService {

    @Inject("dao2")
    protected Dao dao2;
    public boolean checkUser(User user) {
        User u = dao2.fetch(User.class, Cnd.where("username", "=", user.getUsername()).and("passwd", "=", user.getPasswd()));
        return u != null;
    }
    public User checkUserN(User user) {
        User u = dao2.fetch(User.class, Cnd.where("username", "=", user.getUsername()).and("passwd", "=", user.getPasswd()));
        if(u!=null ){
            return u ;
        }else
        {
            return null;
        }
    }
}
