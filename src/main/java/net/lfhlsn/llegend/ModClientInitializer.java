package net.lfhlsn.llegend;

import net.fabricmc.api.ClientModInitializer;
import net.lfhlsn.llegend.event.KeyInputHandler;
import net.lfhlsn.llegend.networking.ModMessages;

public class ModClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ModMessages.registerS2CPackets();
    }
}
