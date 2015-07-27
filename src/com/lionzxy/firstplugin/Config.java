package com.lionzxy.firstplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 27.07.2015.
 */
public class Config {
    public static JobPlugin plugin;
    public static List<String> jobList = new ArrayList<String>();
    public static List<String> requestJob = new ArrayList<String>();

    public static void onEnable(JobPlugin inPlugin){
        plugin=inPlugin;
        plugin.getConfig().options().copyDefaults(true);
        try{
            plugin.getConfig().getList("general.jobList").size();
        }catch (NullPointerException e){
            System.out.println("Create config for JobPlugin");
            plugin.getConfig().set("general.jobList",jobList);
            ((List<String>) plugin.getConfig().getList("general.jobList")).add("Farmer");
        }
        try{
            plugin.getConfig().getList("reqJob.list").size();
        }catch (NullPointerException e){
            System.out.println("Create config for JobPlugin");
            plugin.getConfig().set("reqJob.list",requestJob);
        }
        jobList=(List<String>) plugin.getConfig().getList("general.jobList");
        requestJob=(List<String>) plugin.getConfig().getList("reqJob.list");
        plugin.saveConfig();
    }

    public static boolean removeReq(String job, Player player){
        for(int i = 0; i < requestJob.size(); i++)
            if((requestJob.get(i).substring(0,requestJob.get(i).indexOf(":"))).equalsIgnoreCase(job)){
                if((requestJob.get(i).substring(requestJob.get(i).indexOf(":") + 1, requestJob.get(i).indexOf(":", requestJob.get(i).indexOf(":")+1)).equalsIgnoreCase(player.getDisplayName()))){
                    ((List<String>) plugin.getConfig().getList("reqJob.list")).remove(i);
                    player.sendMessage(ChatColor.AQUA + "Delete req for "+job+" complete!");
                    plugin.saveConfig();
                    return true;
                }}
        player.sendMessage(ChatColor.RED + "Not found req or job. This command request enter jobname and have req on this job!(Ex. '/req remove Farmer' remove req on Farmer");
        return false;

    }

    public static void addReq(String job,String req,Player player){
        requestJob.add(job + ":" + player.getDisplayName() + ":" + req);
        plugin.saveConfig();
    }

    public static void addJob(String job){
        jobList.add(job);
        plugin.saveConfig();
    }

    public static boolean removeJob(String job){
        for(int i = 0; i < jobList.size(); i++)
            if(jobList.get(i).equalsIgnoreCase(job)){
                jobList.remove(i);
                return true;}
        plugin.saveConfig();
        return false;
    }

    public static boolean findJob(String job){
        for(String i : jobList)
            if(i.equalsIgnoreCase(job))
                return true;
        return false;
    }

}
