package plus.dragons.omnicard.renderer;


import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import plus.dragons.omnicard.blockentity.SpecialCardBlockTileEntity;
import plus.dragons.omnicard.model.SpecialCardBlockModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SpecialCardBlockRenderer extends GeoBlockRenderer<SpecialCardBlockTileEntity> {


    public SpecialCardBlockRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new SpecialCardBlockModel());
    }
}
