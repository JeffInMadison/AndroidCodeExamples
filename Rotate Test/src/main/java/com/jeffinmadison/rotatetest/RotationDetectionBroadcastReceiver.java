package com.jeffinmadison.rotatetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RotationDetectionBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = RotationDetectionBroadcastReceiver.class.getSimpleName();

    public RotationDetectionBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "Rotation");
    }
}
