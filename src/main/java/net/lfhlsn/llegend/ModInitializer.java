package net.lfhlsn.llegend;

import net.lfhlsn.llegend.item.ModItems;
import net.lfhlsn.llegend.networking.ModMessages;
import net.lfhlsn.llegend.util.ModLootTableModifiers;

public class ModInitializer implements net.fabricmc.api.ModInitializer {
	public static final String MOD_ID="llegend";

	@Override
	public void onInitialize() {
		ModItems.register();
		ModLootTableModifiers.modifyLootTables();
		ModMessages.registerC2SPackets();
	}
}
