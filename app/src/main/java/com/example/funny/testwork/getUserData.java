package com.example.funny.testwork;


import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GetData { // интерфейс для запроса данных json-объектов
    @GET("/users")
    Observable<ArrayList<UserData>> getUserData();
    @GET("/users/{login}")
    Observable<UserData> getInfoData(@Path("login") String name);
}

public class getUserData extends Fragment {
    public static final String TAG = "getUserData";
    GetData users;
    MyAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Model> list;
    DataBase db;
    SQLiteDatabase connectDB;

    public static final Integer _LIMIT = 10; // Сонстанта для первоначального отображения списка

    public static getUserData users() { // метод для создания экземпляра класса
        getUserData usersList = new getUserData();
        return usersList;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.userslist, container, false);



        users = new RetrofitToken().getUsers(); // экземпляр токена ретрофит

        recyclerView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext()); //создаем менеджер-лэйаут и указываем в качесте данных текущий контекст
        recyclerView.setLayoutManager(layoutManager); //устанавливаем менеджер-лэйаут в ресайкл вью
        db = new DataBase(getContext());
        connectDB = db.getWritableDatabase();


        array(_LIMIT);

        return view;
    }

    private void array(Integer integer) { // метод запроса объектов json с сайта
        ArrayList<UserData> ListUserData = new ArrayList<>();


          users.getUserData().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).flatMap(new Function<ArrayList<UserData>, Observable<UserData>>() { // запрос объектов
            @Override
            public Observable<UserData> apply(ArrayList<UserData> userData) throws Exception { //разбиваем полученный список объектов для получения данных

                return Observable.fromIterable(userData);
            }
        }).take(integer).subscribe(new Observer<UserData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserData userData) {
                ListUserData.add(userData); // добавляем объекты в список
            }

            @Override
            public void onError(Throwable e) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onComplete() {
                makeAdapt(ListUserData); //передаем список объеков в функцию для заполнения адаптера
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void makeAdapt(ArrayList<UserData> arr) {

        String[] loginsArray = new String[arr.size()], avatarsArray = new String[arr.size()];


        ContentValues cv = new ContentValues();

        for (int i = 0; i < arr.size(); i++) { // цикл для получения значение полей из объектов

            loginsArray[i] = arr.get(i).getLogin();
            avatarsArray[i] = arr.get(i).getAvatar_url();
            if (roExist(loginsArray[i]) == false) {
                cv.put(DataBase._ColumnLogin, loginsArray[i]);
                cv.put(DataBase._ColumnAvatar, avatarsArray[i]);
                connectDB.insert(DataBase._USER_TABLE, null, cv);
            }
            else
            {
                Log.i(TAG, "Запись существует");
            }
        }


        adapter = new MyAdapter(getDataAdapter(loginsArray,avatarsArray));
        recyclerView.setAdapter(adapter);



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Integer count = recyclerView.getAdapter().getItemCount();

                if (adapter.getPosition().equals(count - 1)) {


                    array(count + 2);
                }
            }
        });

    }

    private ArrayList<Model> getDataAdapter (String [] logins ,String[] avatarts) //метод для создание листа модели
    {
        list = new ArrayList<>();
        for (int i=0;i< logins.length; i++) {
            list.add(new Model(logins[i],avatarts[i]));
        }

        return list;
    }

    private Boolean roExist(String row)
    {
        Cursor cursor = connectDB.query(DataBase._USER_TABLE,null,null,null,null,null,null);

        if (cursor.moveToFirst()) {
            int login = cursor.getColumnIndex(DataBase._ColumnLogin);
            while (cursor.moveToNext()) {

               if (cursor.getString(login).equals(row))
                    return true;
            }
        }
        return false;
    }

}

