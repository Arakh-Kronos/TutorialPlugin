package nl.alamut.play.firstplugin

import nl.alamut.play.firstplugin.utils.settings.PREFIX
import nl.alamut.play.firstplugin.utils.settings.send
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin


public class FirstPlugin : JavaPlugin(), Listener, CommandExecutor {
    override fun onEnable() {
        // Plugin startup logic

        send("Lobby plugin is now enabled!")

        Bukkit.getPluginManager().registerEvents(this, this)
        Bukkit.getScheduler().runTaskTimer(this, runTimer(), 20*5, 20*12)
    }

    override fun onDisable() {
        // Plugin shutdown logic

        send("Lobby plugin is now disabled!")
    }

    @EventHandler
    fun joinEvent(event: PlayerJoinEvent) {
        event.player.sendMessage(PREFIX + event.player.name + " joined the game!")
        val players = Bukkit.getOnlinePlayers()
        for(p in players){
            val perms = p.effectivePermissions
            for(perm in perms){
                p.sendMessage(PREFIX + perm.permission)
            }
            if(p.hasPermission("alamut.perms.test")){
                p.sendMessage(PREFIX + "Je hebt de juiste permissie om dit command uit te voeren!")
            }
        }
        }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            when (command.name) {
                "test" -> {
                    sender.sendMessage("${PREFIX}Dit is de output van het test command")
                }
                "fly" -> {
                    if(!sender.allowFlight && sender.hasPermission("alamut.command.fly")) {
                        sender.sendMessage("${PREFIX}Your flight has been §aenabled§r!")
                    } else if (sender.hasPermission("alamut.command.fly")) {
                            sender.sendMessage("${PREFIX}Your flight has been §cdisabled§r!")
                            sender.allowFlight = sender.allowFlight != true
                        } else
                            sender.sendMessage("$PREFIX§cJe hebt niet de juiste permissie!")
                }
            }
        }
        return true
    }

    private fun runTimer(): Runnable {
        return Runnable {
            Bukkit.broadcastMessage(PREFIX + "Test broadcast message")
        }
    }

}

















