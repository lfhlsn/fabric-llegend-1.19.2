package net.lfhlsn.llegend.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.lfhlsn.llegend.networking.ModMessages;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_SWORD_SKILL ="key.category.llegend.sword_skill";
    public static final String KEY_SWORD_SKILL_1 ="key.llegend.sword_skill_1";
    public static final String KEY_SWORD_SKILL_2 ="key.llegend.sword_skill_2";
    public static final String KEY_SWORD_SKILL_3 ="key.llegend.sword_skill_3";
    public static KeyBinding swordKey_1;
    public static KeyBinding swordKey_2;
    public static KeyBinding swordKey_3;
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(swordKey_1.wasPressed()){
                ClientPlayNetworking.send(ModMessages.SWORD_SKILL_ID_1, PacketByteBufs.create());
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(swordKey_2.wasPressed()){
                ClientPlayNetworking.send(ModMessages.SWORD_SKILL_ID_2, PacketByteBufs.create());
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(swordKey_3.wasPressed()){
                ClientPlayNetworking.send(ModMessages.SWORD_SKILL_ID_3, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        swordKey_1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SWORD_SKILL_1,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KEY_CATEGORY_SWORD_SKILL
        ));
        swordKey_2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SWORD_SKILL_2,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                KEY_CATEGORY_SWORD_SKILL
        ));
        swordKey_3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SWORD_SKILL_3,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                KEY_CATEGORY_SWORD_SKILL
        ));
        registerKeyInputs();
    }
}
