
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.deathmustdie.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.deathmustdie.world.inventory.HeroesGUIMenu;
import net.mcreator.deathmustdie.world.inventory.GodCardsMenu;
import net.mcreator.deathmustdie.DeathmustdieMod;

public class DeathmustdieModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, DeathmustdieMod.MODID);
	public static final RegistryObject<MenuType<HeroesGUIMenu>> HEROES_GUI = REGISTRY.register("heroes_gui", () -> IForgeMenuType.create(HeroesGUIMenu::new));
	public static final RegistryObject<MenuType<GodCardsMenu>> GOD_CARDS = REGISTRY.register("god_cards", () -> IForgeMenuType.create(GodCardsMenu::new));
}
