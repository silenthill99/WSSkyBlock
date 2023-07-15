package fr.silenthill99.wsskyblock.inventory.hook.modo;

import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.modo.OptionHolder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OptionInventory extends AbstractInventory<OptionHolder>
{
    public OptionInventory()
    {
        super(OptionHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        OptionHolder holder = new OptionHolder(target);

        Inventory inv = createInventory(holder, 27, "Options");
        inv.setItem(26, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, OptionHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        if (current.isSimilar(RETOUR))
            InventoryManager.openInventory(player, InventoryType.MENU, target);
    }
}
