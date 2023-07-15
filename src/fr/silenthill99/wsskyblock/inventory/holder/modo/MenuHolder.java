package fr.silenthill99.wsskyblock.inventory.holder.modo;

import fr.silenthill99.wsskyblock.inventory.SilenthillHolder;
import fr.silenthill99.wsskyblock.inventory.hook.modo.MenuInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class MenuHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public MenuHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public HashMap<Integer, Papier> papier = new HashMap<>();
}
