package com.example.pontosecurity.communicate;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.example.pontosecurity.communicate.ble.SerialService;
import com.example.pontosecurity.communicate.ble.SerialSocket;

import java.io.IOException;

public class ComunicateToArduino {
    public static void sendAlertToBLE(Context context) throws IOException {
        connect(context);
    }
    private static void connect(Context context) throws IOException {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice("");
        System.out.println(device);
        SerialSocket socket = new SerialSocket(context, device);
        SerialService service = new SerialService();
        service.connect(socket);
    }
}
