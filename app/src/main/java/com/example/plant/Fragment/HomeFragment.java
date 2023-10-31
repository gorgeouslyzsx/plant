package com.example.plant.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.plant.R;
/*import com.example.plant.bean.bookBean;
import com.example.plant.utils.Constant;
import com.example.plant.utils.JsonParse;*/


public class HomeFragment extends Fragment{
    private RelativeLayout titlebar;//标题和返回键对应的视图上方的框
    private TextView tv_back, tv_title; //返回键和标题控件
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        tv_back = (TextView) view.findViewById(R.id.tv_back);
        titlebar = (RelativeLayout) view.findViewById(R.id.title_bar);
        tv_back.setVisibility(View.GONE);

    }



}