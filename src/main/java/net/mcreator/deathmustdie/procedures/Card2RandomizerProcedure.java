package net.mcreator.deathmustdie.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;

public class Card2RandomizerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double card1random = 0;
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).GodSelected).equals("Lightning")) {
			card1random = Mth.nextInt(RandomSource.create(), 1, 3);
			if (card1random == 1) {
				if (!(((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1).equals("Lightning Orbs")
						|| ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card3).equals("Lightning Orbs"))) {
					{
						String _setval = "Lightning Orbs";
						entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Card2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					card1random = Mth.nextInt(RandomSource.create(), 1, 3);
				}
			}
			if (card1random == 2) {
				if (!(((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1).equals("Lightning Strikes")
						|| ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card3).equals("Lightning Strikes"))) {
					{
						String _setval = "Lightning Strikes";
						entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Card2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					card1random = Mth.nextInt(RandomSource.create(), 1, 3);
				}
			}
			if (card1random == 3) {
				if (!(((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1).equals("Lightning Dash")
						|| ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card3).equals("Lightning Dash"))) {
					{
						String _setval = "Lightning Dash";
						entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Card2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					card1random = Mth.nextInt(RandomSource.create(), 1, 3);
				}
			}
		}
		if (((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).GodSelected).equals("Justice")) {
			card1random = Mth.nextInt(RandomSource.create(), 1, 4);
			if (card1random == 1) {
				if (!(((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1).equals("Inner Peace")
						|| ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card3).equals("Inner Peace"))) {
					{
						String _setval = "Inner Peace";
						entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Card2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					card1random = Mth.nextInt(RandomSource.create(), 1, 4);
				}
			}
			if (card1random == 2) {
				if (!(((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1).equals("Reprisal")
						|| ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card3).equals("Reprisal"))) {
					{
						String _setval = "Reprisal";
						entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Card2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					card1random = Mth.nextInt(RandomSource.create(), 1, 4);
				}
			}
			if (card1random == 3) {
				if (!(((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1).equals("Divine Shield")
						|| ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card3).equals("Divine Shield"))) {
					{
						String _setval = "Divine Shield";
						entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Card2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					card1random = Mth.nextInt(RandomSource.create(), 1, 4);
				}
			}
			if (card1random == 4) {
				if (!(((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1).equals("Merciful Strike")
						|| ((entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card3).equals("Merciful Strike"))) {
					{
						String _setval = "Merciful Strike";
						entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Card2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					card1random = Mth.nextInt(RandomSource.create(), 1, 4);
				}
			}
		}
	}
}
