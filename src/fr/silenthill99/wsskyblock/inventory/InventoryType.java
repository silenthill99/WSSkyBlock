package fr.silenthill99.wsskyblock.inventory;


import fr.silenthill99.wsskyblock.inventory.hook.*;
import fr.silenthill99.wsskyblock.inventory.hook.admin.AdministrationInventory;
import fr.silenthill99.wsskyblock.inventory.hook.modo.JoueursInventory;
import fr.silenthill99.wsskyblock.inventory.hook.modo.MenuInventory;
import fr.silenthill99.wsskyblock.inventory.hook.modo.OptionInventory;
import fr.silenthill99.wsskyblock.inventory.hook.modo.SanctionInventory;


public enum InventoryType
{
    ADMINISTRATION(new AdministrationInventory()),
    JOUEURS(new JoueursInventory()),
    MENU(new MenuInventory()),
    OPTION(new OptionInventory()),
    PARAMETRES(new ParamInventory()),
    SANCTION(new SanctionInventory()),
    SHOP_BLOCS(new ShopBlocsInventory()),
    SHOP_JARDIN(new ShopJardinInventory()),
    SHOP_MENU(new ShopMenuInventory()),
    SHOP_NOURRITURE(new ShopNourritureInventory())
    ;
    private final AbstractInventory<?> inv;

    InventoryType(AbstractInventory<?> inv)
    {
        this.inv = inv;
    }

    public AbstractInventory<?> getInv()
    {
        return this.inv;
    }

}
