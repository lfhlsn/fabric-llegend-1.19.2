package net.lfhlsn.llegend.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.lfhlsn.llegend.ModInitializer;
import net.lfhlsn.llegend.networking.packet.SwordSkillC2SPacket_1;
import net.lfhlsn.llegend.networking.packet.SwordSkillC2SPacket_2;
import net.lfhlsn.llegend.networking.packet.SwordSkillC2SPacket_3;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier SWORD_SKILL_ID_1 = new Identifier(ModInitializer.MOD_ID,"sword_1");
    public static final Identifier SWORD_SKILL_ID_2 = new Identifier(ModInitializer.MOD_ID,"sword_2");
    public static final Identifier SWORD_SKILL_ID_3 = new Identifier(ModInitializer.MOD_ID,"sword_3");;

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(SWORD_SKILL_ID_1, SwordSkillC2SPacket_1::receive);
        ServerPlayNetworking.registerGlobalReceiver(SWORD_SKILL_ID_2, SwordSkillC2SPacket_2::receive);
        ServerPlayNetworking.registerGlobalReceiver(SWORD_SKILL_ID_3, SwordSkillC2SPacket_3::receive);
    }

    public static void registerS2CPackets(){

    }
}
