package com.company.infrastructure;

import com.company.settings.Constants;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class PaymentsRemoteObject implements IRemoteObject {
    @Override
    public void insert(ISerializable model) throws RemoteException, IOException {
        var fw = new FileWriter(Constants.FileName, true);
        var bw = new BufferedWriter(fw);
        bw.write(model.getFileRepresentation());
        bw.close();
    }

    @Override
    public ArrayList<String> getList() throws RemoteException, IOException {
        var result = new ArrayList<String>();

        var fr = new FileReader(Constants.FileName);
        var br = new BufferedReader(fr);

        while (br.ready()) {
            result.add(br.readLine());
        }

        br.close();
        fr.close();

        return result;
    }
}