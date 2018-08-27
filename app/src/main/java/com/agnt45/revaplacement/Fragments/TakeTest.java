package com.agnt45.revaplacement.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.agnt45.revaplacement.Activities.IconScreen;
import com.agnt45.revaplacement.Activities.TestScreen;
import com.agnt45.revaplacement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeTest extends Fragment {




    public TakeTest() {
        // Required empty public constructor
    }
    private GridLayout gridLayout;
    private Button takeTest,dailytest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_take_test, container, false);
        gridLayout = rootView.findViewById(R.id.grid_icons);
        takeTest = rootView.findViewById(R.id.test_aptitude_button);
        gridLayout.setVisibility(View.GONE);
        dailytest = rootView.findViewById(R.id.daily_test_button);
        dailytest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), TestScreen.class);
                intent.putExtra("TestType","DailyTest");
                startActivity(intent);
            }
        });
        takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.setVisibility(View.VISIBLE);

            }
        });
        for(int i=0;i<gridLayout.getChildCount();i++){
            LinearLayout linearLayout = (LinearLayout) gridLayout.getChildAt(i);
            final int finalI = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iconClick = new Intent(getActivity(), IconScreen.class);
                    iconClick.putExtra("iconFilter:",finalI);
                    startActivity(iconClick);
                }
            });
        }


        return rootView;
    }

}
