package itserthere.displaymodifier;
import itserthere.displaymodifier.transformations.UserTransformationsHandler;
import itserthere.displaymodifier.util.TransformationData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class Reference {
    public static final String MOD_ID = "displaymodifier";
    public static final String MOD_NAME = "Display Modifier";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ResourceLocation SYNC_PACKET_ID = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "sync_packet");
    public static final ResourceLocation SCREEN_PACKET_ID = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "screen_packet");
    public static final Map<String, String> defaultTransformationMap = initializeTransformationMap();
    private static Map<String,String> initializeTransformationMap() {
        Map<String,String> transformationMap= new LinkedHashMap<>();
        transformationMap.put("default","/summon block_display ~ ~1 ~ {transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],scale:[1f,1f,1f],translation:[0f,0f,0f]}");
        return transformationMap;
    }
    public static final List<TransformationData> userTransformations=new ArrayList<>();
    public static void saveTransformation(String poseName, CompoundTag tag) {
        String tagString = tag.toString();
        userTransformations.add(new TransformationData(poseName, tagString));
        UserTransformationsHandler.saveUserPoses();
    }
    public static void removeTransformation(String poseName) {
        userTransformations.removeIf(pose -> pose.name().equalsIgnoreCase(poseName));
        UserTransformationsHandler.saveUserPoses();
    }
}
