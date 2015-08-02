package com.lionzxy.firstplugin.command;

import com.lionzxy.firstplugin.configs.Config;
import com.lionzxy.firstplugin.local.Localaze;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nikit_000 on 01.08.2015.
 */
public class Req {
    public static List<String> reqJob = new ArrayList<>();
    public static List<UUID> reqUUID = new ArrayList<>();
    public static List<String> reqDescr = new ArrayList<>();

    public static boolean req(CommandSender sender,String[] args){
        if(sender instanceof Player) {
            if(args.length>=1){
                //req add
                if(args[0].equalsIgnoreCase("add"))
                    add((Player) sender, args);
                //req remove
                else if(args[0].equalsIgnoreCase("remove"))
                    remove((Player) sender, args);
                //req top
                else if(args[0].equalsIgnoreCase("top"))
                    top((Player) sender, args);
                //req list
                else if(args[0].equalsIgnoreCase("list"))
                    list((Player) sender, args);
                //req help
                else if(args[0].equalsIgnoreCase("help"))
                    help((Player) sender);


            }else {
                sender.sendMessage(Localaze.REQ_ERR_ARG_IS_NULL);
                for(String a : Localaze.REQ_HELP)
                    sender.sendMessage(a);}
        }else sender.sendMessage(Localaze.REQ_ERR_PLAYER);
        Config.generateList();
        return false;
    }

    static void add(Player player,String[] args){
        if(args.length >= 2)
            if(Job.findJob( args[1] ))
                if(findReq( player,args[1] ) == -1)
                    if(args.length >= 3) {
                        String reqDes = "";
                        for(int i = 2; i < args.length; i++)
                            if(i != args.length - 1){
                                reqDes=reqDes + args[i] + " ";
                            }else reqDes=reqDes + args[i];
                        reqJob.add(args[1]);
                        reqUUID.add(player.getUniqueId());
                        reqDescr.add(reqDes);
                        player.sendMessage(Localaze.REQ_ADD_P1 + Job.getName(args[1]) + Localaze.REQ_ADD_P2 + reqDes);
                    }
                    else player.sendMessage(Localaze.REQ_ADD_ERR_NOT_DESCR);
                else player.sendMessage(Localaze.REQ_ADD_ERR_ALREADY);
            else player.sendMessage(Localaze.REQ_ADD_ERR_JOB_NOT_FOUND);
        else player.sendMessage(Localaze.REQ_ERR_ARG_IS_NULL);
    }

    static void remove(Player player,String[] args){
        if(args.length >= 2)
            if(Job.findJob(args[1]))
                if(findReq(player,args[1]) != -1){
                    int a = findReq( player,args[1] );
                    reqJob.remove(a);
                    reqUUID.remove(a);
                    reqDescr.remove(a);
                    player.sendMessage(Localaze.REQ_REMOVE_P1 + Job.getName(args[1]) + Localaze.REQ_REMOVE_P2 + player.getDisplayName());
                }
                else player.sendMessage(Localaze.REQ_REMOVE_ERR_NOT_FOUND_REQ + Job.getName(args[1]));
            else player.sendMessage(Localaze.REQ_ADD_ERR_JOB_NOT_FOUND);
        else player.sendMessage(Localaze.REQ_REMOVE_ERR_NOT_JOB);
    }

    static void top(Player player, String[] args){
        player.sendMessage(Localaze.REQ_TOP);
        String[] arr = new String[Job.jobId.size()];
        for(int i = 0; i < Job.jobId.size(); i++){
            arr[i] = Job.jobId.get(i);
        }

        String temp;
        for(int i = 0;i < arr.length; i++){
            for(int b = 0; b < arr.length-i;b++){
                if(!(getAllReq(arr[i+b]).length>=getAllReq(arr[i]).length)){
                    temp=arr[i];
                    arr[i]=arr[i+b];
                    arr[i+b]=temp;
                    b=0;
                }
            }}
        for(int i = arr.length-1; i >= 0; i--){
            player.sendMessage(Job.getName(arr[i]) + ": [" + getAllReq(arr[i]).length + "]");}
    }

    static void list(Player player, String[] args){
        if(args.length>1)
            if(Job.findJob(args[1]))
                if(args.length > 2){
                    listForPage(player, Integer.parseInt(args[2]),args[1]);
                }else listForPage(player, 1 , args[1]);
            else player.sendMessage(Localaze.JOB_REMOVE_ERR_FOUND);
        else player.sendMessage(Localaze.REQ_LIST_ERR_NOT_JOB);
    }

    static void listForPage(Player player, int page, String jobid){
        player.sendMessage(Localaze.REQ_LIST_ALL);
        if(reqUUID.size() >= page*10)
            for(int i = (page-1)*10; i < page ; i++)
                player.sendMessage(Bukkit.getServer().getOfflinePlayer(reqUUID.get(i)).getName() + " : "+reqDescr.get(i));
        else for(int i = (page-1)*10 ; i < reqUUID.size() % 10 ; i++)
            player.sendMessage(Bukkit.getServer().getOfflinePlayer(reqUUID.get(i)).getName() + " : "+reqDescr.get(i));
        player.sendMessage(Localaze.REQ_LIST_NEXT + jobid + " " + (page+1));
    }

    public static int[] getAllReq(String jobid){
        List<Integer> req = new ArrayList<>();
        for(int i = 0; i < reqJob.size(); i++)
            if(reqJob.get(i).equalsIgnoreCase(jobid)) req.add(i);
        return toArray(req);
    }

    public static int findReq(Player player, String jobid){
        for(int i = 0; i < reqUUID.size(); i++)
            if(player.getUniqueId().equals(reqUUID.get(i)))
                if(reqJob.get(i).equalsIgnoreCase(jobid))
                    return i;
        return -1;
    }

    static void help(Player player){
        for(String a : Localaze.REQ_HELP)
            player.sendMessage(a);
    }

    public static int[] toArray(List<Integer> list){
        int[] arr = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            arr[i] = list.get(i);
        return arr;
    }
}
