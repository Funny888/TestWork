package com.example.funny.testwork;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* Класс фрагмент показывающий данные о версии билда*/

public class About extends Fragment {


    public static About fragAbout() // метод для создания экземпляра класса
    {
        About about = new About();
        return about;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.about, null);

        TextView aboutBuild = view.findViewById(R.id.version);
        aboutBuild.setText("Android version:" + Build.VERSION.RELEASE + "\n" +
                "Name version: " + Build.DEVICE + "\n" +
                "Brand: " + Build.BOARD + "\n" +
                "Model: " + Build.MODEL);


        return view;
    }
}
