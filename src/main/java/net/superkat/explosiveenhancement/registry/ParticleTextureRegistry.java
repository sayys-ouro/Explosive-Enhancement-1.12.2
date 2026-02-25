package net.superkat.explosiveenhancement.registry;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.superkat.explosiveenhancement.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
public class ParticleTextureRegistry {
    public static TextureAtlasSprite[] BLASTWAVE_SPRITES;
    public static TextureAtlasSprite BUBBLE_SPRITE;
    public static TextureAtlasSprite BLANK_SPRITE;
    public static TextureAtlasSprite[] FIREBALL_SPRITES;
    public static TextureAtlasSprite[] SHOCKWAVE_SPRITES;
    public static TextureAtlasSprite[] SMOKE_SPRITES;
    public static TextureAtlasSprite[] SPARKS_SPRITES;
    public static TextureAtlasSprite[] UNDERWATER_BLASTWAVE_SPRITES;

    private static TextureAtlasSprite[] registerSprites(TextureMap map, String baseName, int frameCount) {
        TextureAtlasSprite[] sprites = new TextureAtlasSprite[frameCount];

        for (int i = 0; i < frameCount; i++) {
            ResourceLocation location = new ResourceLocation(Reference.MOD_ID, "particle/" + baseName + (i + 1));

            sprites[i] = map.registerSprite(location);
        }

        return sprites;
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        TextureMap map = event.getMap();

        BLANK_SPRITE = map.registerSprite(new ResourceLocation(Reference.MOD_ID, "particle/blank"));
        BUBBLE_SPRITE = map.registerSprite(new ResourceLocation(Reference.MOD_ID, "particle/bubble"));

        BLASTWAVE_SPRITES = registerSprites(map, "blastwave", 21);
        FIREBALL_SPRITES = registerSprites(map, "fireball", 9);
        SHOCKWAVE_SPRITES = registerSprites(map, "shockwave", 16);
        SMOKE_SPRITES = registerSprites(map, "smoke", 12);
        SPARKS_SPRITES = registerSprites(map, "spark", 4);
        UNDERWATER_BLASTWAVE_SPRITES = registerSprites(map, "underwaterblastwave", 21);
    }
}
