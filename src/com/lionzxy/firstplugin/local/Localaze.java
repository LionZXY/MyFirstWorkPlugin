package com.lionzxy.firstplugin.local;

import org.bukkit.ChatColor;

/**
 * Created by nikit_000 on 01.08.2015.
 */
public class Localaze {

    public static String ERR_GENERATE_LIST_CONFIG_JOB = ChatColor.RED+"WARNING!!! Job list not generate. Now use old version list";
    public static String ERR_GENERATE_LIST_CONFIG_REQ = ChatColor.RED+"WARNING!!! Request list not generate. Now use old version list";
    public static String ERR_PERMISION = ChatColor.RED + "You don't have permission. For this command you must be op or send this command from console";
    public static String ERR_ARGUMENT = ChatColor.RED + "You must enter one ore many argument for this command";

    public static String JOB_GET_ERR = ChatColor.RED + "This job not found";
    public static String JOB_GET = ChatColor.BLUE + "IdJob for job: ";
    public static String JOB_LIST = ChatColor.BLUE + "All job: ";
    public static String REMOVE_JOB_REMOVE = ChatColor.BLUE + "Remove job: ";
    public static String ERR_JOB_REMOVE_FOUND= ChatColor.RED + "This jobid not found";
    public static String ERR_JOB_ADD_ALREADY = ChatColor.RED + "This job already add";
    public static String ERR_JOB_ADD_FIRST_ARG_IS_NULL = ChatColor.RED + "For this command need %jobid%";
    public static String ADD_JOB_ADD_P1 = ChatColor.BLUE + "Add job is successfully! With idJob = ";
    public static String ADD_JOB_ADD_P2 = ChatColor.BLUE + " and Name Job = ";
    public static String HELP_LINE = ChatColor.BLUE + "====================================================================";
    public static String[] HELP_JOB = new String[]{
            HELP_LINE,
            ChatColor.BLUE + "'/job add %jobid% %Job Name% ' for add job. %Job Name% may be null. "+ ChatColor.RED + "Only for OP",
            ChatColor.BLUE + "'/job remove %jobid% ' for remove job."+ ChatColor.RED + "Only for OP",
            ChatColor.BLUE + "'/job reload ' for reload job plugin."+ ChatColor.RED + "Only for OP",
            ChatColor.BLUE + "'/job list' for see all name job.",
            ChatColor.BLUE + "'/job get %Job Name%' for get job id for this job.",
            HELP_LINE
    };
}
