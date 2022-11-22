package net.lfhlsn.llegend.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_3;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SwordSkillC2SPacket_3 {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responsePacketSender) {
        ItemStack itemStack=player.getMainHandStack();
        Item item=itemStack.getItem();
        if(item instanceof SwordWithSkill_3)
        {
            ((SwordWithSkill_3)item).skill_3(player.world,player,itemStack);
        }
    }
}
