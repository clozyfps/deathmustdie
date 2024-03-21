
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.deathmustdie.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.deathmustdie.client.gui.HeroesGUIScreen;
import net.mcreator.deathmustdie.client.gui.GodCardsScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DeathmustdieModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(DeathmustdieModMenus.HEROES_GUI.get(), HeroesGUIScreen::new);
			MenuScreens.register(DeathmustdieModMenus.GOD_CARDS.get(), GodCardsScreen::new);
		});
	}
}
