package dev.cnbetty.core.cutscenes.util;

import org.bukkit.Location;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Player;

public class Camera {
    private Marker cameraentity;

    public Camera(Location location, Player player) {
        //is.cameraentity = location.getWorld().spawn(location, Marker.class, CreatureSpawnEvent.SpawnReason.CUSTOM)
        /*BlockDisplay display = location.getWorld().spawn(location, BlockDisplay.class, entity -> {
            // customize the entity!
            entity.setBlock(Material.GRASS_BLOCK.createBlockData());
        });*/
        Marker camera = location.getWorld().spawn(location, Marker.class, entity -> {
            entity.addPassenger(player);
        });
    }
}
