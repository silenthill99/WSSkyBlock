package fr.silenthill99.wsskyblock.inventory.hook.admin;

import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.admin.AdministrationHolder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdministrationInventory extends AbstractInventory<AdministrationHolder>
{
    public AdministrationInventory()
    {
        super(AdministrationHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        AdministrationHolder holder = new AdministrationHolder(target);

        Inventory inv = createInventory(holder, 27, "Menu administration");
        inv.setItem(26, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, AdministrationHolder holder) {
        OfflinePlayer target = holder.getTarget();

        switch (current.getType())
        {
            case INK_SACK:
            {
                if (current.getDurability() == 1)
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
