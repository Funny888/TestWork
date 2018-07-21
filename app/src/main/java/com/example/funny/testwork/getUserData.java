package com.example.funny.testwork;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;

interface getData {
    @GET("/users")
    Observable<ArrayList<UserData>> getUserData();
}

public class getUserData extends Fragment {
    public static final String TAG = "getUserData";
    getData users;
    MyAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Model> list;


    public static getUserData users() {
        getUserData usersList = new getUserData();
        return usersList;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.userslist, container, false);



        users = new RetrofitToken().getUsers();

        recyclerView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        array();

        return view;
    }

    private void array() {
        ArrayList<UserData> userData1 = new ArrayList<>();


        users.getUserData().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).flatMap(new Function<ArrayList<UserData>, Observable<UserData>>() {
            @Override
            public Observable<UserData> apply(ArrayList<UserData> userData) throws Exception {

                return Observable.fromIterable(userData);
            }
        }).subscribe(new Observer<UserData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserData userData) {
                userData1.add(userData);
            }

            @Override
            public void onError(Throwable e) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: " + userData1);


                makeAdapt(userData1);
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void makeAdapt(ArrayList<UserData> arr) {

        String[] loginsArray = new String[arr.size()], avatarsArray = new String[arr.size()];


        for (int i = 0; i < arr.size(); i++) {
            loginsArray[i] = arr.get(i).getLogin();
            avatarsArray[i] = arr.get(i).getAvatar_url();
        }

        adapter = new MyAdapter(getDataAdapter(loginsArray,avatarsArray));
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Model> getDataAdapter (String [] logins ,String[] avatarts)
    {
        list = new ArrayList<>();
        for (int i=0;i< logins.length; i++) {
            list.add(new Model(logins[i],avatarts[i]));
        }

        return list;
    }
}

