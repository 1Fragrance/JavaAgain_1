package com.company.infrastructure;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteObject extends Remote {
    void insert(ISerializable model) throws RemoteException, IOException;;
    ArrayList<String> getList() throws RemoteException, IOException;;
}
