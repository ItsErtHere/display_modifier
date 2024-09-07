package itserthere.displaymodifier.gui.widgets;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import itserthere.displaymodifier.util.TransformationData;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import org.jetbrains.annotations.NotNull;

public record TransformationEntry(TransformationData tdata, boolean userAdded) implements Comparable<TransformationEntry> {
    public TransformationEntry(String name, String data, boolean userAdded) {
        this(new TransformationData(name, data), userAdded);
    }

    public TransformationData tdata() {
        return null;
    }

    public String getName() {
        assert tdata() != null;
        if (userAdded()) {
            return tdata().name();
        } else {
            return I18n.get("displaymodifier.gui.transformation." + tdata().name());
        }
    }
    public CompoundTag getTag() {
        try {
            assert tdata() != null;
            return TagParser.parseTag(tdata().data());
        } catch (CommandSyntaxException e) {
            return null;
        }
    }
    @Override
    public int compareTo(@NotNull TransformationEntry o) {
        return getName().compareTo(o.getName());
    }
}
