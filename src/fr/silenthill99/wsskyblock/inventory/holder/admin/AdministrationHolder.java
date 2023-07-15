package fr.silenthill99.wsskyblock.inventory.holder.admin;

import fr.silenthill99.wsskyblock.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class AdministrationHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public AdministrationHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
