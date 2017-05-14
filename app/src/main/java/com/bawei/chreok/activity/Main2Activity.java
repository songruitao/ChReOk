package com.bawei.chreok.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.chreok.MyRecycleView;
import com.bawei.chreok.R;
import com.bawei.chreok.bean.Bean;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.re);

        Intent intent=getIntent();
        ArrayList<Bean.DataBean> beanList = (ArrayList<Bean.DataBean>) intent.getSerializableExtra("aa");

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        MyRecycleView myRecycleView=new MyRecycleView(beanList,this);
        recyclerView.setAdapter(myRecycleView);

    }
}
