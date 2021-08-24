package nl.alamut.play.firstplugin.utils

import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.CommandSender

object settings {
    val PREFIX = "&6&lAlamut » &r".translateColorCode()
    val PERMERROR = "&cJe hebt geen permissie om dit command te gebruiken!"

    fun send(message: String) {
        Bukkit.getConsoleSender().sendMessage("$PREFIX$message")
    }

    fun CommandSender.msg(message: String) {
        sendMessage("${settings.PREFIX}$message".translateColorCode());
    }
    fun Server.msgAll(message: String){
        onlinePlayers.forEach {
            it.msg(message)
        }
    }


    fun String.translateColorCode() = replace(Regex("&([A-Za-z0-9])")) { "§" + it.groups[1]!!.value }
}