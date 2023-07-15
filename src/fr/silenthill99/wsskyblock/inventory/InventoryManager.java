package fr.silenthill99.wsskyblock.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryManager implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        for (InventoryType inv : InventoryType.values())
        {
            inv.getInv().onJoin(event);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        for (InventoryType inv : InventoryType.values())
        {
            inv.getInv().onQuit(event);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        for (InventoryType inv : InventoryType.values())
        {
            inv.getInv().onInteract(event);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        if (event.getClickedInventory() == null || !(event.getWhoClicked() instanceof Player))
            return;
        Player player = (Player) event.getWhoClicked();
        InventoryHolder holder = event.getClickedInventory().getHolder();
        if (holder instanceof SilenthillHolder)
        {
            SilenthillHolder nh = (SilenthillHolder) holder;
            for (InventoryType type : InventoryType.values())
            {
                AbstractInventory inv = type.getInv();
                if (inv.isInstance(nh))
                {
                    event.setCancelled(true);
                    ItemStack item = event.getCurrentItem();
                    if (item != null)
                    {
                        if (item.isSimilar(AbstractInventory.CLOSE))
                            player.closeInventory();
                        else
                            inv.manageInventory(event, item, player, nh);
                    }
                    else
                        inv.voidInventory(event, player, nh);
                    return;
                }
            }
        }
        else if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) || !(event.getInventory().getHolder() instanceof SilenthillHolder))
        {
            SilenthillHolder nh = (SilenthillHolder) event.getInventory().getHolder();
            for (InventoryType type : InventoryType.values())
            {
                AbstractInventory inv = type.getInv();
                if (inv.isInstance(nh))
                {
                    event.setCancelled(true);
                    inv.moveFromOtherInventory(event, event.getInventory(), player, nh);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event)
    {
        if (event.getInventory() == null || !(event.getPlayer() instanceof Player))
            return;
        Player player = (Player) event.getPlayer();
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof SilenthillHolder)
        {
            SilenthillHolder nh = (SilenthillHolder) holder;
            for (InventoryType type : InventoryType.values())
            {
                AbstractInventory<?> inv = type.getInv();
                if (inv.isInstance(nh))
                {
                    inv.closeInventory(player, event);
                }
            }
        }
    }

    public static void openInventory(Player player, InventoryType type, Object... args)
    {
        type.getInv().openInventory(player, args);
    }
}
