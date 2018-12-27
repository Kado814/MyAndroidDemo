package com.myapplication.myandroiddemo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.myapplication.myandroiddemo.IMyAidlInterface;

public class MyService extends Service {

    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public int add(int arg1, int arg2) throws RemoteException {
            Log.e("kkk", "add=" + arg1);
            Log.e("kkk", "add=" + arg2);
            return arg1 + arg2;
        }

        @Override
        public boolean googlePayCallback(String callback) throws RemoteException {
            Log.e("kkk", "收到支付结果=" + callback);
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            Toast.makeText(getApplicationContext(), callback, Toast.LENGTH_LONG).show();
            return true;
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
