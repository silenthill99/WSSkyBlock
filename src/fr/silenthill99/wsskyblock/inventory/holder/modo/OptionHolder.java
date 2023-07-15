package fr.silenthill99.wsskyblock.inventory.holder.modo;

import fr.silenthill99.wsskyblock.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class OptionHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public OptionHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
