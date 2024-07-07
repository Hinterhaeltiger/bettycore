package dev.cnbetty.core.cutscenes.util;

import net.minecraft.world.entity.Marker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;

public class Camera {
    private Marker cameraentity;

    public Camera(Location location) {
        //is.cameraentity = location.getWorld().spawn(location, Marker.class, CreatureSpawnEvent.SpawnReason.CUSTOM)
        BlockDisplay display = location.getWorld().spawn(location, BlockDisplay.class, entity -> {
            // customize the entity!
            entity.setBlock(Material.GRASS_BLOCK.createBlockData());
        });
    }
}
