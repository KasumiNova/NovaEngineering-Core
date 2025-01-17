package github.kasuminova.novaeng.common.block;

import github.kasuminova.novaeng.NovaEngineeringCore;
import github.kasuminova.novaeng.common.CommonProxy;
import github.kasuminova.novaeng.common.tile.TileHyperNetTerminal;
import hellfirepvp.modularmachinery.common.block.BlockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockHyperNetTerminal extends BlockController {
    public static final BlockHyperNetTerminal INSTANCE = new BlockHyperNetTerminal();

    private BlockHyperNetTerminal() {
        setRegistryName(new ResourceLocation(NovaEngineeringCore.MOD_ID, "hypernet_terminal_controller"));
        setTranslationKey(NovaEngineeringCore.MOD_ID + '.' + "hypernet_terminal_controller");
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileHyperNetTerminal();
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileHyperNetTerminal();
    }

    @Override
    public boolean onBlockActivated(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileHyperNetTerminal) {
                playerIn.openGui(NovaEngineeringCore.MOD_ID, CommonProxy.GuiType.HYPERNET_TERMINAL.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public String getLocalizedName() {
        return I18n.format("tile.novaeng_core.hypernet_terminal_controller.name");
    }

    public void breakBlock(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
    }

    @Override
    public boolean canConnectRedstone(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nullable EnumFacing side) {
        return false;
    }

    @Override
    public boolean hasComparatorInputOverride(@Nonnull IBlockState state) {
        return false;
    }
}
