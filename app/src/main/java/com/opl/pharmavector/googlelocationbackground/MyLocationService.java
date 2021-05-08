package com.opl.pharmavector.googlelocationbackground;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.location.LocationResult;


public class MyLocationService extends BroadcastReceiver {

    public static final String ACTION_PROCESS_UPDATE= "com.opl.pharmavector.googlelocationbackground.UPDATE_LOCATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null){
            final String aciton = intent.getAction();
            if(ACTION_PROCESS_UPDATE.equals(aciton)){
                LocationResult result =LocationResult.extractResult(intent);
                if(result !=null){
                    Location location = result.getLastLocation();
                    String location_string = new StringBuilder(""+location.getLatitude()).
                            append("/").append(location.getLongitude()).
                            toString();

                    String lat= String.valueOf(location.getLatitude());
                    String lang= String.valueOf(location.getLongitude());

                    double latitude = location.getLatitude();
                    double langtitude= location.getLongitude();
                    Log.e("locationupdate==>",lat +"----"+lang);
                    try{
                        MainActivity.getInstance().updateTextView(location_string);
                        MainActivity.getInstance().updateDatabase(lat,lang);
                        MainActivity.getInstance().getAddress(latitude,langtitude);


                    } catch (Exception e) {
                        Toast.makeText(context,location_string,Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }

        }
    }
}

