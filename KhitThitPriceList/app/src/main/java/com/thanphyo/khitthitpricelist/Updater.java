package com.thanphyo.khitthitpricelist;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Than_Phyo on 5/13/2008.
 */
public class Updater {
    private Activity activity;
    private String json_url, downPath;
    DownloadManager dm;
    private int versionCode = 0;
    private String download_link = null;
    private ProgressDialog pg;
    private DownloadManager.Request mRequest;
    private long downloadFileId;

    public Updater(Activity activity, String json_url){
        this.activity = activity;
        this.json_url = json_url;
        check();
    }

    private void check() {
        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                try{
                    return fetch(json_url);
                }catch (Exception e){
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s!=null){
                    letCheck(s);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void letCheck(String response) {
        if(response!=null){
            try {
                JSONObject obj = new JSONObject(response);
                String title = obj.getString("title");
                String msg = obj.getString("message");
                download_link = obj.getString("link");
                boolean force = obj.getBoolean("force");
                versionCode = obj.getInt("versionCode");
                String noti = obj.getString("notice");

                PackageManager pm = activity.getPackageManager();
                PackageInfo packageInfo;
                int currentVersion = 0;
                try {
                    packageInfo = pm.getPackageInfo(activity.getPackageName(), 0);
                    currentVersion = packageInfo.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                if(versionCode > currentVersion){
                    letUpdate(title, msg, force);
                }else{
                    Brand.undNotic.setText(noti);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void letUpdate(String title, String msg, boolean force) {
        final Dialog dilog = new Dialog(activity);
        dilog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dilog.setContentView(R.layout.notice_dialog);
        dilog.setCanceledOnTouchOutside(false);
        dilog.setCancelable(false);
        dilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dilog.show();

        TextView noticeTitle = (TextView)dilog.findViewById(R.id.noticeTitle);
        TextView mesg = (TextView)dilog.findViewById(R.id.mesg);
        Button btnUpdate = (Button)dilog.findViewById(R.id.btnUpdate);
        btnUpdate.setBackgroundColor(Color.TRANSPARENT);

        noticeTitle.setText(title);
        mesg.setText(msg);

        if(!force){
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {dilog.dismiss();
                    downloadFile(download_link, activity.getString(R.string.app_name) + "_v" + Integer.toString(versionCode) + ".apk");


                }
            });
        }
    }

    private void downloadFile(String url, String filename) {
        pg = new ProgressDialog(activity);
        pg.setMessage("Downloading...");
        pg.setCancelable(false);
        pg.show();
        try{
            String mBaseFolderPath =  downPath;
            if(!new File(mBaseFolderPath).exists()){
                new File(mBaseFolderPath).mkdirs();
            }
            File myFile = new File(mBaseFolderPath + filename);
            if(!myFile.exists()){
                String mFilePath = "file://" + mBaseFolderPath + filename;
                Uri downloarUri = Uri.parse(url);
                mRequest = new DownloadManager.Request(downloarUri);
                mRequest.setDestinationUri(Uri.parse(mFilePath));
                mRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadFileId = dm.enqueue(mRequest);
                IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
                activity.registerReceiver(downloadReceiver, filter);
            }else{
                openFile(myFile.toString());
            }
        }catch (Exception e){
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final Uri uri = dm.getUriForDownloadedFile(downloadFileId);
            final String apk = getRealPartFromURI(uri);
            openFile(apk);
        }
    };

    private void openFile(String apk) {
        if(pg != null){
            pg.dismiss();
        }
        File f = new File(apk);
        Uri fileUri = Uri.fromFile(f);
        if(Build.VERSION.SDK_INT >= 24){
            fileUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", f);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, fileUri);
        intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
        intent.setDataAndType(fileUri, "application/vnd.android" + ".package.archive");
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivity(intent);
        activity.finish();
    }

    private String getRealPartFromURI(Uri contentUri) {
        String Path = null;
        String[] prog = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity.getContentResolver().query(contentUri, prog, null, null, null);
        if(cursor.moveToFirst()){
            int colum_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            Path = cursor.getString(colum_index);
        }
        cursor.close();
        return Path;
    }

    private String fetch(String link) {
        String result = "";
        try{
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            int ResponseCode = connection.getResponseCode();
            if(ResponseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream ba = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1){
                    ba.write(buffer, 0, length);
                }
                ba.close();
                result = ba.toString("UTF-8");
            }
        } catch (Exception e) {
           result = e.getMessage();
        }
        return result;
    }
}
