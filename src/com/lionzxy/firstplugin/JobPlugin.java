package com.lionzxy.firstplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.List;
import java.util.logging.Logger;

public class JobPlugin extends JavaPlugin{



    public void onEnable(){
        PluginDescriptionFile pdfFile= getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        Config.onEnable(this);
        logger.info(pdfFile.getName()+" load plugin");
    }
    public void onDisable(){
        PluginDescriptionFile pdfFile= getDescription();
        Logger logger = Bukkit.getLogger();
        this.saveConfig();
        logger.info(pdfFile.getName()+" unload plugin");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("job"))
             return job(sender,args);

        else if(label.equalsIgnoreCase("req")){
            req((Player)sender,args);}

    return false;
    }

    public boolean job(CommandSender sender,String[] args){
        if((sender instanceof Player&&((Player)sender).isOp())||!(sender instanceof Player)) {
            //job add %nickname%
            try {
                if (args[0].equalsIgnoreCase("add")) {
                    String itog = args[1];
                    if (args.length > 2)
                        for (int i = 2; i < args.length; i++)
                            itog = itog + " " + args[i];
                    Config.addJob(itog, sender);
                    return false;
                }
            } catch (NullPointerException e) {
                sender.sendMessage(ChatColor.RED + "This command request enter jobname!(Ex. '/job add Farmer' add job Farmer)");
            }
            //job remove %nickname%
            try {
                if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length>=2) {
                        if (Config.removeJob(args[1]))
                            sender.sendMessage(ChatColor.GREEN + "Remove job: " + args[1]);
                        else {
                            sender.sendMessage(ChatColor.RED + "Job not found!");
                            printAllJob(sender);
                        }
                    } else
                        sender.sendMessage(ChatColor.RED + "This command request enter jobname!(Ex. '/job remove Farmer' remove job Farmer)");
                }
            } catch (NullPointerException e) {
                sender.sendMessage(ChatColor.RED + "This command request enter jobname!(Ex. '/job remove Farmer' remove job Farmer)");
            }
                if(args[0].equalsIgnoreCase("reload")){
                    onDisable();
                    onEnable();
                }

        }else sender.sendMessage(ChatColor.RED + "You don't have permission");
        if(args[0].equalsIgnoreCase("list")){
            for(String a : Config.jobList){
                sender.sendMessage(ChatColor.GREEN+a);
            }
        }
        if(args[0].equalsIgnoreCase("help")){
            sender.sendMessage(ChatColor.BLUE+"=============================================================================================");
            sender.sendMessage(ChatColor.BLUE+"/job add %jobname% - Add a new job. "+ChatColor.RED+"Only for admin");
            sender.sendMessage(ChatColor.BLUE+"/job remove %jobname% - Remove a job and all req fo this job. "+ChatColor.RED+"Only for admin");
            sender.sendMessage(ChatColor.BLUE+"/job list %jobname% - List all job on this server ");
            sender.sendMessage(ChatColor.BLUE+"=============================================================================================");
        }
        return false;}

    public void printAllJob(CommandSender sender){
        for(String i : Config.jobList)
            sender.sendMessage(i);
    }
    public void req(Player player,String[] args){
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

    }
}
