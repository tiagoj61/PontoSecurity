package com.example.pontosecurity;

import android.app.Activity;
import android.os.Bundle;

import com.example.pontosecurity.adapter.NumericButtonAdapter;
import com.example.pontosecurity.callback.IFutureCallback;
import com.example.pontosecurity.communicate.ComunicateToArduino;
import com.example.pontosecurity.communicate.ComunicateToServer;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pontosecurity.databinding.ActivityPassBinding;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PassActivity extends AppCompatActivity {

    private ActivityPassBinding binding;
    private String numerosDaSenha;
    private int connectedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numerosDaSenha = "";
        connectedDevices = 0;
        binding = ActivityPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        NumericButtonAdapter numericButtonAdapter = new NumericButtonAdapter(this, numeros);
        binding.grid.setAdapter(numericButtonAdapter);
        binding.grid.setOnItemClickListener((adapterView, view, position, idView) -> {
            int clickedNumber = numeros.get(position);
            numerosDaSenha += String.valueOf(clickedNumber);
            System.out.println(numerosDaSenha);
            if (numerosDaSenha.length() == 5) {
                try {
                    send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(numerosDaSenha.length() >5){
                ComunicateToArduino.send("1");
            }
        });
    }


    public void send() throws IOException {
        ComunicateToArduino.sendAlertToBLE(this.getApplicationContext());
        ComunicateToServer threadICPGetACs = new ComunicateToServer();
        threadICPGetACs.onFutureCallback(new IFutureCallback() {
            @Override
            public void onSuccess() {
                System.out.println("sc");
            }

            @Override
            public void onError(Exception exception) {
                System.out.println("erro");


            }

        });

        threadICPGetACs.start();
        String msg = "";
//        if (ComunicateToServer.pushPassToServer(numerosDaSenha)) {
//            msg = "Connect";
//            connectedDevices++;
//            ComunicateToArduino.sendAlertToBLE(this);
//        } else {
//            msg = "Not Connect";
//            connectedDevices = connectedDevices == 0 ? connectedDevices - 1 : 0;
//        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG);
        //numerosDaSenha = "";
        changeTextConnectedDevices();
    }
    public void changeTextConnectedDevices() {
    }
}