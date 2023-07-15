package fr.silenthill99.wsskyblock.inventory.hook;

import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.ShopNourritureHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopNourritureInventory extends AbstractInventory<ShopNourritureHolder>
{
    public ShopNourritureInventory()
    {
        super(ShopNourritureHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        ShopNourritureHolder holder = new ShopNourritureHolder();

        Inventory inv = createInventory(holder, 27, "Nourriture");
        inv.setItem(53, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ShopNourritureHolder holder) {
        switch (current.getType())
        {
            case INK_SACK:
            {
                if (current.isSimilar(RETOUR))
                    InventoryManager.openInventory(player, InventoryType.SHOP_MENU);
            }
        }
    }
}
