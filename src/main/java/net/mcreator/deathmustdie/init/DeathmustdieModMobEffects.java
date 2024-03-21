
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.deathmustdie.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.deathmustdie.potion.SmitedMobEffect;
import net.mcreator.deathmustdie.potion.DashEffectMobEffect;
import net.mcreator.deathmustdie.potion.DashCooldownMobEffect;
import net.mcreator.deathmustdie.DeathmustdieMod;

public class DeathmustdieModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DeathmustdieMod.MODID);
	public static final RegistryObject<MobEffect> DASH_COOLDOWN = REGISTRY.register("dash_cooldown", () -> new DashCooldownMobEffect());
	public static final RegistryObject<MobEffect> DASH_EFFECT = REGISTRY.register("dash_effect", () -> new DashEffectMobEffect());
	public static final RegistryObject<MobEffect> SMITED = REGISTRY.register("smited", () -> new SmitedMobEffect());
}
