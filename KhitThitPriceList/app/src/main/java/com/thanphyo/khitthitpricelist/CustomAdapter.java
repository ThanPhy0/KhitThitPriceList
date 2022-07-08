package com.thanphyo.khitthitpricelist;
import android.content.*;
import android.view.*;
import android.widget.*;

public class CustomAdapter extends BaseAdapter
{
	Context context;
	int logo[];
	String[] brand;
	LayoutInflater lf;
	
	public CustomAdapter(Context applicationContext, int[] logo, String[] brand){
		this.context = applicationContext;
		this.logo = logo;
		this.brand = brand;
		lf = (LayoutInflater.from(applicationContext));
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return logo.length;
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return 0;
	}

	@Override
	public View getView(int i, View view, ViewGroup p3)
	{
	    view = lf.inflate(R.layout.brand_spinner_row, null);
		ImageView brandImg = (ImageView)view.findViewById(R.id.brandImg);
		TextView brandNames = (TextView)view.findViewById(R.id.brandNames);
//		brandImg.setImageResource(logo[i]);
		brandNames.setText(brand[i]);

        switch(i){
            case 0:
                brandImg.setImageResource(R.drawable.touch);
                break;
            case 1:
                brandImg.setImageResource(R.drawable.lcd);
                break;
            case 2:
                brandImg.setImageResource(R.drawable.touch_lcd);
                break;
            case 3:
                brandImg.setImageResource(R.drawable.battery);
                break;
//            case 4:
//                brandImg.setImageResource(R.drawable.vivo);
//                break;
//            case 5:
//                brandImg.setImageResource(R.drawable.meizu);
//                break;
//            case 6:
//                brandImg.setImageResource(R.drawable.kenbo);
//                break;
        }
		return view;
	}
	
	
}
