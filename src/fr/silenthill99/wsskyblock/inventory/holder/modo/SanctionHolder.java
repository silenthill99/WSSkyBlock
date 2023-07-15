package fr.silenthill99.wsskyblock.inventory.holder.modo;

import fr.silenthill99.wsskyblock.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class SanctionHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public SanctionHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
