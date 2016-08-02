package com.ostan.recorder.background;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import com.ostan.recorder.background.features.AbstractFeature;
import com.ostan.recorder.utils.SystemLogger;

import java.util.HashMap;

/**
 * Created by marco on 02/08/2016.
 */
public abstract class AbstractAppService  extends Service {
    HashMap<Integer, AbstractFeature> features = new HashMap<>();
    Context context;

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if(features.containsKey(msg.what)) {
                SystemLogger.debugRegardlessSubscription(features.get(msg.what).getClass().getName() + "  --  GOT MESSAGE: " + msg.arg1);
                features.get(msg.what).act(msg.arg1, msg.arg2, msg.obj);
            } else {
                SystemLogger.debugRegardlessSubscription("NO FEATURE FOUND");
            }
        }
    }

    final Messenger messenger = new Messenger(new IncomingHandler());

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        registerFeatures();
        SystemLogger.debugRegardlessSubscription("Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SystemLogger.debugRegardlessSubscription("Service destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    /**
     * Here should all the calls to @method registerSingleFeature
     * to be performed
     * */
    protected abstract void registerFeatures();

    /**
     * Method that should be used to register any feature that
     * is desired to run in a service context
     * */
    protected void registerSingleFeature(AbstractFeature feature){
        feature.onRegister();
        features.put(feature.getFeatureId(), feature);
    }
}
