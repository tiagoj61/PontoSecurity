package com.example.pontosecurity;

import android.os.Bundle;

import com.example.pontosecurity.adapter.NumericButtonAdapter;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pontosecurity.databinding.ActivityPassBinding;

import java.util.Arrays;
import java.util.List;

public class PassActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        NumericButtonAdapter numericButtonAdapter = new NumericButtonAdapter(this, numeros);
        binding.grid.setAdapter(numericButtonAdapter);
        binding.grid.setOnItemClickListener((adapterView, view, position, idView) -> {
            System.out.println(position);
            System.out.println(numeros.get(position));
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_pass);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}