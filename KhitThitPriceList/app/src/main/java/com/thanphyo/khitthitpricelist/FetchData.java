package com.thanphyo.khitthitpricelist;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Than Phyo on 4/26/2020.
 */
public class FetchData extends AsyncTask<Void,Void,Void> {
	String data ="";
    String MiTouch = "";
    String MiLcd = "";
    String MiTL = "";
    String MiBatte = "";
    String HuaweiTouch = "";
    String HuaweiLcd = "";
    String HuaweiTL = "";
    String HuaweiBatte = "";
    String SamTouch = "";
    String SamLcd = "";
    String SamTL = "";
    String SamBtte = "";
    String OppoTouch = "";
    String OppoLcd = "";
    String OppoTL = "";
    String OppoBatte = "";
    String VivoTouch= "";
    String VivoLcd = "";
    String VivoTL = "";
    String VivoBatte = "";
    String MeizuTouch = "";
    String MeizuLcd = "";
    String MeizuTL = "";
    String MeizuBatte = "";
    String KenboTouch = "";
    String KenboLcd = "";
    String KenboTL = "";
    String KenboBt = "";
    String MMM,mmm;
    static String Notice = "";
    static String a = "dsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    int singleParsed;
    String miTouch,miLcd, miTL, miBatte, huaweiTouch, huaweiLcd, huaweiTL, huaweiBatte, samTouch, samLcd, samTL, samBt, oppoTouch, oppoLcd, oppoTL, oppoBatte, vivoTouch, vivoLcd, vivoTL, vivoBatte, meizuTouch, meizuLcd, meizuTL, meizuBatte, kenboTouch, kenboLcd, kenboTL, kenboBat, notice ;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://thanwaiphyo.000webhostapp.com/KhitThit/pricelist.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject JO = new JSONObject(data);
            miTouch = JO.optString("mi_onltouch");
            miLcd = JO.optString("mi_lcd");
            miTL = JO.optString("mi_tl");
            miBatte = JO.optString("mi_bat");
            huaweiTouch = JO.optString("huawei_onltouch");
            huaweiLcd = JO.optString("huawei_lcd");
            huaweiTL = JO.optString("huawei_tl");
            huaweiBatte = JO.optString("huawei_bat");
            samTouch = JO.optString("samsung_onltouch");
            samLcd = JO.optString("samsung_lcd");
            samTL = JO.optString("samsung_tl");
            samBt = JO.optString("samsung_bt");
            oppoTouch = JO.optString("oppo_onltouch");
            oppoLcd = JO.optString("oppo_lcd");
            oppoTL = JO.optString("oppo_tl");
            oppoBatte = JO.optString("oppo_bat");
            vivoTouch = JO.optString("vivo_onltouch");
            vivoLcd = JO.optString("vivo_lcd");
            vivoTL = JO.optString("vivo_tl");
            vivoBatte = JO.optString("vivo_bat");
            meizuTouch = JO.optString("meizu_onltouch");
            meizuLcd = JO.optString("meizu_lcd");
            meizuTL = JO.optString("meizu_tl");
            meizuBatte = JO.optString("meizu_bat");
            kenboTouch = JO.optString("kenbo_onltouch");
            kenboLcd = JO.optString("kenbo_lcd");
            kenboTL = JO.optString("kenbo_tl");
            kenboBat = JO.optString("kenbo_b");
            notice = JO.optString("notice");

