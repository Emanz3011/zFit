package com.z.fit.zfit;

/**
 * Created by Zacc on 2016-04-30.
 */
public class Time {

    private int nSeconds;
    private int nMinutes;

    Time(int seconds){
        nSeconds = seconds%60;
        nMinutes = (seconds - nSeconds) / 60;
    }

    String getTimeString(){
        if (nSeconds != 0)
            return nMinutes + ":" + nSeconds;
        else
            return nMinutes + ":00";
    }

    int getTimeInt(){
        return nSeconds + (nMinutes * 60);
    }
}
