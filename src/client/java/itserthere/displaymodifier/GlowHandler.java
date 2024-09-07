package itserthere.displaymodifier;

import java.util.UUID;

public class GlowHandler {
    private static long glowStartTime = 0;
    private static UUID glowingDisplay = null;

    public static boolean shouldDisplayGlow() {
        if (glowStartTime == -1) {
            return false;
        }
        boolean notEmpty = glowingDisplay != null;
        if (notEmpty && System.currentTimeMillis() - glowStartTime > 5000) {
            glowStartTime = -1;
            glowingDisplay = null;
        }
        return notEmpty;
    }

    public static boolean isGlowing(UUID uuid) {
        if (!shouldDisplayGlow())
            return false;
        else
            return glowingDisplay != null && glowingDisplay.equals(uuid);
    }

    public static void startGlowing(UUID uuid) {
        glowStartTime = System.currentTimeMillis();
        glowingDisplay = uuid;
    }
}
