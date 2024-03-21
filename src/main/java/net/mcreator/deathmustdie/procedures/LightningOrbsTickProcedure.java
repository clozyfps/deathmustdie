package net.mcreator.deathmustdie.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;
import net.mcreator.deathmustdie.init.DeathmustdieModEntities;
import net.mcreator.deathmustdie.entity.LightningOrbEntity;

import java.util.Comparator;

public class LightningOrbsTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double randomx = 0;
		double randomz = 0;
		double orbtimer = 0;
		DeathmustdieModVariables.MapVariables.get(world).LightningOrbsTimer = DeathmustdieModVariables.MapVariables.get(world).LightningOrbsTimer + 1;
		DeathmustdieModVariables.MapVariables.get(world).syncData(world);
		if (DeathmustdieModVariables.MapVariables.get(world).LightningOrbsTimer >= 20) {
			DeathmustdieModVariables.MapVariables.get(world).LightningOrbsTimer = 0;
			DeathmustdieModVariables.MapVariables.get(world).syncData(world);
			randomx = entity.getX() + Mth.nextInt(RandomSource.create(), -5, 5);
			randomz = entity.getZ() + Mth.nextInt(RandomSource.create(), -5, 5);
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = DeathmustdieModEntities.LIGHTNING_ORB.get().spawn(_level, BlockPos.containing(randomx, y, DeathmustdieModVariables.MapVariables.get(world).RandomZ), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
			if (!world.getEntitiesOfClass(LightningOrbEntity.class, AABB.ofSize(new Vec3(x, y, z), 60, 60, 60), e -> true).isEmpty()) {
				if (((Entity) world.getEntitiesOfClass(LightningOrbEntity.class, AABB.ofSize(new Vec3(x, y, z), 60, 60, 60), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof TamableAnimal _toTame && entity instanceof Player _owner)
					_toTame.tame(_owner);
			}
		}
	}
}
