package net.mcreator.deathmustdie.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;

import java.util.ArrayList;

public class DeathRealmPlayerEntersDimensionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (DeathmustdieModVariables.MapVariables.get(world).Wave == 0) {
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.spawn")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.spawn")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
			DeathmustdieModVariables.MapVariables.get(world).WaveTimer = 2500;
			DeathmustdieModVariables.MapVariables.get(world).syncData(world);
			DeathmustdieModVariables.MapVariables.get(world).Wave = 1;
			DeathmustdieModVariables.MapVariables.get(world).syncData(world);
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
			world.getLevelData().getGameRules().getRule(GameRules.RULE_MOBGRIEFING).set(false, world.getServer());
			{
				Entity _ent = entity;
				_ent.teleportTo(x, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z)), z);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(x, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z)), z, _ent.getYRot(), _ent.getXRot());
			}
		}
	}
}
