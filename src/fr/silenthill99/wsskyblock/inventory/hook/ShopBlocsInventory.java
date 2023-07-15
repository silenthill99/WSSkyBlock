package fr.silenthill99.wsskyblock.inventory.hook;

import fr.silenthill99.wsskyblock.ItemBuilder;
import fr.silenthill99.wsskyblock.Main;
import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.ShopBlocsHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopBlocsInventory extends AbstractInventory<ShopBlocsHolder>
{
    public ShopBlocsInventory()
    {
        super(ShopBlocsHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        ShopBlocsHolder holder = new ShopBlocsHolder();

        ItemStack cobblestone = new ItemBuilder(Material.COBBLESTONE, 10).setLore("Prix = 15€ la pierre", "Clique gauche pour acheter", "Clique droit pour vendre").toItemStack();

        Inventory inv = createInventory(holder, 54, "Acheter des blocs");
        inv.setItem(0, cobblestone);
        inv.setItem(53, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ShopBlocsHolder holder)
    {
        switch (current.getType())
        {
            case COBBLESTONE:
            {
                if (event.getClick().isLeftClick())
                {
                    if (Main.getInstance().economy.has(player, 150))
                    {
                        player.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 10));
                        Main.getInstance().economy.withdrawPlayer(player, 150);
                        player.sendMessage(ChatColor.GREEN + "Vous avez acheté 10 pierres pour 150€");
                    }
                    else
                    {
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez d'argent !");
                    }
                }
                else if (event.getClick().isRightClick())
                {
                    if (player.getInventory().contains(Material.COBBLESTONE, 10)){
                        player.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 10));
                        Main.getInstance().economy.depositPlayer(player, 150);
                        player.sendMessage(ChatColor.GREEN + "Vous avez vendu 10 pierres pour 150€");
                    }
                    else
                    {
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez de pierres sur vous !");
                    }
                }
                break;
            }
            case INK_SACK:
            {
                if (current.isSimilar(RETOUR))
                    InventoryManager.openInventory(player, InventoryType.SHOP_MENU);
                break;
            }
            default:
                break;
        }
    }
}
