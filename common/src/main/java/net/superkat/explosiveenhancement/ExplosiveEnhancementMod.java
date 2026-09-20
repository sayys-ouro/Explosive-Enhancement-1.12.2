package net.superkat.explosiveenhancement;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
    modid=Reference.MOD_ID,
    name=Reference.MOD_NAME,
    version=Reference.VERSION,
    clientSideOnly=true,
    acceptableRemoteVersions="*"
)
public class ExplosiveEnhancementMod {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);
}
