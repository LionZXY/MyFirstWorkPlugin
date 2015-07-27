package com.lionzxy.firstplugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 27.07.2015.
 */
public class Utils {
    //Get list nick + desctiptions for job vacanse
    public static List<String> getJobVacance(String job){
        List<String> nickList = new ArrayList<String>();
        for(String workWithString : Config.requestJob){
            int a = workWithString.indexOf(":");
            if(workWithString.substring(0,a).equalsIgnoreCase(job))
                nickList.add(workWithString.substring(a+1));
        }
        return nickList;
    }
     public static boolean checkToReq(String job, String nick){
         for(String i : Config.requestJob){
            int a = i.indexOf(":");
            if((i.substring(0,a)).equalsIgnoreCase(job))
                if((i.substring(a+1,i.indexOf(":",a+1))).equalsIgnoreCase(nick))
                    return true;
         }
         return false;
     }

}
