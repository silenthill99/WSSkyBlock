package fr.silenthill99.wsskyblock;

import fr.silenthill99.wsskyblock.commands.Menu;
import fr.silenthill99.wsskyblock.commands.Param;
import fr.silenthill99.wsskyblock.commands.Shop;
import fr.silenthill99.wsskyblock.inventory.InventoryManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    public Economy economy = null;

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Le plugin est opérationnel");
        commands();
        setupEconomy();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryManager(), this);
    }

    public boolean setupEconomy() {
        RegisteredServiceProvider<Economy> eco = getServer().getServicesManager().getRegistration(Economy.class);
        if (eco != null) {
            economy = eco.getProvider();
        }
        return economy != null;
    }

    private void commands()
    {
        getCommand("shop").setExecutor(new Shop());
        getCommand("menu").setExecutor(new Menu());
        getCommand("param").setExecutor(new Param());
    }
}
