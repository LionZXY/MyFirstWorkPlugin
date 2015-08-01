package com.lionzxy.firstplugin.local;

import org.bukkit.ChatColor;

/**
 * Created by nikit_000 on 01.08.2015.
 */
public class Localaze {

    //job
    public static String JOB_ADD_ERR_ALREADY = ChatColor.RED + "[JobPlugin] This job already add";
    public static String JOB_ADD_ERR_FIRST_ARG_IS_NULL= ChatColor.RED + "[JobPlugin] For this command need %jobid%";
    public static String JOB_ADD_P1 = ChatColor.AQUA + "[JobPlugin] Add job is successfully! With idJob = ";
    public static String JOB_ADD_P2 = ChatColor.AQUA + " and Name Job = ";
    public static String JOB_REMOVE = ChatColor.AQUA + "[JobPlugin] Remove job: ";
    public static String JOB_REMOVE_ERR_FOUND= ChatColor.RED + "[JobPlugin] This jobid not found";
    public static String JOB_RELOAD = ChatColor.AQUA + "[JobPlugin] Plugin reload";
    public static String JOB_GET_ERR = ChatColor.RED + "[JobPlugin] This job not found";
    public static String JOB_GET = ChatColor.AQUA + "[JobPlugin] IdJob for job: ";
    public static String JOB_LIST = ChatColor.AQUA + "[JobPlugin] All job: ";

    //req
    public static String REQ_ERR_PLAYER = ChatColor.RED + "[JobPlugin] You must be a player for this command";
    public static String REQ_ERR_ARG_IS_NULL = ChatColor.RED+ "[JobPlugin] This command need argument";
    public static String REQ_ADD_ERR_JOB_NOT_FOUND = ChatColor.RED + "[JobPlugin] This job not found";
    public static String REQ_ADD_ERR_ALREADY = ChatColor.RED + "[JobPlugin] This request already add";
    public static String REQ_ADD_ERR_NOT_DESCR = ChatColor.RED + "[JobPlugin] This command need description";



    public static String HELP_LINE = ChatColor.AQUA + "=====================================================";
    public static String[] HELP_JOB = new String[]{
            HELP_LINE,
            ChatColor.AQUA + "1./job add %jobid% %Job Name%  for add job. %Job Name% may be null. "+ ChatColor.RED + "Only for OP",
            ChatColor.AQUA + "2./job remove %jobid%  for remove job. "+ ChatColor.RED + "Only for OP",
            ChatColor.AQUA + "3./job reload  for reload job plugin. "+ ChatColor.RED + "Only for OP",
            ChatColor.AQUA + "4./job list for see all name job.",
            ChatColor.AQUA + "5./job get %Job Name% for get job id for this job.",
            ChatColor.AQUA + "6./job help  for get help on job command",
            HELP_LINE
    };

    public static String[] REQ_HELP = new String[]{
            HELP_LINE,

            HELP_LINE
    };

    public static String ERR_GENERATE_LIST_CONFIG_JOB = ChatColor.RED+"[JobPlugin] WARNING!!! Job list not generate. Now use old version list";
    public static String ERR_GENERATE_LIST_CONFIG_REQ = ChatColor.RED+"[JobPlugin] WARNING!!! Request list not generate. Now use old version list";
    public static String ERR_PERMISION = ChatColor.RED + "[JobPlugin] You don't have permission. For this command you must be op or send this command from console";
    public static String ERR_ARGUMENT = ChatColor.RED + "[JobPlugin] You must enter one ore many argument for this command";

}
