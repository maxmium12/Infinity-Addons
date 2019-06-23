package com.maxmium.infiniaddons.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


public class ConfigLoader {
    private static Configuration config;
    private static Logger logger;
    public ConfigLoader(FMLPreInitializationEvent evt){
        logger=evt.getModLog();
        config=new Configuration(evt.getSuggestedConfigurationFile());
        config.load();
        load();
    }
    public static void load(){
        logger.info("Started loading config. ");
        String comment;
        comment="Is on the digger zombie.";
        Boolean diggerzombie=config.get(Configuration.CATEGORY_GENERAL,"boolean","false").getBoolean();
        config.save();
        logger.info("Finished loading config. ");
    }
    public static Logger logger()
    {
        return logger;
    }
}
