package dev.cnbetty.core.cutscenes.trigger;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TriggerContext {
    private final Player triggerplayer;
    private final CutsceneTrigger cutscenetrigger;
    private Location location;
    private String triggermessage;

    public TriggerContext(Location location, Player triggerplayer, CutsceneTrigger cutscenetrigger) {
        this.location = location;
        this.triggerplayer = triggerplayer;
        this.cutscenetrigger = cutscenetrigger;
    }

    public TriggerContext(CutsceneTrigger cutscenetrigger, Player triggerplayer) {
        this.cutscenetrigger = cutscenetrigger;
        this.triggerplayer = triggerplayer;
    }

    public TriggerContext(Player triggerplayer, String triggermessage, CutsceneTrigger cutscenetrigger) {
        this.triggerplayer = triggerplayer;
        this.triggermessage = triggermessage;
        this.cutscenetrigger = cutscenetrigger;
    }

    public String getTriggerMessage() {
        return triggermessage;
    }

    public Location getLocation() {
        return location;
    }

    public Player getTriggerplayer() {
        return triggerplayer;
    }

    public CutsceneTrigger getCutscenetrigger() {
        return cutscenetrigger;
    }
}
