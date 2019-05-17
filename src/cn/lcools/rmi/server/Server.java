package cn.lcools.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by liushuai3 on 2019/5/17.
 */
public class Server {
    public static void main(String[] args) throws RemoteException {
        UserService userService = new UserService();
        UserInterface userInterface = (UserInterface)UnicastRemoteObject.exportObject(userService,0);
        Registry registry = LocateRegistry.createRegistry(2002);
        registry.rebind("userManager", userInterface);
        System.out.println("server is ready");
    }
}
