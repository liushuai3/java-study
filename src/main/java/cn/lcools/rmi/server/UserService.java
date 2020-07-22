package cn.lcools.rmi.server;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by liushuai3 on 2019/5/17.
 */
public class UserService implements UserInterface{
    @Override
    public User getUser() throws RemoteException{
        User user = new User();
        user.setName("liushuai");
        user.setAge(25);
        user.setBirthday(new Date());
        return user;
    }
}
