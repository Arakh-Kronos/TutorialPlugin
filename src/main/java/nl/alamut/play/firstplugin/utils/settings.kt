package nl.alamut.play.firstplugin.utils

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

object settings {

    val PREFIX = "§6§lAlamut » §r"

    fun send(message: String) {
        Bukkit.getConsoleSender().sendMessage("$PREFIX$message")

        fun sender.msg(message: string) {
            it.sendMessage("$PREFIX $message")
        }
    }

    

}