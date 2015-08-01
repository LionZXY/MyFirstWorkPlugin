package com.lionzxy.firstplugin.configs;

import com.lionzxy.firstplugin.JobPlugin;
import com.lionzxy.firstplugin.command.Job;
import com.lionzxy.firstplugin.command.Req;
import com.lionzxy.firstplugin.local.Localaze;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 01.08.2015.
 */
public class Config {
    public static JobPlugin plugin;
    public static List<String> jobList = new ArrayList<String>();
    public static List<String> requestJob = new ArrayList<String>();

    public static void onEnable(JobPlugin plugin1){
        plugin.saveConfig();
        plugin=plugin1;
        generateConfig();
        generateLists();
        plugin.saveConfig();
    }

    public static void onDisable(){
        plugin.saveConfig();
        generateList();
        plugin.saveConfig();
    }


    public static void generateList(){
        plugin.saveConfig();

        if(Job.jobName.size()==Job.jobId.size()) {
            jobList.clear();
            for(int i = 0; i < Job.jobId.size(); i++){
                jobList.add(i,Job.jobId.get(i)+";"+Job.jobName.get(i));
            }
        }else System.out.print(Localaze.ERR_GENERATE_LIST_CONFIG_JOB);


        if(Req.reqJob.size()==Req.reqUUID.size()&&Req.reqUUID.size()==Req.reqDescr.size()){
            requestJob.clear();
            for(int i = 0; i < Req.reqJob.size(); i++){
                requestJob.add(i,Req.reqJob.get(i)+";"+Req.reqUUID.get(i)+";"+Req.reqDescr.get(i));
            }
        }else System.out.print(Localaze.ERR_GENERATE_LIST_CONFIG_REQ);

        plugin.saveConfig();
    }

    public static void generateLists(){
        plugin.saveConfig();
        int a,i;
        Job.jobId.clear();
        Job.jobName.clear();
        for(i = 0; i < jobList.size(); i++){
            Job.jobId.add(i,jobList.get(i).substring(0,jobList.get(i).indexOf(";")));
            Job.jobName.add(i,jobList.get(i).substring(jobList.get(i).indexOf(";")+1));
        }

        Req.reqJob.clear();
        Req.reqUUID.clear();
        Req.reqDescr.clear();
        for(i = 0; i < requestJob.size(); i++){
            a = requestJob.get(i).indexOf(";");
            Req.reqJob.add(i,requestJob.get(i).substring(0,a));
            Req.reqUUID.add(i,requestJob.get(i).substring(a+1,requestJob.get(i).indexOf(";",a+1)));
            Req.reqDescr.add(i, requestJob.get(i).substring(requestJob.get(i).indexOf(";",a+1)));
        }

    }

    public static void generateConfig(){
        plugin.getConfig().options().copyDefaults(true);
        try{plugin.getConfig().getList("general.jobList").size();}catch(NullPointerException e){
            plugin.getConfig().set("general.jobList",jobList);
            ((List<String>) plugin.getConfig().getList("general.jobList")).add("jobid;Job Name");
        }

        try{plugin.getConfig().getList("reqJob.list").size();}catch (NullPointerException e){
            plugin.getConfig().set("reqJob.list", requestJob);
            ((List<String>) plugin.getConfig().getList("general.jobList")).add("jobid;UUID;Description");
        }
        plugin.saveConfig();
    }
}
