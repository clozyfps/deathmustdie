
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.deathmustdie.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.deathmustdie.DeathmustdieMod;

public class DeathmustdieModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DeathmustdieMod.MODID);
	public static final RegistryObject<SoundEvent> BELL = REGISTRY.register("bell", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("deathmustdie", "bell")));
	public static final RegistryObject<SoundEvent> KAMINARI = REGISTRY.register("kaminari", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("deathmustdie", "kaminari")));
}
