package fr.silenthill99.wsskyblock.inventory.hook.modo;

import fr.silenthill99.wsskyblock.ItemBuilder;
import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.modo.JoueursHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class JoueursInventory extends AbstractInventory<JoueursHolder>
{
    public JoueursInventory() {
        super(JoueursHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        JoueursHolder holder = new JoueursHolder();

        Inventory inv = createInventory(holder, 54, "Liste des joueurs connectés");
        int slot = 0;
        for (Player target : Bukkit.getOnlinePlayers())
        {
            holder.target.put(slot, target);
            inv.setItem(slot++, new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3).setSkullOwner(target.getName()).setName("§e" + target.getName()).toItemStack());
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, JoueursHolder holder)
    {
        OfflinePlayer target = holder.target.get(event.getSlot());
        switch (current.getType())
        {
            case SKULL_ITEM:
            {
                if (current.getDurability() == 3)
                {
                    InventoryManager.openInventory(player, InventoryType.MENU, target);
                }
                break;
            }
            default:
                break;
        }
    }
}
