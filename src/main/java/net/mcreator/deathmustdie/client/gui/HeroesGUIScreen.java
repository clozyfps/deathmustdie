package net.mcreator.deathmustdie.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.deathmustdie.world.inventory.HeroesGUIMenu;
import net.mcreator.deathmustdie.network.HeroesGUIButtonMessage;
import net.mcreator.deathmustdie.DeathmustdieMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class HeroesGUIScreen extends AbstractContainerScreen<HeroesGUIMenu> {
	private final static HashMap<String, Object> guistate = HeroesGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_knight;
	Button button_knight1;
	Button button_knight2;
	Button button_knight3;
	Button button_knight4;

	public HeroesGUIScreen(HeroesGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 100;
		this.imageHeight = 140;
	}

	private static final ResourceLocation texture = new ResourceLocation("deathmustdie:textures/screens/heroes_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		button_knight = Button.builder(Component.translatable("gui.deathmustdie.heroes_gui.button_knight"), e -> {
			if (true) {
				DeathmustdieMod.PACKET_HANDLER.sendToServer(new HeroesGUIButtonMessage(0, x, y, z));
				HeroesGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 21, this.topPos + 5, 56, 20).build();
		guistate.put("button:button_knight", button_knight);
		this.addRenderableWidget(button_knight);
		button_knight1 = Button.builder(Component.translatable("gui.deathmustdie.heroes_gui.button_knight1"), e -> {
		}).bounds(this.leftPos + 21, this.topPos + 31, 56, 20).build();
		guistate.put("button:button_knight1", button_knight1);
		this.addRenderableWidget(button_knight1);
		button_knight2 = Button.builder(Component.translatable("gui.deathmustdie.heroes_gui.button_knight2"), e -> {
		}).bounds(this.leftPos + 21, this.topPos + 60, 56, 20).build();
		guistate.put("button:button_knight2", button_knight2);
		this.addRenderableWidget(button_knight2);
		button_knight3 = Button.builder(Component.translatable("gui.deathmustdie.heroes_gui.button_knight3"), e -> {
		}).bounds(this.leftPos + 21, this.topPos + 87, 56, 20).build();
		guistate.put("button:button_knight3", button_knight3);
		this.addRenderableWidget(button_knight3);
		button_knight4 = Button.builder(Component.translatable("gui.deathmustdie.heroes_gui.button_knight4"), e -> {
		}).bounds(this.leftPos + 21, this.topPos + 115, 56, 20).build();
		guistate.put("button:button_knight4", button_knight4);
		this.addRenderableWidget(button_knight4);
	}
}
