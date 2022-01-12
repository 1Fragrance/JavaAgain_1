package com.company;

import com.company.infrastructure.IRemoteObject;
import com.company.models.Payment;
import com.company.settings.Constants;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ClientSide {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        var registry= LocateRegistry.getRegistry(Constants.Host);
        var stub = (IRemoteObject) registry.lookup(Constants.BindingName);

        System.out.println("Client was successfully connected to the server\n");
        printMenu();

        var scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" : {
                handleInsertRecord(stub);
                break;
            }
            case "2" : {
                handleGetList(stub);
                break;
            }
            default: {
                System.out.println("Invalid command");
            }
        }
    }

    private static void handleInsertRecord(IRemoteObject remoteObject) {
        var sc = new Scanner(System.in);

        System.out.println("Credit code:");
        var code = sc.nextLine();

        System.out.println("Amount:");
        var value = sc.nextLine();

        try {
            var parsedValue = Double.parseDouble(value);
            remoteObject.insert(new Payment(code, parsedValue));

            System.out.println("Payment info was successfully added");
        } catch(NumberFormatException e) {
            System.err.println("Incorrect data");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.err.println("Error during data insert");
            e.printStackTrace();
        }
    }

    private static void handleGetList(IRemoteObject remoteObject) {
        try {
            var list = remoteObject.getList();

            for (String s : list) {
                System.out.print(s);
            }
        }
        catch (IOException e) {
            System.err.println("Error during data receiving");
            e.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("Welcome");
        System.out.println("1. Add new payment info");
        System.out.println("2. Get all credit payments");
    }
}
