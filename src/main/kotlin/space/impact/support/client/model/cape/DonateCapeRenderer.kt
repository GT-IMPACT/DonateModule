package space.impact.support.client.model.cape

import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.model.ModelBiped
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.RenderPlayer
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.potion.Potion
import net.minecraft.util.MathHelper
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.RenderPlayerEvent
import org.lwjgl.opengl.GL11
import java.lang.reflect.Field

class DonateCapeRenderer : RenderPlayer() {

    init {
        setRenderManager(RenderManager.instance)
    }
    fun render(event: RenderPlayerEvent.Specials.Post) {
        val player = event.entityPlayer as AbstractClientPlayer
        val partialTicks = event.partialRenderTick
        if (player.isInvisible) return
        if (getPotion(player, Potion.invisibility.id)) return
        try {
            var cape: ResourceLocation? = null
            loop@for (name in donateList) {
                if (player.displayName.equals(name, ignoreCase = true)) {
                    cape = DONATE_CAPE
                    break@loop
                }
            }
            if (cape != null && !player.hideCape) {
                bindTexture(cape)
                GL11.glPushMatrix()
                GL11.glTranslatef(0.0f, 0.0f, 0.125f)
                val d0 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * partialTicks)
                val d1 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * partialTicks)
                val d2 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks)
                val f6 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks
                val d3 = MathHelper.sin(f6 * 3.141593f / 180.0f).toDouble()
                val d4 = -MathHelper.cos(f6 * 3.141593f / 180.0f).toDouble()
                var f7 = d1.toFloat() * 10.0f
                var f8 = (d0 * d3 + d2 * d4).toFloat() * 100.0f
                val f9 = (d0 * d4 - d2 * d3).toFloat() * 100.0f
                if (f7 < -6.0f) {
                    f7 = -6.0f
                }
                if (f7 > 32.0f) {
                    f7 = 32.0f
                }
                if (f8 < 0.0f) {
                    f8 = 0.0f
                }
                val f10 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks
                f7 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0f) * 32.0f * f10
                if (player.isSneaking) {
                    f7 += 25.0f
                }
                GL11.glRotatef(6.0f + f8 / 2.0f + f7, 1.0f, 0.0f, 0.0f)
                GL11.glRotatef(f9 / 2.0f, 0.0f, 0.0f, 1.0f)
                GL11.glRotatef(-f9 / 2.0f, 0.0f, 1.0f, 0.0f)
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f)
                (mainModel as ModelBiped).renderCloak(0.0625f)
                GL11.glPopMatrix()
            }
        } catch (ignored: Exception) {
        }
    }

    companion object {
        private val DONATE_CAPE = ResourceLocation("support:textures/DonateCape.png")
        val donateList: MutableList<String> = ArrayList()

        fun getPotion(aPlayer: EntityLivingBase?, aPotionIndex: Int): Boolean {
            try {
                var tPotionHashmap: Field? = null
                val var3 = EntityLiving::class.java.declaredFields
                for (var6 in var3) {
                    if (var6.type == HashMap::class.java) {
                        tPotionHashmap = var6
                        var6.isAccessible = true
                        break
                    }
                }
                if (tPotionHashmap != null) {
                    return (tPotionHashmap[aPlayer] as HashMap<*, *>)[aPotionIndex] != null
                }
            } catch (ignored: Throwable) {
            }
            return false
        }
    }
}