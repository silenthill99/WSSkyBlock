package fr.silenthill99.wsskyblock.inventory.hook;

import fr.silenthill99.wsskyblock.ItemBuilder;
import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.ShopHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopMenuInventory extends AbstractInventory<ShopHolder>
{
    public ShopMenuInventory()
    {
        super(ShopHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        ShopHolder holder = new ShopHolder();

        ItemStack stone = new ItemBuilder(Material.COBBLESTONE).setName(ChatColor.YELLOW + "Blocs de construction").toItemStack();
        ItemStack Jardin = new ItemBuilder(Material.RED_ROSE).setName(ChatColor.GOLD + "Jardins et agriculture").toItemStack();
        ItemStack Nourriture = new ItemBuilder(Material.APPLE).setName(ChatColor.RED + "Nourriture").toItemStack();

        Inventory inv = createInventory(holder, 27, "Magasin");
        inv.setItem(0, stone);
        inv.setItem(1, Jardin);
        inv.setItem(2, Nourriture);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ShopHolder holder)
    {
        switch(current.getType())
        {
            case COBBLESTONE:
            {
                InventoryManager.openInventory(player, InventoryType.SHOP_BLOCS);
                break;
            }
            case RED_ROSE:
            {
                InventoryManager.openInventory(player, InventoryType.SHOP_JARDIN);
                break;
            }
            case APPLE:
            {
                InventoryManager.openInventory(player, InventoryType.SHOP_NOURRITURE);
                break;
            }
            default:
                break;
        }
    }
}
