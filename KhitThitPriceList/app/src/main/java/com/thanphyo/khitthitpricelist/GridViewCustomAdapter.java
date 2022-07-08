package com.thanphyo.khitthitpricelist;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by Than Phyo on 7/22/2020.
 */
public class GridViewCustomAdapter extends ArrayAdapter {
    Context context;
    public GridViewCustomAdapter(Context context) {
        super(context, 0);
        this.context=context;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null){
            LayoutInflater lf = ((Activity)context).getLayoutInflater();
            row = lf.inflate(R.layout.brand_item, parent, false);
            ImageView img = (ImageView)row.findViewById(R.id.brandView);
            int i = position%7;
            switch (i){
                case 0:
                    img.setImageResource(R.drawable.mi);
                    break;
                case 1:
                    img.setImageResource(R.drawable.huawei);
                    break;
                case 2:
                    img.setImageResource(R.drawable.sam);
                    break;
                case 3:
                    img.setImageResource(R.drawable.oppo);
                    break;
                case 4:
                    img.setImageResource(R.drawable.vivo);
                    break;
                case 5:
                    img.setImageResource(R.drawable.meizu);
                    break;
                case 6:
                    img.setImageResource(R.drawable.kenbo);
                    break;
            }
        }
        return row;
    }
}
