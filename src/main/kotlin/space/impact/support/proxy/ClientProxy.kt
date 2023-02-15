package space.impact.support.proxy

import cpw.mods.fml.common.event.FMLInitializationEvent
import space.impact.support.Support.registerEvent
import space.impact.support.client.event.SupportEvent

class ClientProxy : CommonProxy() {
    override fun init(event: FMLInitializationEvent) {
        registerEvent(SupportEvent())
    }
}