package com.thanphyo.khitthitpricelist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Than Phyo on 7/22/2020.
 */
public class PriceView extends Activity implements AdapterView.OnItemSelectedListener {
    public static TextView tv0;
    public static Spinner spinner;
    private CustomAdapter customadapter;
    int logo[] = {R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a};
    private int posi;
    private Bundle bdn;
    String[] type, type1;
    static int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_view);

        Intent intent = getIntent();
        bdn = intent.getExtras();
        posi = bdn.getInt("position");
        p = posi;

        spinner = (Spinner)findViewById(R.id.spinnerBrand);
        tv0 = (TextView)findViewById(R.id.tv0);

        Typeface tf = Typeface.createFromAsset(getAssets(),"font.ttf");
        tv0.setTypeface(tf);

        switch (posi){
            case 0:
                type = getResources().getStringArray(R.array.type);
                customadapter = new CustomAdapter(getApplicationContext(), logo, type);
                spinner.setAdapter(customadapter);
                break;
            case 1:
                type = getResources().getStringArray(R.array.type);
                customadapter = new CustomAdapter(getApplicationContext(), logo, type);
                spinner.setAdapter(customadapter);
                break;
            case 2:
                type = getResources().getStringArray(R.array.type);
                customadapter = new CustomAdapter(getApplicationContext(), logo, type);
                spinner.setAdapter(customadapter);
                break;
            case 3:
                type = getResources().getStringArray(R.array.type);
                customadapter = new CustomAdapter(getApplicationContext(), logo, type);
                spinner.setAdapter(customadapter);
                break;
            case 4:
                type = getResources().getStringArray(R.array.type);
                customadapter = new CustomAdapter(getApplicationContext(), logo, type);
                spinner.setAdapter(customadapter);
                break;
            case 5:
                type = getResources().getStringArray(R.array.type);
                customadapter = new CustomAdapter(getApplicationContext(), logo, type);
                spinner.setAdapter(customadapter);
                break;
            case 6:
                type = getResources().getStringArray(R.array.type);
                customadapter = new CustomAdapter(getApplicationContext(), logo, type);
                spinner.setAdapter(customadapter);
                break;
        }
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                Process();
                break;
            case 1:
                Process();
                break;
            case 2:
                Process();
                break;
            case 3:
                Process();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Process(){
        FetchData p = new FetchData();
        p.execute();
    }

}
