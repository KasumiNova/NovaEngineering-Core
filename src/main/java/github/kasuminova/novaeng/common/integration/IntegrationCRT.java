package github.kasuminova.novaeng.common.integration;

import github.kasuminova.novaeng.NovaEngineeringCore;
import github.kasuminova.novaeng.common.registry.RegistryHyperNet;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

@SuppressWarnings("MethodMayBeStatic")
public class IntegrationCRT {
    @SubscribeEvent
    @Optional.Method(modid = "zenutils")
    public void onScriptsReloading(ScriptReloadEvent.Pre event) {
        RegistryHyperNet.clearRegistry();
        NovaEngineeringCore.log.info("[NovaEng-Core] Cleared HyperNet registry.");
    }

    @SubscribeEvent
    @Optional.Method(modid = "zenutils")
    public void onScriptsReloaded(ScriptReloadEvent.Post event) {

    }
}
