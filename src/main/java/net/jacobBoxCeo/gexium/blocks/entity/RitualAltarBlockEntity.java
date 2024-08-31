package net.jacobBoxCeo.gexium.blocks.entity;

import net.jacobBoxCeo.gexium.init.ModItems;
import net.jacobBoxCeo.gexium.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RitualAltarBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private LazyOptional<IItemHandler> lazyItemHandler;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    public RitualAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RITUAL_ALTAR_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch(i) {
                    case 0 -> RitualAltarBlockEntity.this.progress;
                    case 1 -> RitualAltarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch(i) {
                    case 0 -> RitualAltarBlockEntity.this.progress = i1;
                    case 1 -> RitualAltarBlockEntity.this.maxProgress = i1;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }



    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(Util.makeDescriptionId("block", new ResourceLocation("ritual_altar")));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("invntory", itemHandler.serializeNBT());
        pTag.putInt("ritual_altar.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("ritual_altar.progress");
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            progress++;
            setChanged(level, blockPos, blockState);
            if(progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.GEXIUM.get(), 1);
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == Items.NETHERITE_INGOT;
        ItemStack result = new ItemStack(ModItems.GEXIUM.get());
        return hasCraftingItem && canInsertIntoOutputSlot(result);
    }

    private boolean canInsertIntoOutputSlot(ItemStack result) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(result.getItem()) && this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount() <= result.getMaxStackSize();
    }
}
