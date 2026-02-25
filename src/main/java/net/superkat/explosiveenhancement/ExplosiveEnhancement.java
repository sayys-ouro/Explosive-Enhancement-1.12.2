package net.superkat.explosiveenhancement;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;

@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.VERSION
)
public class ExplosiveEnhancement {
    public static Logger logger;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     * Take a look at how many FMLStateEvents you can listen to via
     * the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(@NonNull FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }
}
