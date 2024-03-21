package net.mcreator.deathmustdie.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.deathmustdie.network.DeathmustdieModVariables;

public class Card1DisplayProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7l" + (entity.getCapability(DeathmustdieModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DeathmustdieModVariables.PlayerVariables())).Card1;
	}
}
