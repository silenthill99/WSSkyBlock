package fr.silenthill99.wsskyblock.inventory.hook.modo;

import fr.silenthill99.wsskyblock.ItemBuilder;
import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.modo.MenuHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuInventory extends AbstractInventory<MenuHolder>
{
    static OfflinePlayer target;
    public MenuInventory()
    {
        super(MenuHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        target = (OfflinePlayer) args[0];
        MenuHolder holder = new MenuHolder(target);

        ItemStack tete = new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3).toItemStack();
        ItemStack Administrateur = new ItemBuilder(Material.INK_SACK, 1, (byte)1).setName(ChatColor.RED + "Menu Administration").toItemStack();

        Inventory inv = createInventory(holder, 27, "Menu de " + target.getName());
        inv.setItem(4, tete);
        for (Papier papier: Papier.values())
        {
            holder.papier.put(papier.getSlot(), papier);
            inv.setItem(papier.getSlot(), new ItemBuilder(Material.PAPER).setName(papier.getName()).toItemStack());
        }
        inv.setItem(13, Administrateur);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, MenuHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Papier papier = holder.papier.get(event.getSlot());

        switch(current.getType())
        {
            case PAPER:
            {
                switch (papier)
                {
                    case SANCTIONNER:
                    {
                        InventoryManager.openInventory(player, InventoryType.SANCTION, target);
                        break;
                    }
                    case OPTIONS:
                    {
                        InventoryManager.openInventory(player, InventoryType.OPTION, target);
                        break;
                    }
                    default:
                        break;
                }
                break;
            }
            case INK_SACK:
            {
                if (current.getDurability() == 1)
                {
                    if (!player.hasPermission("group.administrateur"))
                        InventoryManager.openInventory(player, InventoryType.ADMINISTRATION, target);
                    else
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas accès au panel de l'administration !");
                }
            }
            default:
                break;
        }
    }

    public enum Papier
    {
        SANCTIONNER(10, ChatColor.DARK_RED + "Sanctionner " + target.getName()),
        OPTIONS(16, ChatColor.GOLD + "Options");

        private final int slot;
        private final String name;

        Papier(int slot, String name)
        {
            this.slot = slot;
            this.name = name;
        }

        public int getSlot()
        {
            return this.slot;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
