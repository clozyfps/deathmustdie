package net.mcreator.deathmustdie.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.deathmustdie.DeathmustdieMod;

public class LightningOrbOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		DeathmustdieMod.queueServerWork(25, () -> {
			if (!entity.level().isClientSide())
				entity.discard();
		});
	}
}
