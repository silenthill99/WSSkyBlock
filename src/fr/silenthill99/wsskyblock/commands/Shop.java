package fr.silenthill99.wsskyblock.commands;

import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Shop implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Cette commande ne peux pas être éxécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (!hasAvaliableSlot(player))
        {
            player.sendMessage(ChatColor.DARK_RED + "Veuillez faire de la place dans votre inventaire !");
            return false;
        }

        player.sendMessage(ChatColor.DARK_RED + "Attention ! Système en bêta");

        InventoryManager.openInventory(player, InventoryType.SHOP_MENU);

        return false;
    }

    public boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }
}
