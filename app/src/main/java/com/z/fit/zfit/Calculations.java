package com.z.fit.zfit;

/**
 * Created by Zacc on 2016-04-30.
 */
public class Calculations {
    private static int PushupMultiplier = 1;
    private static int CrunchMultiplier = 1;
    private static int JogMultiplier = 18;

    public static int getPushup(int Level){
        return Level* PushupMultiplier;
    }
    public static int getCrunch(int Level){
        return Level* CrunchMultiplier;
    }
    public static Time getJog(int Level){
        return new Time(Level * JogMultiplier);
    }
    public static double getPercentage (int Level){
        if (Level == 1){
            return 100;
        }
        double percentage = ((Double.valueOf(Level)/Double.valueOf(Level-1)) - 1)*100;
        return Math.round(percentage * 10)/10;
    }

    public static Long getTimeValue(String sTime) {
        Long time = Long.valueOf(0);
        time += Long.valueOf(sTime.split(":")[0]) * (60 * 60 * 1000);
        time += Long.valueOf(sTime.split(":")[1]) * (60 * 1000);
        return time;
    }
}
