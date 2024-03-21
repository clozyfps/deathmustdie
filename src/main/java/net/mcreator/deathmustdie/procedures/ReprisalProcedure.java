package net.mcreator.deathmustdie.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.registries.Registries;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;
import net.mcreator.deathmustdie.init.DeathmustdieModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ReprisalProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Reprisal" + "LVL4")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 5)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), entity), 20);
				if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(DeathmustdieModMobEffects.SMITED.get(), 100, 0, false, false));
			}
		}
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Reprisal" + "LVL3")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 8)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), entity), 15);
				if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(DeathmustdieModMobEffects.SMITED.get(), 100, 0, false, false));
			}
		}
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Reprisal" + "LVL2")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 10)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), entity), 10);
				if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(DeathmustdieModMobEffects.SMITED.get(), 100, 0, false, false));
			}
		}
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).MoveList).contains("Reprisal" + "LVL1")) {
			entity.getPersistentData().putDouble("reprisalrandom", (Mth.nextInt(RandomSource.create(), 1, 15)));
			if (entity.getPersistentData().getDouble("reprisalrandom") == 2) {
				sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), entity), 5);
				if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(DeathmustdieModMobEffects.SMITED.get(), 100, 0, false, false));
			}
		}
	}
}
