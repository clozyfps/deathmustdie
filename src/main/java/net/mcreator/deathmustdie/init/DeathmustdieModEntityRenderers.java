
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.deathmustdie.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.deathmustdie.client.renderer.LightningOrbRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DeathmustdieModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DeathmustdieModEntities.LIGHTNING_ORB.get(), LightningOrbRenderer::new);
	}
}
