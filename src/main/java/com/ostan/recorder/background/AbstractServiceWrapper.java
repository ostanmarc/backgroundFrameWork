package com.ostan.recorder.background;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * Created by marco on 02/08/2016.
 */
public abstract class AbstractServiceWrapper {

    Messenger messenger;
    Context ctx;
    protected static AbstractServiceWrapper instance;

    protected AbstractServiceWrapper(Context ctx) {
        this.ctx = ctx;
        ctx.bindService(new Intent(ctx, getServiceClass()), connection, Context.BIND_AUTO_CREATE);
    }


    protected abstract Class getServiceClass();


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };

    public void Do(int featureId, int action, Object object){
        Message msg = new Message();
        msg.what = featureId;
        msg.arg1 = action;
        msg.obj = object;
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
