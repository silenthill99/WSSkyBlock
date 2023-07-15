package fr.silenthill99.wsskyblock.inventory.hook;

import fr.silenthill99.wsskyblock.ItemBuilder;
import fr.silenthill99.wsskyblock.Main;
import fr.silenthill99.wsskyblock.inventory.AbstractInventory;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import fr.silenthill99.wsskyblock.inventory.InventoryType;
import fr.silenthill99.wsskyblock.inventory.holder.ShopJardinHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopJardinInventory extends AbstractInventory<ShopJardinHolder>
{
    public ShopJardinInventory()
    {
        super(ShopJardinHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        ShopJardinHolder holder = new ShopJardinHolder();

        ItemStack Bone_meal = new ItemBuilder(Material.INK_SACK, 10).setDurability((short) 15).setLore("Prix : 10€ la poudre", "Clique gauche pour acheter", "Clique droit pour vendre").toItemStack();
        ItemStack Graines_de_ble = new ItemBuilder(Material.SEEDS, 10).setLore("Prix : 10€ la graine", "Clique gauche pour acheter", "Clique droit pour vendre").toItemStack();
        ItemStack Graines_de_citrouille = new ItemBuilder(Material.PUMPKIN_SEEDS, 10).setLore("Prix : 10€ la graine", "Clique gauche pour acheter", "Clique droit pour vendre").toItemStack();
        ItemStack Graines_de_pasteque = new ItemBuilder(Material.MELON_SEEDS, 10).setLore("Prix : 10€ la graine", "Clique gauche pour acheter", "Clique droit pour vendre").toItemStack();

        Inventory inv = createInventory(holder, 54, "Jardins et Agriculture");
        inv.setItem(0, Bone_meal);
        inv.setItem(1, Graines_de_ble);
        inv.setItem(2, Graines_de_citrouille);
        inv.setItem(3, Graines_de_pasteque);
        inv.setItem(53, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ShopJardinHolder holder) {
        switch(current.getType())
        {
            case INK_SACK:
            {
                if (current.isSimilar(RETOUR))
                    InventoryManager.openInventory(player, InventoryType.SHOP_MENU);
                else if (current.getDurability() == 15)
                {
                    if (event.getClick().isLeftClick())
                    {
                        if (Main.getInstance().economy.has(player, 100))
                        {
                            player.getInventory().addItem(new ItemStack(Material.INK_SACK, 10, (byte)15));
                            player.sendMessage(ChatColor.GREEN + "Vous avez acheté 10 poudres d'os pour 100€");
                            Main.getInstance().economy.withdrawPlayer(player, 100);
                        }
                        else
                            player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez d'argent sur vous");
                    }
                    else if (event.getClick().isRightClick())
                    {
                        if (player.getInventory().contains(new ItemStack(Material.INK_SACK, 10, (byte)15)))
                        {
                            player.getInventory().removeItem(new ItemStack(Material.INK_SACK, 10, (byte) 15));
                            player.sendMessage(ChatColor.GREEN + "Vous avez vendu 10 poudres d'os pour 100€");
                            Main.getInstance().economy.depositPlayer(player, 100);
                        }
                        else
                            player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez de poudre d'os !");
                    }
                }
                break;

            }
            case SEEDS:
            {
                if (event.getClick().isLeftClick()) {
                    if (Main.getInstance().economy.has(player, 100)) {
                        player.getInventory().addItem(new ItemStack(Material.SEEDS, 10));
                        player.sendMessage(ChatColor.GREEN + "Vous avez acheté 10 graînes de blé pour 100€");
                        Main.getInstance().economy.withdrawPlayer(player, 100);
                    } else
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez d'argent sur vous");
                } else if (event.getClick().isRightClick()) {
                    if (player.getInventory().contains(Material.SEEDS, 10)) {
                        player.getInventory().removeItem(new ItemStack(Material.SEEDS, 10));
                        player.sendMessage(ChatColor.GREEN + "Vous avez vendu 10 graînes de blé pour 100€");
                        Main.getInstance().economy.depositPlayer(player, 100);
                    } else
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez de graînes de blé");
                }
                break;
            }
            case PUMPKIN_SEEDS:
            {
                if (event.getClick().isLeftClick()) {
                    if (Main.getInstance().economy.has(player, 100)) {
                        player.getInventory().addItem(new ItemStack(Material.PUMPKIN_SEEDS, 10));
                        player.sendMessage(ChatColor.GREEN + "Vous venez d'acheter 10 graines de citrouille pour 100€");
                        Main.getInstance().economy.withdrawPlayer(player, 100);
                    } else
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez d'argent !");
                }
                if (event.getClick().isRightClick()) {
                    if (player.getInventory().contains(Material.PUMPKIN_SEEDS, 10)) {
                        player.getInventory().removeItem(new ItemStack(Material.PUMPKIN_SEEDS, 10));
                        player.sendMessage(ChatColor.GREEN + "Vous avez vendu 10 graine de citrouille pour 100€");
                        Main.getInstance().economy.depositPlayer(player, 100);
                    } else
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez de graînes de citrouille sur vous !");
                }
                break;
            }
            case MELON_SEEDS:
            {
                if (event.getClick().isLeftClick()) {
                    if (Main.getInstance().economy.has(player, 100)) {
                        player.getInventory().addItem(new ItemStack(Material.MELON_SEEDS, 10));
                        player.sendMessage(ChatColor.GREEN + "Vous venez d'acheter 10 graines de pastèque pour 100€");
                        Main.getInstance().economy.withdrawPlayer(player, 100);
                    } else
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez d'argent !");
                } else if (event.getClick().isRightClick()) {
                    if (player.getInventory().contains(new ItemStack(Material.MELON_SEEDS))) {
                        player.getInventory().removeItem(new ItemStack(Material.MELON_SEEDS, 10));
                        player.sendMessage(ChatColor.GREEN + "Vous venez de vendre 10 graines de pastèque pour 100€");
                        Main.getInstance().economy.depositPlayer(player, 100);
                    } else
                        player.sendMessage(ChatColor.DARK_RED + "Vous n'avez pas assez de graines de pastèque !");
                }
                break;
            }
            default:
                break;
        }
    }
}
