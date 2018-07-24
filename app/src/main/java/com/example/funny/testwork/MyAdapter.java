package com.example.funny.testwork;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GetInfo { // интерфейс для запроса данных json-объектов

    @GET("/users/{login}")
    Observable<UserData> getInfoData(@Path("login") String name);
}

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    public static final String TAG = "MyAdapter";

    private Context context;
    private ArrayList<Model> modelList;
    protected Integer position;
    GetInfo info;


    public Integer getPosition() { // метод для получение позиции вью
        return position;
    }


    public MyAdapter(ArrayList<Model> list) {
        this.modelList = list;
    }


    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item, parent, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {

        Model model = modelList.get(position);

        info = new RetrofitToken().getInfo(); // экземпляр токена ретрофит


        holder.login.setText(model.getLogin());
        Picasso.get().load(model.getUrl_avatar())
                .into(holder.avatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.getInfoData(model.getLogin()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(userData2 -> {

                    AlertDialog dialog = new AlertDialog.Builder(v.getContext())
                            .setTitle("Информация")
                            .setMessage("Имя: " + userData2.getName() + "\n"
                                    +  "Компания: " + userData2.getCompany() + "\n" +
                                    "Почта: " + userData2.getEmail() + "\n")
                            .create();
                    dialog.show();
                   // нет записи "о себе так как я просто не нашел подобную строку"

                });
            }
        });

            this.position = position;
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        protected TextView login;
        protected ImageView avatar;


        public MyHolder(View itemView) {
            super(itemView);
            login = (TextView) itemView.findViewById(R.id._loginI);
            avatar = (ImageView) itemView.findViewById(R.id._avatarI);
        }
    }
}
