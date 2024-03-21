package net.mcreator.deathmustdie.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.deathmustdie.DeathmustdieMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeathmustdieModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		DeathmustdieMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
		DeathmustdieMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.IntervalLevel = original.IntervalLevel;
			clone.Dashes = original.Dashes;
			if (!event.isWasDeath()) {
				clone.Card1 = original.Card1;
				clone.Card2 = original.Card2;
				clone.Card3 = original.Card3;
				clone.GodSelected = original.GodSelected;
				clone.MoveList = original.MoveList;
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					DeathmustdieMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					DeathmustdieMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					DeathmustdieMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "deathmustdie_worldvars";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				DeathmustdieMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "deathmustdie_mapvars";
		public double Wave = 0;
		public double EnemiesLeft = 0;
		public double MaxEnemy = 0;
		public double RandomX = 0;
		public double RandomZ = 0;
		public double RandomY = 0;
		public double EnemiesDeath = 0.0;
		public double WaveTimer = 0;
		public double ZombieTimer = 0;
		public double WitherSkeletonTimer = 0;
		public double BlazeTimer = 0;
		public double LightningOrbsTimer = 0;
		public double LightningStrikesTimer = 0;
		public double SkeletonTimer = 0;
		public double SlimeTimer = 0;
		public double VindicatorTimer = 0;
		public double EndermanTimer = 0;
		public double RavagerTimer = 0;
		public double SlimeSpawns = 25.0;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			Wave = nbt.getDouble("Wave");
			EnemiesLeft = nbt.getDouble("EnemiesLeft");
			MaxEnemy = nbt.getDouble("MaxEnemy");
			RandomX = nbt.getDouble("RandomX");
			RandomZ = nbt.getDouble("RandomZ");
			RandomY = nbt.getDouble("RandomY");
			EnemiesDeath = nbt.getDouble("EnemiesDeath");
			WaveTimer = nbt.getDouble("WaveTimer");
			ZombieTimer = nbt.getDouble("ZombieTimer");
			WitherSkeletonTimer = nbt.getDouble("WitherSkeletonTimer");
			BlazeTimer = nbt.getDouble("BlazeTimer");
			LightningOrbsTimer = nbt.getDouble("LightningOrbsTimer");
			LightningStrikesTimer = nbt.getDouble("LightningStrikesTimer");
			SkeletonTimer = nbt.getDouble("SkeletonTimer");
			SlimeTimer = nbt.getDouble("SlimeTimer");
			VindicatorTimer = nbt.getDouble("VindicatorTimer");
			EndermanTimer = nbt.getDouble("EndermanTimer");
			RavagerTimer = nbt.getDouble("RavagerTimer");
			SlimeSpawns = nbt.getDouble("SlimeSpawns");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("Wave", Wave);
			nbt.putDouble("EnemiesLeft", EnemiesLeft);
			nbt.putDouble("MaxEnemy", MaxEnemy);
			nbt.putDouble("RandomX", RandomX);
			nbt.putDouble("RandomZ", RandomZ);
			nbt.putDouble("RandomY", RandomY);
			nbt.putDouble("EnemiesDeath", EnemiesDeath);
			nbt.putDouble("WaveTimer", WaveTimer);
			nbt.putDouble("ZombieTimer", ZombieTimer);
			nbt.putDouble("WitherSkeletonTimer", WitherSkeletonTimer);
			nbt.putDouble("BlazeTimer", BlazeTimer);
			nbt.putDouble("LightningOrbsTimer", LightningOrbsTimer);
			nbt.putDouble("LightningStrikesTimer", LightningStrikesTimer);
			nbt.putDouble("SkeletonTimer", SkeletonTimer);
			nbt.putDouble("SlimeTimer", SlimeTimer);
			nbt.putDouble("VindicatorTimer", VindicatorTimer);
			nbt.putDouble("EndermanTimer", EndermanTimer);
			nbt.putDouble("RavagerTimer", RavagerTimer);
			nbt.putDouble("SlimeSpawns", SlimeSpawns);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				DeathmustdieMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("deathmustdie", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public String Card1 = "";
		public String Card2 = "";
		public String Card3 = "";
		public String GodSelected = "";
		public String MoveList = "";
		public double IntervalLevel = 0;
		public double Dashes = 2.0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				DeathmustdieMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("Card1", Card1);
			nbt.putString("Card2", Card2);
			nbt.putString("Card3", Card3);
			nbt.putString("GodSelected", GodSelected);
			nbt.putString("MoveList", MoveList);
			nbt.putDouble("IntervalLevel", IntervalLevel);
			nbt.putDouble("Dashes", Dashes);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			Card1 = nbt.getString("Card1");
			Card2 = nbt.getString("Card2");
			Card3 = nbt.getString("Card3");
			GodSelected = nbt.getString("GodSelected");
			MoveList = nbt.getString("MoveList");
			IntervalLevel = nbt.getDouble("IntervalLevel");
			Dashes = nbt.getDouble("Dashes");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.Card1 = message.data.Card1;
					variables.Card2 = message.data.Card2;
					variables.Card3 = message.data.Card3;
					variables.GodSelected = message.data.GodSelected;
					variables.MoveList = message.data.MoveList;
					variables.IntervalLevel = message.data.IntervalLevel;
					variables.Dashes = message.data.Dashes;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
