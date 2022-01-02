package com.company;

import com.company.infrastructure.IRemoteObject;
import com.company.infrastructure.PaymentsRemoteObject;
import com.company.settings.Constants;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ServerSide {
    public static void main(String[] args) {

        var remoteObject = new PaymentsRemoteObject();

        try{
            var stub = (IRemoteObject) UnicastRemoteObject.exportObject(remoteObject, 0);
            var registry = LocateRegistry.createRegistry(Constants.Port);

            registry.bind(Constants.BindingName, stub);

            System.out.println("Server is ready to receive requests");
        }
        catch(Throwable cause) {
            System.err.println(cause.getMessage());
        }
    }
}
