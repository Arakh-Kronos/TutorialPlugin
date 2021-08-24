package nl.alamut.play.firstplugin.utils

import org.bukkit.Bukkit

object settings {

    val PREFIX = "§6§lAlamut » §r"

    fun send(message: String) {
        Bukkit.getConsoleSender().sendMessage("$PREFIX$message")
    }

    public var players = Bukkit.getOnlinePlayers()

}