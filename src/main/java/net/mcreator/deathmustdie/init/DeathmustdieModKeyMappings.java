
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.deathmustdie.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.deathmustdie.network.TravelMessage;
import net.mcreator.deathmustdie.network.DashMessage;
import net.mcreator.deathmustdie.DeathmustdieMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class DeathmustdieModKeyMappings {
	public static final KeyMapping DASH = new KeyMapping("key.deathmustdie.dash", GLFW.GLFW_KEY_C, "key.categories.dmd") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				DeathmustdieMod.PACKET_HANDLER.sendToServer(new DashMessage(0, 0));
				DashMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping TRAVEL = new KeyMapping("key.deathmustdie.travel", GLFW.GLFW_KEY_C, "key.categories.dmd") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				DeathmustdieMod.PACKET_HANDLER.sendToServer(new TravelMessage(0, 0));
				TravelMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(DASH);
		event.register(TRAVEL);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				DASH.consumeClick();
				TRAVEL.consumeClick();
			}
		}
	}
}
