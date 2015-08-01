package com.lionzxy.firstplugin.command;

import com.lionzxy.firstplugin.local.Localaze;
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
                add((Player) sender, args);

            }else {
                sender.sendMessage(Localaze.REQ_ERR_ARG_IS_NULL);
                for(String a : Localaze.REQ_HELP)
                    sender.sendMessage(a);}
        }else sender.sendMessage(Localaze.REQ_ERR_PLAYER);
        return false;
    }

    static void add(Player player,String[] args){
        if(args.length>=2)
            if(Job.findJob(args[1]))
                if(!findReq(player,args[1]))
                    if(args.length>=3) {
                        String reqDes = "";
                        for(int i = 2; i < args.length-1; i++)
                            if(i !=args.length-1){
                                reqDes=reqDes + args[i] + " ";
                            }else reqDes=reqDes + args[i];
                        reqJob.add(args[1]);
                        reqUUID.add(player.getUniqueId());
                        reqDescr.add(reqDes);
                    }
                    else player.sendMessage(Localaze.REQ_ADD_ERR_NOT_DESCR);
                else player.sendMessage(Localaze.REQ_ADD_ERR_ALREADY);
            else player.sendMessage(Localaze.REQ_ADD_ERR_JOB_NOT_FOUND);
        else player.sendMessage(Localaze.REQ_ERR_ARG_IS_NULL);
    }

    public static boolean findReq(Player player, String job){
        for(int i = 0; i < reqUUID.size(); i++)
            if(player.getUniqueId().equals(reqUUID.get(i)))
                if(reqJob.get(i).equalsIgnoreCase(job))
                    return true;
        return false;
    }
}
