package nl.alamut.play.firstplugin

import nl.alamut.play.firstplugin.utils.settings.PERMERROR
import nl.alamut.play.firstplugin.utils.settings.PREFIX
import nl.alamut.play.firstplugin.utils.settings.msg
import nl.alamut.play.firstplugin.utils.settings.msgAll
import nl.alamut.play.firstplugin.utils.settings.send
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionEffectType.REGENERATION
import org.bukkit.potion.PotionEffectType.registerPotionEffectType


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
     fun moveEvent(event: PlayerMoveEvent){
        if(event.player.location.block.type != Material.GRASS_BLOCK) return
        event.player.msg("Je staat op gras")
    }

    @EventHandler
    fun joinEvent(event: PlayerJoinEvent) {
        event.joinMessage(null)
        event.player.server.msgAll("${event.player.name} &ajoined &fthe game!")
//        val perms = event.player.effectivePermissions
//        perms.forEach {
//           event.player.msg(it.permission)
//        }

        if (event.player.hasPermission("alamut.perms.test")) {
            event.player.msg("Je hebt de &ajuiste permissie&r om dit command uit te voeren!")
        }
    }


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            when (command.name) {
                "test" -> {
                    sender.msg("Dit is de output van het test command")
                }
                "fly" -> {
                    if (args.isEmpty()) {
                        sender.msg("&eGebruik dit command ook om andere spelers te laten vliegen! &f/fly (username)")

                        if (!sender.allowFlight && sender.hasPermission("alamut.command.fly")) {
                            sender.allowFlight = true
                            sender.msg("Your flight has been &aenabled&r!")

                        } else if (sender.hasPermission("alamut.command.fly")) {
                            sender.msg("Your flight has been &cdisabled§r!")
                            sender.allowFlight = sender.allowFlight != true
                        } else
                            sender.msg(PERMERROR)
                    } else if (args.size == 1) {
                        val player = Bukkit.getOnlinePlayers().first { player -> player.name == args[0] }
                        if (player != null) {
                            player.allowFlight = !player.allowFlight
                            if (player.allowFlight) {
                                sender.msg("You have &aenabled &fflight for${player.name}")
                                player.msg("Your flight has been &aenabled&r!")
                            } else
                                sender.msg("You have &cdisabled &fflight for${player.name}")
                            player.msg("Your flight has been &cdisabled§r!")
                        }

                    }
                }
                "broadcast" -> {
                    if (sender.hasPermission("alamut.command.broadcast")) {
                        sender.server.msgAll(args.joinToString(separator = " "))
                    } else
                        sender.msg(PERMERROR)
                }
                "heal" -> {
                    if (sender.hasPermission("alamut.command.heal")) {
                        sender.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 40, 30, true))
                        if (sender.health == 20.0) {
                            sender.removePotionEffect(REGENERATION)
                            sender.msg("&rYou have &asuccessfully &rbeen &ahealed&r!")
                        }
                    } else
                        sender.msg(PERMERROR)
                }
            }
        }
        return true
    }

    fun runTimer(): Runnable {
        return Runnable {
            Bukkit.broadcastMessage("${PREFIX}Test broadcast message")
        }
    }

}

