            MiTouch = MiTouch + miTouch ;
            MiLcd = MiLcd + miLcd;
            MiTL = MiTL + miTL ;
            MiBatte = MiBatte + miBatte;
            HuaweiTouch = HuaweiTouch + huaweiTouch;
            HuaweiLcd = HuaweiLcd + huaweiLcd ;
            HuaweiTL = HuaweiTL + huaweiTL ;
            HuaweiBatte = HuaweiBatte + huaweiBatte;
            SamTouch = SamTouch + samTouch;
            SamLcd = SamLcd + samLcd ;
            SamTL = SamTL + samTL ;
            SamBtte = SamBtte + samBt;
            OppoTouch = OppoTouch + oppoTouch;
            OppoLcd = OppoLcd + oppoLcd;
            OppoTL = OppoTL + oppoTL ;
            OppoBatte = OppoBatte + oppoBatte ;
            VivoTouch = VivoTouch + vivoTouch;
            VivoLcd = VivoLcd + vivoLcd;
            VivoTL = VivoTL + vivoTL ;
            VivoBatte = VivoBatte + vivoBatte;
            MeizuTouch = MeizuTouch + meizuTouch;
            MeizuLcd = MeizuLcd + meizuLcd;
            MeizuTL = MeizuTL + meizuTL;
            MeizuBatte = MeizuBatte + meizuBatte;
            KenboTouch = KenboTouch + kenboTouch ;
            KenboLcd = KenboLcd + kenboLcd;
            KenboTL = KenboTL + kenboTL;
            KenboBt = KenboBt + kenboBat;
            Notice = Notice + notice;
           MMM = "gridView1";
            mmm = "gridView2";

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        int gridPosit = PriceView.p;
		int positon = PriceView.spinner.getSelectedItemPosition();
        switch (gridPosit){
            case 0:
                PriceView.tv0.setText(this.MMM);
                switch(positon){
                    case 0:
                        PriceView.tv0.setText(this.MiTouch);
                    break;
                    case 1:
                        PriceView.tv0.setText(this.MiLcd);
                    break;
                    case 2:
                        PriceView.tv0.setText(this.MiTL);
                    break;
                    case 3:
                        PriceView.tv0.setText(this.MiBatte);
                    break;
                }
             break;
            case 1:
                PriceView.tv0.setText(this.MMM);
                switch(positon){
                    case 0:
                        PriceView.tv0.setText(this.HuaweiTouch);
                        break;
                    case 1:
                        PriceView.tv0.setText(this.HuaweiLcd);
                        break;
                    case 2:
                        PriceView.tv0.setText(this.HuaweiTL);
                        break;
                    case 3:
                        PriceView.tv0.setText(this.HuaweiBatte);
                        break;
                }
                break;
            case 2:
                PriceView.tv0.setText(this.MMM);
                switch(positon){
                    case 0:
                        PriceView.tv0.setText(this.SamTouch);
                        break;
                    case 1:
                        PriceView.tv0.setText(this.SamLcd);
                        break;
                    case 2:
                        PriceView.tv0.setText(this.SamTL);
                        break;
                    case 3:
                        PriceView.tv0.setText(this.SamBtte);
                        break;
                }
                break;
            case 3:
                PriceView.tv0.setText(this.MMM);
                switch(positon){
                    case 0:
                        PriceView.tv0.setText(this.OppoTouch);
                        break;
                    case 1:
                        PriceView.tv0.setText(this.OppoLcd);
                        break;
                    case 2:
                        PriceView.tv0.setText(this.OppoTL);
                        break;
                    case 3:
                        PriceView.tv0.setText(this.OppoBatte);
                        break;
                }
                break;
            case 4:
                PriceView.tv0.setText(this.MMM);
                switch(positon){
                    case 0:
                        PriceView.tv0.setText(this.VivoTouch);
                        break;
                    case 1:
                        PriceView.tv0.setText(this.VivoLcd);
                        break;
                    case 2:
                        PriceView.tv0.setText(this.VivoTL);
                        break;
                    case 3:
                        PriceView.tv0.setText(this.VivoBatte);
                        break;
                }
                break;
            case 5:
                PriceView.tv0.setText(this.MMM);
                switch(positon){
                    case 0:
                        PriceView.tv0.setText(this.MeizuTouch);
                        break;
                    case 1:
                        PriceView.tv0.setText(this.MeizuLcd);
                        break;
                    case 2:
                        PriceView.tv0.setText(this.MeizuTL);
                        break;
                    case 3:
                        PriceView.tv0.setText(this.MeizuBatte);
                        break;
                }
                break;
            case 6:
                PriceView.tv0.setText(this.MMM);
                switch(positon){
                    case 0:
                        PriceView.tv0.setText(this.KenboTouch);
                        break;
                    case 1:
                        PriceView.tv0.setText(this.KenboLcd);
                        break;
                    case 2:
                        PriceView.tv0.setText(this.KenboTL);
                        break;
                    case 3:
                        PriceView.tv0.setText(this.KenboBt);
                        break;
                }
                break;
        }
    }
}
