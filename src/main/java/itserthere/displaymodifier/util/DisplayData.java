package itserthere.displaymodifier.util;

import com.mojang.math.Transformation;
import jdk.jfr.Description;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.awt.*;
import java.util.Objects;

public class DisplayData {
    public static boolean customNameVisible = false;
    public static Vector3f scale=new Vector3f(1,1,1);
    public static Quaternionf left_rotation = new Quaternionf(0,0,0,1);
    public static Vector3f translation=new Vector3f(1,1,1);
    public static Quaternionf right_rotation = new Quaternionf(0,0,0,1);
    public static Float interpolation_duration = 0F; public static int teleportation_duration = 0;
    public static Float start_interpolation = 0F; public static int block_brightness = 15;
    public static Vector3f repetition = new Vector3f(1,1,1);
    public static Vector3f shift = new Vector3f(1,1,1); public static int sky_brightness;
    public static Transformation transformation = new Transformation(translation,left_rotation,scale,right_rotation);
    public float[] trans = new float[16]; public static int shadow_radius = 1;
    public static Color glow_color=new Color(255,255,255);
    public static int view_range; public static int shadow_strength = 0;
    public static boolean horizontal_billboard=false;public static boolean vertical_billboard=false;
    public static String block_id="air";public static String displayType="block";
    public static String custom_name="";
    private final String[] display_options = new String[]{"none","thirdperson_lefthand","thirdperson_righthand","firstperson_lefthand",
            "firstperson_righthand","head","gui","ground","fixed"};
    public static Float[] allValues = new Float[] {
            scale.x,scale.y,scale.z,start_interpolation,
            translation.x,translation.y,translation.z,interpolation_duration,
            left_rotation.x,left_rotation.y,left_rotation.z,left_rotation.w,
            right_rotation.x,right_rotation.y,right_rotation.z,right_rotation.w,
            (float)glow_color.getRed(), (float)glow_color.getGreen(), (float)glow_color.getBlue(),(float) teleportation_duration,
            (float)shadow_radius, (float) shadow_strength,(float) block_brightness, 0F,(float) sky_brightness,(float)view_range};
    public void readFromNBT(CompoundTag compoundTag) {
        if(compoundTag.contains("glow_color_override")) {setGlowColor(new Color(compoundTag.getInt("glow_color_override"),false));}
        if(compoundTag.contains("left_rotation")) {setLeftRotation(qtfromString(compoundTag.getAsString(),"left_rotation"));}
        if(compoundTag.contains("right_rotation")) {setRightRotation(qtfromString(compoundTag.getAsString(),"right_rotation"));}
        if(compoundTag.contains("scale")) {setScale(v3fromString(compoundTag.getAsString(),"scale"));}
        if(compoundTag.contains("translation")) {setTranslation(v3fromString(compoundTag.getAsString(),"translation"));}
        if(compoundTag.contains("billboard")) {
            setHorizontalBillboard(getHorizontalBillboardFromString(compoundTag.getString("billboard")));
            setVerticalBillboard(getVerticalBillboardFromString(compoundTag.getString("billboard")));
        }
        if(compoundTag.contains("start_interpolation")) {setInterpolationStart((float) compoundTag.getInt("start_interpolation"));}
        if(compoundTag.contains("interpolation_duration")) {setInterpolationDuration((float) compoundTag.getInt("interpolation_duration"));}
        if(compoundTag.contains("teleport_duration")) {setTeleportationDuration(compoundTag.getInt("teleport_duration"));}
        if(compoundTag.contains("block_brightness")) {setBlockBrightness(compoundTag.getInt("block_brightness"));}
        if(compoundTag.contains("sky_brightness")) {setSkyBrightness(compoundTag.getInt("sky_brightness"));}
        if(compoundTag.contains("shadow_radius")) {setShadowRadius(compoundTag.getInt("shadow_radius"));}
        if(compoundTag.contains("shadow_strength")) {setShadowStrength(compoundTag.getInt("shadow_strength"));}
        if(compoundTag.contains("view_range")) {setViewRange(compoundTag.getInt("view_range"));}
        if(compoundTag.contains("item")) {setDisplayType(compoundTag.getString("item_display"));setBlockId(compoundTag.getString("item"));}
        if(compoundTag.contains("block_state")) {setDisplayType("block");setBlockId(compoundTag.getString("Name"));}
        if(compoundTag.contains("custom_name")) {custom_name=compoundTag.getString("custom_name");}
    }
    public String getBlockId() {return block_id;}
    public void setBlockId(String s) {block_id=s;}
    public void setTeleportationDuration(int q) {teleportation_duration=q;}
    public void setSkyBrightness(int q) {sky_brightness=q;}
    public void setViewRange(int q) {view_range=q;}
    public void setShadowRadius(int q) {shadow_radius=q;}
    public void setShadowStrength(int q) {shadow_strength=q;}
    @Description(value = "Block Displays return False, Item Displays return True. Text Displays and Interaction entities are not included in this class")
    public String getDisplayType() {return displayType;}
    @Description(value = "Set to false for Block Displays, set to true for Item Displays. Text Displays and Interaction entities are not included in this class")
    public void setDisplayType(String f) {displayType=f;}
    public boolean getHorizontalBillboard() {return horizontal_billboard;}
    public void setHorizontalBillboard(Boolean b) {horizontal_billboard=b;}
    public boolean getVerticalBillboard() {return vertical_billboard;}
    public void setVerticalBillboard(Boolean b) {vertical_billboard=b;}
    public String getBillboard() {
        String s="";
        if(!horizontal_billboard&!vertical_billboard) s="fixed";
        else if(!horizontal_billboard&vertical_billboard) s="vertical";
        else if(horizontal_billboard&!vertical_billboard) s="horizontal";
        else if(horizontal_billboard&vertical_billboard) s="center";
        return s;
    }
    public boolean getHorizontalBillboardFromString(String s) {
        return Objects.equals(s, "horizontal") | Objects.equals(s, "center");
    }
    public boolean getVerticalBillboardFromString(String s) {
        return Objects.equals(s, "vertical") | Objects.equals(s, "center");
    }
    public void setBillboard(boolean h,boolean v) {horizontal_billboard=h;vertical_billboard=v;}
    public Vector3f getTranslation() {return translation;}
    public void setTranslation(Vector3f v) {translation=v;}
    public void setCustomNameVisible(Boolean b) {customNameVisible=b;}
    public boolean getCustomNameVisible() {return customNameVisible;}
    public void setScale(Vector3f v) {scale=v;}
    public Vector3f getScale() {return scale;}
    public void setLeftRotation(Quaternionf q) {left_rotation=q;}
    public Quaternionf getLeftRotation() {return left_rotation;}
    public void setRightRotation(Quaternionf q) {right_rotation=q;}
    public Quaternionf getRightRotation() {return right_rotation;}
    public Transformation getTransformation() {return transformation;}
    public void setGlowColor(Color c) {glow_color=c;}
    public Color getGlowColor() {return glow_color;}
    public void setRepetition(Vector3f v) {repetition=v;}
    public Vector3f getRepetition() {return repetition;}
    public void setShift(Vector3f v) {shift=v;}
    public Vector3f getShift() {return shift;}
    public void setInterpolationDuration(Float f) {interpolation_duration=f;}
    public Float getInterpolationDuration() {return interpolation_duration;}
    public void setInterpolationStart(Float f) {start_interpolation=f;}
    public Float getInterpolationStart() {return start_interpolation;}
    public void setBlockBrightness(int k) {block_brightness=k;}
    public int getTeleportationDuration() {return teleportation_duration;}
    public int getViewRange() {return view_range;}
    public int getShadowRadius() {return shadow_radius;}
    public int getSkyBrightness() {return sky_brightness;}
    public int getShadowStrength() {return shadow_strength;}
    public int getBlockBrightness() {return block_brightness;}
    public String getCustomName() {return custom_name;}
    public void setCustomName(String s) {custom_name=s;}
    public Float[] getTransformList() {return allValues;}
    public static void setListValue(int i,Float f) {
        if(i%4==3|i>=19) {allValues[i % allValues.length]= (float) Math.floor(f);}
        else allValues[i % allValues.length]=f;
    }
    public static Vector3f v3fromString(String str, String name) {
        int[] indexes={0,0,0,0};
        indexes[0]=str.indexOf("[",str.indexOf(name))+1;
        indexes[1]=str.indexOf(",",indexes[0]);
        indexes[2]=str.indexOf(",",indexes[1]);
        indexes[3]=str.indexOf("]",indexes[2]);
        float[] flo = {0,0,0};
        flo[0]= Float.parseFloat(str.substring(indexes[0],indexes[1]));
        flo[1]= Float.parseFloat(str.substring(indexes[1],indexes[2]));
        flo[2]= Float.parseFloat(str.substring(indexes[2],indexes[3]));
        return new Vector3f(flo[0],flo[1],flo[2]);
    }
    public static Quaternionf qtfromString(String str, String name) {
        int[] indexes={0,0,0,0,0};
        indexes[0]=str.indexOf("[",str.indexOf(name))+1;
        indexes[1]=str.indexOf(",",indexes[0]);
        indexes[2]=str.indexOf(",",indexes[1]);
        indexes[3]=str.indexOf(",",indexes[2]);
        indexes[4]=str.indexOf("]",indexes[3]);
        float[] flo = {0,0,0,0};
        flo[0]= Float.parseFloat(str.substring(indexes[0],indexes[1]));
        flo[1]= Float.parseFloat(str.substring(indexes[1],indexes[2]));
        flo[2]= Float.parseFloat(str.substring(indexes[2],indexes[3]));
        flo[3]= Float.parseFloat(str.substring(indexes[3],indexes[4]));
        return new Quaternionf(flo[0],flo[1],flo[2],flo[3]);
    }
    public static String quaternionToTag(Quaternionf q) {
        return "["+q.x+","+q.y+","+q.z+","+q.w+"]";
    }
    public static String vector3ToTag(Vector3f v) {
        return "["+v.x+","+v.y+","+v.z+"]";
    }
    public void readFromEntity(Display display) {
        CompoundTag compoundTag=new CompoundTag();
        compoundTag=display.saveWithoutId(compoundTag);
        readFromNBT(compoundTag);
    }
    public Quaternionf getRotation() {
        return left_rotation.mul(right_rotation);
    }
    public CompoundTag writeToNBT() {
        Vector3f v0=new Vector3f(defaultList[0],defaultList[1],defaultList[2]);
        Vector3f v1=new Vector3f(defaultList[4],defaultList[5],defaultList[6]);
        Quaternionf q0=new Quaternionf(defaultList[8],defaultList[9],defaultList[10],defaultList[11]);
        Quaternionf q1=new Quaternionf(defaultList[12],defaultList[13],defaultList[14],defaultList[15]);
        Transformation t0=new Transformation(v1,q0,v0,q1);
        String s=(Objects.equals(transformation, t0) ?"{\"transformation\":{"+
                (Objects.equals(translation,v0)?"\"translation\":"+vector3ToTag(this.getTranslation()):"")+
                (Objects.equals(left_rotation,q0)?",\"left_rotation\":"+quaternionToTag(this.getLeftRotation()):"")+
                (Objects.equals(scale,v1)?",\"scale\":"+vector3ToTag(this.getScale()):"")+
                (Objects.equals(right_rotation,q1)?",\"left_rotation\":"+quaternionToTag(this.getLeftRotation()):"")+
                "},":"")+
                (glow_color.getRGB()==0?"\"glow_color_override\":"+this.getGlowColor().getRGB():"")+
                (start_interpolation==defaultList[3]?",\"start_interpolation\":"+this.getInterpolationStart():"")+
                (interpolation_duration==defaultList[7]?",\"interpolation_duration\":"+this.getInterpolationDuration():"")+
                (teleportation_duration==defaultList[19]?",\"teleport_duration\":"+this.getTeleportationDuration():"")+
                (view_range==defaultList[28]?",\"view_range\":"+this.getViewRange():"")+
                (shadow_radius==defaultList[24]?",\"shadow_radius\":"+this.getShadowRadius():"")+
                (shadow_strength==defaultList[25]?",\"shadow_strength\":"+this.getShadowStrength():"")+
                (block_brightness==defaultList[26]?",\"block_brightness\":"+this.getBlockBrightness():"")+
                (sky_brightness==defaultList[27]?",\"sky_brightness\":"+this.getSkyBrightness():"")+
                (Objects.equals(this.getBillboard(),"fixed") ?",\"billboard\":"+this.getBillboard():"")+
                (Objects.equals(custom_name,"")?",\"custom_name\":"+custom_name:"")+
                (isItemDisplay() ? ",\"item\":{":",\"block\":{"+getBlockId())+"}}";
        CompoundTag compoundTag= new CompoundTag();
        compoundTag.getCompound(s);
        return compoundTag;
    }
    public boolean isItemDisplay() {return this.getEntityType()==EntityType.ITEM_DISPLAY;}
    public CompoundTag writeAllTransformations(Display display) {
        CompoundTag compoundTag=this.writeToNBT();
        return display.saveWithoutId(compoundTag);
    }
    public static float[] defaultList=new float[] {1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,1};
    public void setList(float[] v) {for(int i=0;i<32;i++) setListValue(i,v[i]);}
    public void setTransList(Float[] v) {
        if(v.length== allValues.length) allValues=v;
    }
    public boolean getBooleanValue(int i) {
        boolean b=false;
        if(i==0) b=horizontal_billboard;
        if(i==1) b=vertical_billboard;
        if(i==2) b=customNameVisible;
        return b;
    }
    public EntityType getEntityType() {
        if(Objects.equals(getDisplayType(), "block")) return EntityType.BLOCK_DISPLAY; else return EntityType.ITEM_DISPLAY;
    }
    public void toDefaultType() {
        if(!Objects.equals(getDisplayType(), "block")) {setDisplayType("fixed");}
    }
    public void toDefault(int i) {if(i<0) setList(defaultList); else setListValue(i,defaultList[i]);}
    public CompoundTag defaultDisplayTag() {
        this.setList(defaultList);
        this.setBillboard(false,false);
        this.toDefaultType();
        return writeToNBT();
    }
    public Display.BlockDisplay summonBlockDisplay(BlockPos pos) {
        //
    }
}