package net.mcreator.deathmustdie.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;

import javax.annotation.Nullable;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class DeathTickProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		double SkeletonMax = 0;
		double SkeletonTimer = 0;
		double WitherSkeletonMax = 0;
		double WitherSkeletonTimer = 0;
		double BlazeMax = 0;
		double BlazeTimer = 0;
		double WaveTimer = 0;
		double RealSkeletonMax = 0;
		double SlimeMax = 0;
		if (DeathmustdieModVariables.MapVariables.get(world).Wave > 0) {
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				if ((entityiterator.level().dimension()) == (ResourceKey.create(Registries.DIMENSION, new ResourceLocation("deathmustdie:death_realm")))) {
					if (DeathmustdieModVariables.MapVariables.get(world).Wave < 20) {
						if (DeathmustdieModVariables.MapVariables.get(world).WaveTimer <= 0) {
							DeathmustdieModVariables.MapVariables.get(world).Wave = DeathmustdieModVariables.MapVariables.get(world).Wave + 1;
							DeathmustdieModVariables.MapVariables.get(world).syncData(world);
							DeathmustdieModVariables.MapVariables.get(world).WaveTimer = 800;
							DeathmustdieModVariables.MapVariables.get(world).syncData(world);
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.spawn")),
											SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.spawn")), SoundSource.NEUTRAL, 1, 1,
											false);
								}
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.cave")), SoundSource.NEUTRAL, 1,
											1);
								} else {
									_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.cave")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							if (entityiterator instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("Wave " + new java.text.DecimalFormat("#").format(DeathmustdieModVariables.MapVariables.get(world).Wave))), true);
							DeathmustdieModVariables.MapVariables.get(world).ZombieTimer = 40;
							DeathmustdieModVariables.MapVariables.get(world).syncData(world);
							DeathmustdieModVariables.MapVariables.get(world).WitherSkeletonTimer = 200;
							DeathmustdieModVariables.MapVariables.get(world).syncData(world);
							DeathmustdieModVariables.MapVariables.get(world).BlazeTimer = 350;
							DeathmustdieModVariables.MapVariables.get(world).syncData(world);
							DeathmustdieModVariables.MapVariables.get(world).SkeletonTimer = 30;
							DeathmustdieModVariables.MapVariables.get(world).syncData(world);
							DeathmustdieModVariables.MapVariables.get(world).SlimeTimer = 10;
							DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						}
						DeathmustdieModVariables.MapVariables.get(world).RandomZ = entityiterator.getZ() + Mth.nextInt(RandomSource.create(), -9, 9);
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).RandomX = entityiterator.getX() + Mth.nextInt(RandomSource.create(), -9, 9);
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).WaveTimer = DeathmustdieModVariables.MapVariables.get(world).WaveTimer - 1;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).WitherSkeletonTimer = DeathmustdieModVariables.MapVariables.get(world).WitherSkeletonTimer - 1;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).BlazeTimer = DeathmustdieModVariables.MapVariables.get(world).BlazeTimer - 1;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).ZombieTimer = DeathmustdieModVariables.MapVariables.get(world).ZombieTimer - 1;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).SkeletonTimer = DeathmustdieModVariables.MapVariables.get(world).SkeletonTimer - 1;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).SlimeTimer = DeathmustdieModVariables.MapVariables.get(world).SlimeTimer - 1;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
					}
				}
			}
			if (DeathmustdieModVariables.MapVariables.get(world).ZombieTimer <= 0) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = EntityType.ZOMBIE.spawn(_level,
							BlockPos.containing(DeathmustdieModVariables.MapVariables.get(world).RandomX,
									world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) DeathmustdieModVariables.MapVariables.get(world).RandomX, (int) DeathmustdieModVariables.MapVariables.get(world).RandomZ),
									DeathmustdieModVariables.MapVariables.get(world).RandomZ),
							MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
				SkeletonMax = 60 - DeathmustdieModVariables.MapVariables.get(world).Wave * 2;
				DeathmustdieModVariables.MapVariables.get(world).ZombieTimer = SkeletonMax;
				DeathmustdieModVariables.MapVariables.get(world).syncData(world);
			}
			if (DeathmustdieModVariables.MapVariables.get(world).Wave >= 2) {
				if (DeathmustdieModVariables.MapVariables.get(world).SkeletonTimer <= 0) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = EntityType.SKELETON.spawn(_level,
								BlockPos.containing(DeathmustdieModVariables.MapVariables.get(world).RandomX,
										world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) DeathmustdieModVariables.MapVariables.get(world).RandomX, (int) DeathmustdieModVariables.MapVariables.get(world).RandomZ),
										DeathmustdieModVariables.MapVariables.get(world).RandomZ),
								MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
						}
					}
					RealSkeletonMax = 70 - DeathmustdieModVariables.MapVariables.get(world).Wave * 2;
					DeathmustdieModVariables.MapVariables.get(world).SkeletonTimer = RealSkeletonMax;
					DeathmustdieModVariables.MapVariables.get(world).syncData(world);
				}
			}
			if (DeathmustdieModVariables.MapVariables.get(world).Wave >= 3) {
				if (DeathmustdieModVariables.MapVariables.get(world).WitherSkeletonTimer <= 0) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = EntityType.WITHER_SKELETON.spawn(_level,
								BlockPos.containing(DeathmustdieModVariables.MapVariables.get(world).RandomX,
										world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) DeathmustdieModVariables.MapVariables.get(world).RandomX, (int) DeathmustdieModVariables.MapVariables.get(world).RandomZ),
										DeathmustdieModVariables.MapVariables.get(world).RandomZ),
								MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
						}
					}
					WitherSkeletonMax = 70 - DeathmustdieModVariables.MapVariables.get(world).Wave * 2;
					DeathmustdieModVariables.MapVariables.get(world).WitherSkeletonTimer = WitherSkeletonMax;
					DeathmustdieModVariables.MapVariables.get(world).syncData(world);
				}
			}
			if (DeathmustdieModVariables.MapVariables.get(world).Wave == 6) {
				if (DeathmustdieModVariables.MapVariables.get(world).SlimeTimer <= 0) {
					if (DeathmustdieModVariables.MapVariables.get(world).SlimeSpawns > 0) {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = EntityType.SLIME.spawn(_level,
									BlockPos.containing(DeathmustdieModVariables.MapVariables.get(world).RandomX,
											world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) DeathmustdieModVariables.MapVariables.get(world).RandomX, (int) DeathmustdieModVariables.MapVariables.get(world).RandomZ),
											DeathmustdieModVariables.MapVariables.get(world).RandomZ),
									MobSpawnType.MOB_SUMMONED);
							if (entityToSpawn != null) {
								entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
							}
						}
						for (Entity entityiterator : new ArrayList<>(world.players())) {
							if ((entityiterator.level().dimension()) == (ResourceKey.create(Registries.DIMENSION, new ResourceLocation("deathmustdie:death_realm")))) {
								if (entityiterator instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal("\u00A74\u00A7lWAVE 6 EVENT: SLIME RAID"), true);
							}
						}
						SlimeMax = 10 - DeathmustdieModVariables.MapVariables.get(world).Wave * 2;
						DeathmustdieModVariables.MapVariables.get(world).SlimeTimer = SlimeMax;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
						DeathmustdieModVariables.MapVariables.get(world).SlimeSpawns = DeathmustdieModVariables.MapVariables.get(world).SlimeSpawns - 1;
						DeathmustdieModVariables.MapVariables.get(world).syncData(world);
					}
				}
			}
			if (DeathmustdieModVariables.MapVariables.get(world).Wave >= 5) {
				if (DeathmustdieModVariables.MapVariables.get(world).BlazeTimer <= 0) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = EntityType.BLAZE.spawn(_level,
								BlockPos.containing(DeathmustdieModVariables.MapVariables.get(world).RandomX,
										world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) DeathmustdieModVariables.MapVariables.get(world).RandomX, (int) DeathmustdieModVariables.MapVariables.get(world).RandomZ),
										DeathmustdieModVariables.MapVariables.get(world).RandomZ),
								MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
						}
					}
					BlazeMax = 90 - DeathmustdieModVariables.MapVariables.get(world).Wave * 2;
					DeathmustdieModVariables.MapVariables.get(world).BlazeTimer = BlazeMax;
					DeathmustdieModVariables.MapVariables.get(world).syncData(world);
				}
			}
		}
	}
}
