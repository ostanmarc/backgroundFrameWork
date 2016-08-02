package com.ostan.recorder.background.features;

import android.content.Context;

/**
 * Created by marco on 25/03/2016.
 */
public abstract class AbstractFeature {
    public abstract int getFeatureId();
    public abstract void act(int action, int primitiveArg, Object obj);
    public abstract void onRegister();
    Context context;

    public AbstractFeature(Context context) {
        this.context = context;
    }

    public void handleAction(int action, Object... obj){
        int actionInternal = action;
        int primitiveParameter = (int)obj[0];
        Object internalObj = obj[1];
        act(actionInternal, primitiveParameter, internalObj);

    }




}
