package net.mcreator.deathmustdie.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.deathmustdie.world.inventory.GodCardsMenu;
import net.mcreator.deathmustdie.procedures.Card3DisplayProcedure;
import net.mcreator.deathmustdie.procedures.Card2DisplayProcedure;
import net.mcreator.deathmustdie.procedures.Card1DisplayProcedure;
import net.mcreator.deathmustdie.network.GodCardsButtonMessage;
import net.mcreator.deathmustdie.DeathmustdieMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class GodCardsScreen extends AbstractContainerScreen<GodCardsMenu> {
	private final static HashMap<String, Object> guistate = GodCardsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_invisbutton;
	ImageButton imagebutton_invisbutton1;
	ImageButton imagebutton_invisbutton2;

	public GodCardsScreen(GodCardsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 0;
		this.imageHeight = 15;
	}

	private static final ResourceLocation texture = new ResourceLocation("deathmustdie:textures/screens/god_cards.png");

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
		guiGraphics.drawString(this.font,

				Card1DisplayProcedure.execute(entity), -181, -6, -1, false);
		guiGraphics.drawString(this.font,

				Card2DisplayProcedure.execute(entity), -37, -6, -1, false);
		guiGraphics.drawString(this.font,

				Card3DisplayProcedure.execute(entity), 107, -6, -1, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		imagebutton_invisbutton = new ImageButton(this.leftPos + -190, this.topPos + -6, 80, 16, 0, 0, 16, new ResourceLocation("deathmustdie:textures/screens/atlas/imagebutton_invisbutton.png"), 80, 32, e -> {
			if (true) {
				DeathmustdieMod.PACKET_HANDLER.sendToServer(new GodCardsButtonMessage(0, x, y, z));
				GodCardsButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_invisbutton", imagebutton_invisbutton);
		this.addRenderableWidget(imagebutton_invisbutton);
		imagebutton_invisbutton1 = new ImageButton(this.leftPos + -46, this.topPos + -6, 80, 16, 0, 0, 16, new ResourceLocation("deathmustdie:textures/screens/atlas/imagebutton_invisbutton1.png"), 80, 32, e -> {
			if (true) {
				DeathmustdieMod.PACKET_HANDLER.sendToServer(new GodCardsButtonMessage(1, x, y, z));
				GodCardsButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:imagebutton_invisbutton1", imagebutton_invisbutton1);
		this.addRenderableWidget(imagebutton_invisbutton1);
		imagebutton_invisbutton2 = new ImageButton(this.leftPos + 98, this.topPos + -6, 80, 16, 0, 0, 16, new ResourceLocation("deathmustdie:textures/screens/atlas/imagebutton_invisbutton2.png"), 80, 32, e -> {
			if (true) {
				DeathmustdieMod.PACKET_HANDLER.sendToServer(new GodCardsButtonMessage(2, x, y, z));
				GodCardsButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:imagebutton_invisbutton2", imagebutton_invisbutton2);
		this.addRenderableWidget(imagebutton_invisbutton2);
	}
}
