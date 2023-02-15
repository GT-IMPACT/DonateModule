package space.impact.support.parser

import space.impact.support.client.model.cape.DonateCapeRenderer.Companion.donateList
import java.net.URL
import java.util.*

object CapeParser {

    fun parseCapeList() {
        try {
            val scanner = Scanner(URL("https://raw.githubusercontent.com/GT-IMPACT/DonateModule/main/accessCapes.txt").openStream())
            while (scanner.hasNextLine()) {
                val tName: String = scanner.nextLine()
                donateList.add(tName.lowercase(Locale.getDefault()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}