package com.lionzxy.firstplugin.command;

import com.lionzxy.firstplugin.configs.Config;
import com.lionzxy.firstplugin.local.Localaze;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 01.08.2015.
 */
public class Job {
    public static List<String> jobId = new ArrayList<>();
    public static List<String> jobName = new ArrayList<>();

    public static boolean job(CommandSender sender,String[] args){

        //check for first argument
        if(args.length>=1){
            //Only op or only from console
            //job add
            if (args[0].equalsIgnoreCase("add"))
                if (!(sender instanceof Player) || ((Player) sender).isOp()){
                    add(sender, args);
                    return false;}
                else System.out.println(Localaze.ERR_PERMISION);
            //Only op or only from console
            //job remove
            if (args[0].equalsIgnoreCase("remove"))
                if (!(sender instanceof Player) || ((Player) sender).isOp()){
                    remove(sender, args);
                    return false;}
                else System.out.println(Localaze.ERR_PERMISION);


            //job list
            if (args[0].equalsIgnoreCase("list")){
                list(sender);
                return false;}

            //job get
            if (args[0].equalsIgnoreCase("get")){
                get(sender, args);
                return false;}

            //job help
            if (args[0].equalsIgnoreCase("help"))
                help(sender);

            //job reload
            if(args[0].equalsIgnoreCase("reload"))
                Config.reload(sender);

        }else {
            sender.sendMessage(Localaze.ERR_ARGUMENT);
            for(String a : Localaze.HELP_JOB)
                sender.sendMessage(a);
        }
        return false;
    }

    static void add(CommandSender sender,String[] args){
        if(args.length>=1){
            if(!findJob(args[1])) {
                String jobIdTime = args[1];
                String jobNameTime = "";
                sender.sendMessage(String.valueOf(args.length));
                if (args.length >= 3)
                    for (int i = 2; i < args.length; i++)
                        if (i != args.length-1)
                            jobNameTime = jobNameTime + args[i] + " ";
                        else jobNameTime = jobNameTime + args[i];
                else jobNameTime = jobIdTime;
                jobId.add(jobIdTime);
                jobName.add(jobNameTime);
                sender.sendMessage(Localaze.JOB_ADD_P1 + jobIdTime + Localaze.JOB_ADD_P2 + jobNameTime);
            }else sender.sendMessage(Localaze.JOB_ADD_ERR_ALREADY);
        }else sender.sendMessage(Localaze.JOB_ADD_ERR_FIRST_ARG_IS_NULL);
    }

    static void remove(CommandSender sender,String[] args){
        if(args.length>=1){
            if(findJob(args[1])){
                for(int i = 0; i < jobId.size(); i++)
                    if(jobId.get(i).equalsIgnoreCase(args[1])){
                        jobId.remove(i);
                        jobName.remove(i);
                    }
                sender.sendMessage(Localaze.JOB_REMOVE+args[1]);
            }else sender.sendMessage(Localaze.JOB_REMOVE_ERR_FOUND);
        }else sender.sendMessage(Localaze.JOB_ADD_ERR_FIRST_ARG_IS_NULL);

    }

    static void list(CommandSender sender){
        sender.sendMessage(Localaze.JOB_LIST);
        for(String a : jobName)
            sender.sendMessage(a);
    }

    static void get(CommandSender sender,String[] args){
        if(findJob(args)>0){
            sender.sendMessage(Localaze.JOB_GET + jobId.get(findJob(args)));
        }else sender.sendMessage(Localaze.JOB_GET_ERR);
    }

    static void help(CommandSender sender){
        for(String a : Localaze.HELP_JOB)
            sender.sendMessage(a);
    }
    static int findJob(String[] args){
        String jobNameTime = "";
        for (int i = 1; i < args.length; i++)
            if (i != args.length-1)
                jobNameTime = jobNameTime + args[i] + " ";
            else jobNameTime = jobNameTime + args[i];
        for(int i = 0; i < jobName.size(); i++)
            if(jobNameTime.equalsIgnoreCase(jobName.get(i)))
                return i;
        return -1;
    }

    static boolean findJob(String jobid){
        for(String a : jobId)
            if(a.equalsIgnoreCase(jobid))
                return true;
        return false;
    }
}
