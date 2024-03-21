package net.mcreator.deathmustdie.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DashesCooldownProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Dashes < 2) {
			entity.getPersistentData().putDouble("dashestick", (entity.getPersistentData().getDouble("dashestick") + 1));
			if (entity.getPersistentData().getDouble("dashestick") >= 40) {
				entity.getPersistentData().putDouble("dashestick", 0);
				{
					double _setval = (entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Dashes + 1;
					entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Dashes = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.experience_orb.pickup")), SoundSource.NEUTRAL, (float) 0.3, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.experience_orb.pickup")), SoundSource.NEUTRAL, (float) 0.3, 1, false);
					}
				}
			}
			{
				double _setval = (entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Dashes + 0;
				entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Dashes = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
