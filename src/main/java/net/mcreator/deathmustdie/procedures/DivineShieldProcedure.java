package net.mcreator.deathmustdie.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DivineShieldProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Divine Shield" + "LVL4")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 40)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Divine Shield" + "LVL3")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 50)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Divine Shield" + "LVL2")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 70)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Divine Shield" + "LVL1")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 100)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
	}
}
