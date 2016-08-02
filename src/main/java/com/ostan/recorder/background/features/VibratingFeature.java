package com.ostan.recorder.background.features;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by marco on 01/05/2016.
 */
public class VibratingFeature extends BaseFeature {

    public static final int FEATURE_ID = 29292;
    public static final int SHORT_VIBRATING_FEEDBACK = 0;
    public static final int LONG_VIBRATING_FEEDBACK = 1;

    public VibratingFeature(Context context) {
        super(context);
    }

    @Override
    public int getFeatureId() {
        return FEATURE_ID;
    }

    @Override
    public void act(int action, int primitiveArg, Object obj) {
        if(super.baseAct(action,primitiveArg,obj)){
            return;
        }
        switch (action) {
            case SHORT_VIBRATING_FEEDBACK:
              vibrate(100);
                break;
            case LONG_VIBRATING_FEEDBACK:
                vibrate(400);
                break;
        }
    }

    @Override
    public void onRegister() {

    }

    private void vibrate(int time){
        ((Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(time);
    }
}
