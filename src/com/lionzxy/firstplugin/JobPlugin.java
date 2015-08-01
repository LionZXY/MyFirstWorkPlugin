package com.lionzxy.firstplugin;

import com.lionzxy.firstplugin.command.Job;
import com.lionzxy.firstplugin.command.Req;
import com.lionzxy.firstplugin.configs.Config;
import com.lionzxy.firstplugin.local.Localaze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class JobPlugin extends JavaPlugin{



    public void onEnable(){
        PluginDescriptionFile pdfFile= getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        Config.onEnable(this);
    }
    public void onDisable(){
        PluginDescriptionFile pdfFile= getDescription();
        Logger logger = Bukkit.getLogger();
        this.saveConfig();
        Config.onDisable();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("job"))
             return Job.job(sender, args);

        else if(label.equalsIgnoreCase("req"))
            Req.req(sender, args);


    return false;
    }


    /*public void req(Player player,String[] args){
        //req add
        try{
        if(args[0].equalsIgnoreCase("add")){
            if(!Utils.checkToReq(args[1],player.getDisplayName())&&Config.findJob(args[1])){
                if(args.length>=3){
                    String description = "";
                    for(int i = 2; i < args.length; i++)
                        description=description+" "+args[i];
                    Config.addReq(args[1],description,player);
                    player.sendMessage(ChatColor.GREEN + "Req added! Job: "+args[1]+". Description: "+description);
                } else player.sendMessage(ChatColor.RED + "You don't enter description for job!This command request enter jobname and description!(Ex. '/req add Farmer I want farmer for my sexual fantasy' add req Farmer with description)");
            }else player.sendMessage(ChatColor.RED +"You have already req fo this job! ");
        }}catch (NullPointerException e){player.sendMessage(ChatColor.DARK_PURPLE +"This command request enter jobname and description!(Ex. '/req add Farmer I want farmer for my sexual fantasy' add req Farmer with description)");}
        //req remove
        try{
        if(args[0].equalsIgnoreCase("remove"))
            Config.removeReq(args[1],player);
        }catch (NullPointerException e){
            player.sendMessage("Not found req or job. This command request enter jobname and have req on this job!(Ex. '/req remove Farmer' remove req on Farmer");
        }
        //req top
        if(args[0].equalsIgnoreCase("top")){
            player.sendMessage(ChatColor.GREEN+"Top req for job");
            String[] arr = new String[Config.jobList.size()];
            for(int i = 0; i < Config.jobList.size(); i++){
                arr[i]=Config.jobList.get(i);
            }
            String temp;
            for(int i = 0;i < arr.length; i++){
                for(int b = 0; b < arr.length-i;b++){
                    if(!(Utils.getJobVacance(arr[i+b]).size()>=Utils.getJobVacance(arr[i]).size())){
                        temp=arr[i];
                        arr[i]=arr[i+b];
                        arr[i+b]=temp;
                        b=0;
                    }
                }}
                for(int i = arr.length-1; i >= 0; i--){
                    player.sendMessage(arr[i]+": ["+Utils.getJobVacance(arr[i]).size()+"]");}
        }
        try{
        if(args[0].equalsIgnoreCase("list")){
            player.sendMessage(ChatColor.GREEN+"All req for job: "+args[1]);
            for(String a : Utils.getJobVacance(args[1]))
                player.sendMessage(a);
        }}catch (NullPointerException a){player.sendMessage("Enter a job!!!");}
        if(args[0].equalsIgnoreCase("help")){
            player.sendMessage(ChatColor.BLUE+"=============================================================================================");
            player.sendMessage(ChatColor.BLUE+"/req add %jobname% %des% - Add a new job. "+ChatColor.RED+"Only for admin");
            player.sendMessage(ChatColor.BLUE+"=============================================================================================");

        }

    }*/
}
