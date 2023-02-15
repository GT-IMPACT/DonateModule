package space.impact.support.client.event

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraftforge.client.event.RenderPlayerEvent
import space.impact.support.client.model.cape.DonateCapeRenderer

@SideOnly(Side.CLIENT)
class SupportEvent {

    private val donateCapeRenderer: DonateCapeRenderer = DonateCapeRenderer()

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun receiveRenderSpecialsEvent(e: RenderPlayerEvent.Specials.Pre) {
        donateCapeRenderer.render(e)
    }
}