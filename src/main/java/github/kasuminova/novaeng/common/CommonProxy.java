package github.kasuminova.novaeng.common;

import github.kasuminova.novaeng.NovaEngineeringCore;
import github.kasuminova.novaeng.common.adapter.RecipeAdapterExtended;
import github.kasuminova.novaeng.common.container.ContainerHyperNetTerminal;
import github.kasuminova.novaeng.common.handler.HyperNetEventHandler;
import github.kasuminova.novaeng.common.integration.IntegrationCRT;
import github.kasuminova.novaeng.common.registry.RegistryBlocks;
import github.kasuminova.novaeng.common.registry.RegistryItems;
import github.kasuminova.novaeng.common.tile.TileHyperNetTerminal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

@SuppressWarnings("MethodMayBeStatic")
public class CommonProxy implements IGuiHandler {

    public CommonProxy() {
        MinecraftForge.EVENT_BUS.register(new RegistryBlocks());
        MinecraftForge.EVENT_BUS.register(new RegistryItems());
    }

    public void preInit() {
        NetworkRegistry.INSTANCE.registerGuiHandler(NovaEngineeringCore.MOD_ID, this);

        MinecraftForge.EVENT_BUS.register(new IntegrationCRT());
        MinecraftForge.EVENT_BUS.register(new HyperNetEventHandler());
    }

    public void init() {
        RecipeAdapterExtended.registerAdapter();
    }

    public void postInit() {

    }

    @Nullable
    @Override
    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        GuiType type = GuiType.values()[MathHelper.clamp(ID, 0, GuiType.values().length - 1)];
        Class<? extends TileEntity> required = type.requiredTileEntity;
        TileEntity present = null;
        if (required != null) {
            TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
            if (te != null && required.isAssignableFrom(te.getClass())) {
                present = te;
            } else {
                return null;
            }
        }

        switch (type) {
            case HYPERNET_TERMINAL:
                return new ContainerHyperNetTerminal((TileHyperNetTerminal) present, player);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        return null;
    }

    public enum GuiType {

        HYPERNET_TERMINAL(TileHyperNetTerminal.class),
        ;

        public final Class<? extends TileEntity> requiredTileEntity;

        GuiType(@Nullable Class<? extends TileEntity> requiredTileEntity) {
            this.requiredTileEntity = requiredTileEntity;
        }
    }
}
