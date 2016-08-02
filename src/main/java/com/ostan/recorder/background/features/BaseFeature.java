package com.ostan.recorder.background.features;

import android.content.Context;

/**
 * Created by marco on 02/08/2016.
 */
public abstract class BaseFeature extends AbstractFeature {

    public static final int TURN_FEATURE_EVENTS_RECEIVING_ON = 1;
    public static final int TURN_FEATURE_EVENTS_RECEIVING_OFF = -1;

    private boolean isTurnedOn = true;
    public BaseFeature(Context context) {
        super(context);
    }


    protected boolean baseAct(int action, int primitiveArg, Object obj){
        switch (action){
            case TURN_FEATURE_EVENTS_RECEIVING_OFF:
                isTurnedOn = false;
                break;
            case TURN_FEATURE_EVENTS_RECEIVING_ON:
                isTurnedOn = true;
                break;
            default:
                return false;
        }
        return isTurnedOn;
    }
}
