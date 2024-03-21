package net.mcreator.deathmustdie.procedures;

import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerXpEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.MenuProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.deathmustdie.world.inventory.GodCardsMenu;
import net.mcreator.deathmustdie.network.DeathmustdieModVariables;

import javax.annotation.Nullable;

import io.netty.buffer.Unpooled;

@Mod.EventBusSubscriber
public class LevelUpProcedure {
	@SubscribeEvent
	public static void onPlayerXPLevelChange(PlayerXpEvent.LevelChange event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double randomgod = 0;
		{
			double _setval = (entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).IntervalLevel + 1;
			entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.IntervalLevel = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).IntervalLevel == 2) {
			{
				double _setval = 0;
				entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.IntervalLevel = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			randomgod = Mth.nextInt(RandomSource.create(), 1, 2);
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 999999,
						(int) ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0) + 1), false, false));
			if (randomgod == 1) {
				{
					String _setval = "Lightning";
					entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.GodSelected = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (randomgod == 2) {
				{
					String _setval = "Justice";
					entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.GodSelected = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			Card1RandomizerProcedure.execute(entity);
			Card2RandomizerProcedure.execute(entity);
			Card3RandomizerProcedure.execute(entity);
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("GodCards");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new GodCardsMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
