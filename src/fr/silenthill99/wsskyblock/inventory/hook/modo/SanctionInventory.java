package fr.silenthill99.wsskyblock.inventory.hook.modo;

import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.modo.SanctionHolder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SanctionInventory extends AbstractInventory<SanctionHolder>
{
    public SanctionInventory()
    {
        super(SanctionHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        SanctionHolder holder = new SanctionHolder(target);

        Inventory inv = createInventory(holder, 27, "Sanctionner " + target.getName());
        inv.setItem(26, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, SanctionHolder holder) {
        OfflinePlayer target = holder.getTarget();
        if (current.isSimilar(RETOUR))
            InventoryManager.openInventory(player, InventoryType.MENU, target);
    }
}
