package com.example.pontosecurity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pontosecurity.R;

import java.util.List;

public class NumericButtonAdapter extends ArrayAdapter<Integer> {

    public NumericButtonAdapter(@NonNull Context context, List<Integer> listas) {
        super(context, 0,listas);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.adapter_numeric_buttons, parent, false);
        Button button = root.findViewById(R.id.numero);
        button.setText(getItem(position).toString());

        return root;
    }
}
