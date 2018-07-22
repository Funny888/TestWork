package com.example.funny.testwork;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* класс создан для поиска репозитория */

public class Repositories extends Fragment {

    public static Repositories searchRepos() // метод для создания экземпляра класса
    {
        Repositories repositories = new Repositories();

        return repositories;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.repositories,container,false);
    }
}
