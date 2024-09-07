package itserthere.displaymodifier.platform.services; import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Display; import java.nio.file.Path;
public interface IPlatformHelper {
    void updateEntity(Display display, CompoundTag compound);
    boolean allowScrolling(); Path getUserPresetFolder();
}
