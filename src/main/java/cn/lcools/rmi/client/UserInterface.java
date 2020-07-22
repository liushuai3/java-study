package cn.lcools.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by liushuai3 on 2019/5/17.
 */
public interface UserInterface extends Remote {
    User getUser() throws RemoteException;
}
