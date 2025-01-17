package github.kasuminova.novaeng.common.hypernet.upgrade.type;

import crafttweaker.annotations.ZenRegister;
import github.kasuminova.mmce.common.upgrade.UpgradeType;
import github.kasuminova.mmce.common.upgrade.registry.RegistryUpgrade;
import github.kasuminova.novaeng.common.hypernet.upgrade.ProcessorModuleCPU;
import github.kasuminova.novaeng.common.registry.RegistryHyperNet;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("novaeng.hypernet.upgrade.type.ProcessorModuleCPUType")
public class ProcessorModuleCPUType extends ProcessorModuleType {
    protected final float computationPointGeneration;

    public ProcessorModuleCPUType(final int minDurability,
                                  final int maxDurability,
                                  final int energyConsumption,
                                  final float computationPointGeneration)
    {
        super(minDurability, maxDurability, energyConsumption);
        this.computationPointGeneration = computationPointGeneration;
    }

    @ZenMethod
    public static ProcessorModuleCPUType create(final int minDurability,
                                                final int maxDurability,
                                                final int energyConsumption,
                                                final float computationPointGeneration)
    {
        return new ProcessorModuleCPUType(minDurability, maxDurability, energyConsumption, computationPointGeneration);
    }

    @Override
    public ProcessorModuleCPUType register(String typeName, String localizedName, int level) {
        UpgradeType type = new UpgradeType(typeName, localizedName, level, 1);
        RegistryHyperNet.addDataProcessorModuleCPUType(type, this);
        RegistryUpgrade.registerUpgrade(typeName, new ProcessorModuleCPU(type));

        return this;
    }

    @ZenGetter("computationPointGeneration")
    public float getComputationPointGeneration() {
        return computationPointGeneration;
    }
}
