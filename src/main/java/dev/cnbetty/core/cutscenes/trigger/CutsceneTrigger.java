package dev.cnbetty.core.cutscenes.trigger;

import org.jetbrains.annotations.NotNull;

public interface CutsceneTrigger {
    public void onTriggered(@NotNull TriggerContext context);
}
