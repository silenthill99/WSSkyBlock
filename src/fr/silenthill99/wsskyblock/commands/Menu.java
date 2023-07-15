package fr.silenthill99.wsskyblock.commands;

import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Menu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Cette commande ne peux pas être éxécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0)
        {
            InventoryManager.openInventory(player, InventoryType.JOUEURS);
            return false;
        }

        if (args.length > 1)
        {
            player.sendMessage(ChatColor.DARK_RED + "Vous avez mis trop d'arguments");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        if (!target.isOnline())
        {
            player.sendMessage(ChatColor.DARK_RED + "Ce joueur n'est pas connecté ou n'existe pas !");
            return false;
        }

        InventoryManager.openInventory(player, InventoryType.MENU, target);

        return false;
    }
}
