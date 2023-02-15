package space.impact.support

import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import net.minecraftforge.common.MinecraftForge
import space.impact.support.proxy.CommonProxy


@Mod(
    modid = MODID,
    version = VERSION,
    name = MODNAME,
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:forgelin;"
)
object Support {

    @SidedProxy(clientSide = "$GROUPNAME.proxy.ClientProxy", serverSide = "$GROUPNAME.proxy.CommonProxy")
    lateinit var proxy: CommonProxy

    @Mod.InstanceFactory
    fun instance() = Support

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
    }

    fun registerEvent(obj: Any?) {
        FMLCommonHandler.instance().bus().register(obj)
        MinecraftForge.EVENT_BUS.register(obj)
    }
}