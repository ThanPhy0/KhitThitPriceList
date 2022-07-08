package com.thanphyo.khitthitpricelist;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Than Phyo on 7/24/2020.
 */
public class FirstLogo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_logo);

        TextView pwd = (TextView)findViewById(R.id.Pwd);
        Typeface tf = Typeface.createFromAsset(getAssets(),"font1.ttf");
        pwd.setTypeface(tf);

        if(!NetTest()){
           netCheck();
        }else{
            Thread timer = new Thread(){
                public void run(){
                    try{
                        sleep(3000);
                        Intent i = new Intent(FirstLogo.this, Brand.class);
                        startActivity(i);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.start();
        }
    }

    private boolean NetTest() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo != null;
    }

    public void netCheck() {
        final Dialog dialog = new Dialog(FirstLogo.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.connection_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        Button btnOk = (Button)dialog.findViewById(R.id.btnOk);
        btnOk.setBackgroundColor(Color.TRANSPARENT);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
    }

}
