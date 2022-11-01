package com.example.pertemuan8api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    int no;
    TextView textView1;
    TextView counter;
    Button button1;
    RecyclerView recyclerview;
    CatAdapter catAdapter;
    private ArrayList<Cat> catArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        counter = findViewById(R.id.Counter);
        button1 = findViewById(R.id.button1);

        //Recyclerview
        recyclerview = findViewById(R.id.recyclerview);
        catAdapter = new CatAdapter(this, catArrayList);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(catAdapter);
        no  = 0;
        counter.setText(no+"");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCatImage(catAdapter);
                no++;
                if (no == 5){
                    counter.setText(no+" Anda Suka sekali Dengan Kucing Ya");
                }
                counter.setText(no+"");
            }

        });
    }

    //get random data ini sudah secara otomatis request data di background
    // kalau mau secara manual jalan di bg, maka harus menggunakan equator

    private void getCatImage(CatAdapter CatAdapter){
        Call<Cat> caller = RetrofitClient.getInstance().getMyApi().getCatImage();
        caller.enqueue(new Callback<Cat>() {
            @Override
            public void onResponse(Call<Cat> call, Response<Cat> response) {
                Cat cat  = response.body();
                String url = cat.getFacts();
                catArrayList.add(new Cat(url));
                CatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Cat> call, Throwable t) {

            }
        });
    }
}