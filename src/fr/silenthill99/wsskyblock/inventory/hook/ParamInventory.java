package fr.silenthill99.wsskyblock.inventory.hook;

import fr.silenthill99.wsskyblock.ItemBuilder;
import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.holder.ParamHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ParamInventory extends AbstractInventory<ParamHolder>
{
    public ParamInventory()
    {
        super(ParamHolder.class);
    }

    ItemStack VISIBLE = new ItemBuilder(Material.INK_SACK).setDurability((short) 10).setName(ChatColor.GREEN + "Cacher les autres joueurs").toItemStack();
    ItemStack INVISIBLE = new ItemBuilder(Material.INK_SACK).setDurability((short) 1).setName(ChatColor.RED + "Montrer les autre joueurs").toItemStack();


    @Override
    public void openInventory(Player player, Object... args)
    {
        ParamHolder holder = new ParamHolder();

        Inventory inv = createInventory(holder, 27, "Paramètres");

        if (holder.hidden.contains(player.getUniqueId()))
            inv.setItem(0, INVISIBLE);
        else
            inv.setItem(0, VISIBLE);

        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ParamHolder holder) {
        switch (current.getType())
        {
            case INK_SACK:
            {
                if (current.getDurability() == 10)
                {
                    player.sendMessage(ChatColor.GREEN + "Les autres joueurs sont désormais invisibles !");
                    for (Player players : Bukkit.getOnlinePlayers())
                    {
                        player.hidePlayer(players);
                    }
                    holder.hidden.add(player.getUniqueId());
                }
                else if (current.getDurability() == 1)
                {
                    player.sendMessage(ChatColor.GREEN + "Les autres joueurs sont désormais visibles !");
                    for (Player players : Bukkit.getOnlinePlayers())
                    {
                        player.showPlayer(players);
                    }
                    holder.hidden.remove(player.getUniqueId());
                }
                openInventory(player);
                break;
            }
            default:
                break;
        }
    }
}
