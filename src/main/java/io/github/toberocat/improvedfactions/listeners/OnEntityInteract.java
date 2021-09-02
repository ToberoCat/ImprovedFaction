package io.github.toberocat.improvedfactions.listeners;

import io.github.toberocat.improvedfactions.ImprovedFactionsMain;
import io.github.toberocat.improvedfactions.data.PlayerData;
import io.github.toberocat.improvedfactions.factions.Faction;
import io.github.toberocat.improvedfactions.factions.FactionUtils;
import io.github.toberocat.improvedfactions.utility.ChunkUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnEntityInteract implements Listener {

    @EventHandler
    public void EntityInteract(PlayerInteractEntityEvent event) {
        PlayerData playerData = ImprovedFactionsMain.playerData.get(event.getPlayer().getUniqueId());

        Faction claimFaction = ChunkUtils.GetFactionClaimedChunk(event.getRightClicked().getLocation().getChunk());

        if (claimFaction == null)
            return;

        if (FactionUtils.getFaction(event.getPlayer()) == null) {
            event.setCancelled(true);
            return;
        }

        if (!claimFaction.getRegistryName()
                .equals(playerData.playerFaction.getRegistryName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void HangingBreak(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof Player) {
            PlayerData playerData = ImprovedFactionsMain.playerData.get(event.getRemover().getUniqueId());

            Faction claimFaction = ChunkUtils.GetFactionClaimedChunk(event.getEntity().getLocation().getChunk());

            if (claimFaction == null)
                return;

            if (FactionUtils.getFaction((Player) event.getRemover()) == null) {
                event.setCancelled(true);
                return;
            }

            if (!claimFaction.getRegistryName()
                    .equals(playerData.playerFaction.getRegistryName())) {
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void ArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        PlayerData playerData = ImprovedFactionsMain.playerData.get(event.getPlayer().getUniqueId());

        Faction claimFaction = ChunkUtils.GetFactionClaimedChunk(event.getRightClicked().getLocation().getChunk());

        if (claimFaction == null)
            return;

        if (FactionUtils.getFaction(event.getPlayer()) == null) {
            event.setCancelled(true);
            return;
        }

        if (!claimFaction.getRegistryName()
                .equals(playerData.playerFaction.getRegistryName())) {
            event.setCancelled(true);
        }
    }
}
