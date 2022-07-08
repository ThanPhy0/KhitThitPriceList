package com.thanphyo.khitthitpricelist;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by Than Phyo on 7/22/2020.
 */
public class Brand extends Activity {

    public TextView tv0, tv1, tv2, tv3, tv4, devFb;
    static GridView gridView;
    static TextView undNotic;
    Button btnInfo, ok;
    GridViewCustomAdapter gridViewCustomAdapter;
    String[] ary;

    int logo[] = {R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a};
    String[] type;
    private CustomAdapter customadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand);

        new Updater(this, "https://thanwaiphyo.000webhostapp.com/KhitThit/Updater.json");

        undNotic = (TextView)findViewById(R.id.undNotic);
        TextView tvK = (TextView)findViewById(R.id.tvK);
        TextView tvH = (TextView)findViewById(R.id.tvH);
        btnInfo = (Button)findViewById(R.id.btnInfo);
        ok = (Button)findViewById(R.id.btnOk);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Brand.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.info_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                ok = (Button) dialog.findViewById(R.id.ok);
                ok.setBackgroundColor(Color.TRANSPARENT);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                devFb = (TextView)dialog.findViewById(R.id.devFb);
                devFb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(OpenMyProfile(Brand.this, "fb://profile/100010841646874", "https://mobile.facebook.com/profile.php?id=100010841646874"));
                    }
                });
            }

        });



        Typeface tf = Typeface.createFromAsset(getAssets(),"font.ttf");
        undNotic.setTypeface(tf);

        undNotic.setSelected(true);

        Typeface t = Typeface.createFromAsset(getAssets(), "MyanmarPunk.ttf");
        tvH.setTypeface(t);

        Typeface tt = Typeface.createFromAsset(getAssets(), "MyanmarPunk.ttf");
        tvK.setTypeface(tt);

        gridView = (GridView)findViewById(R.id.brandGrid);
        gridViewCustomAdapter = new GridViewCustomAdapter(this);
        gridView.setAdapter(gridViewCustomAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Intent i = new Intent(Brand.this, PriceView.class);
                        i.putExtra("position", position);
                        startActivity(i);
                        break;
                    case 1:
                        Intent i1 = new Intent(Brand.this, PriceView.class);
                        i1.putExtra("position", position);
                        startActivity(i1);
                        break;
                    case 2:
                        Intent i2 = new Intent(Brand.this, PriceView.class);
                        i2.putExtra("position", position);
                        startActivity(i2);
                        break;
                    case 3:
                        Intent i3 = new Intent(Brand.this, PriceView.class);
                        i3.putExtra("position", position);
                        startActivity(i3);
                        break;
                    case 4:
                        Intent i4 = new Intent(Brand.this, PriceView.class);
                        i4.putExtra("position", position);
                        startActivity(i4);
                        break;
                    case 5:
                        Intent i5 = new Intent(Brand.this, PriceView.class);
                        i5.putExtra("position", position);
                        startActivity(i5);
                        break;
                    case 6:
                        Intent i6 = new Intent(Brand.this, PriceView.class);
                        i6.putExtra("position", position);
                        startActivity(i6);
                        break;
                }
            }
        });
    }

    public static Intent OpenMyProfile(Context context, String uri, String uriBrowser){
        try{
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent("android.intent.action.VIEW", Uri.parse(uri));
        } catch (Exception e) {
            return new Intent("android.intent.action.VIEW", Uri.parse(uriBrowser));
        }
    }
}
