package cn.lcools.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by liushuai3 on 2019/5/17.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost",2002);
        UserInterface userManager = (UserInterface)registry.lookup("userManager");
        User user = userManager.getUser();
        System.out.println(user.getName()+";"+user.getAge()+";"+user.getBirthday());
    }
}
