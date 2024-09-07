package itserthere.displaymodifier.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Display.TextDisplay;

import java.awt.*;

public class TextDisplayData {
    public static int line_width=1;
    public static Color background_color=new Color(255,255,255,255);
    public static int text_opacity = 255;
    public static boolean shadow=false;public static boolean see_through=false;
    public static boolean default_background=false;
    public static byte style_flags_id;
    public static String alignment;
    public static String text;public static String custom_name;
    private static byte loadFlag(byte b, CompoundTag compoundTag, String string, byte c) {
        return compoundTag.getBoolean("string") ? (byte)(b | c) : b;
    }
    public int[] intList = new int[] {background_color.getRed(),
            background_color.getGreen(), background_color.getBlue(), background_color.getAlpha(),
    line_width,text_opacity};
    public boolean[] boolList = new boolean[] {shadow,see_through,default_background};
    public void getTextDisplayData(TextDisplay display) {
        CompoundTag compoundTag=display.saveWithoutId(new CompoundTag());
        this.readFromNBT(compoundTag);
    }
    public void readFromNBT(CompoundTag compoundTag) {
        line_width=compoundTag.getInt("line_width");
        text_opacity=compoundTag.getInt("text_opacity");
        background_color=new Color(compoundTag.getInt("background"));
        shadow= compoundTag.getBoolean("shadow");
        see_through=compoundTag.getBoolean("see_through");
        default_background=compoundTag.getBoolean("default_background");
        style_flags_id=compoundTag.getByte("style_flags");
        text=compoundTag.getString("text");custom_name=compoundTag.getString("CustomName");
        alignment=compoundTag.getString("alignment");
    }
    public int[] getIntList() {return intList;}
    public void setIntListComponent(int k,int i) {intList[i]=k;}
    public boolean[] getBoolList() {return boolList;}
    public void setBoolListComponent(boolean b,int i) {boolList[i]=b;}
    public CompoundTag createTag() {
        CompoundTag compoundTag=new CompoundTag();
        compoundTag.putInt("line_width",line_width);
        compoundTag.putInt("text_opacity",text_opacity);
        compoundTag.putInt("background",background_color.getRGB());
        compoundTag.putBoolean("shadow",shadow);
        compoundTag.putBoolean("see_through",see_through);
        compoundTag.putBoolean("default_background",default_background);
        compoundTag.putString("text",text); compoundTag.putString("CustomName",custom_name);
        compoundTag.putString("alignment",alignment);
        return compoundTag;
    }
    public void setTextDisplayData(TextDisplay display) {
        display.saveWithoutId(this.createTag());
    }
    public void setText(String string) {text=string;}
    public boolean getBooleanValue(int i) {return boolList[i];}
    public String getText() {return text;}
    public void setCustomName(String string) {custom_name=string;}
    public String getCustomName() {return custom_name;}
    public void setLineWidth(int i) {line_width=i;}
    public int getLineWidth() {return line_width;}
    public int getTextOpacity() {return text_opacity;}
    public void setTextOpacity(int i) {text_opacity=i;}
    public Color getBackgroundColor() {return background_color;}
    public void setBackgroundColor(Color c) {background_color=c;}
    public String getStringValue(int i) {
        String s="";
        if(i==1) s=getText();
        else if(i==2) s=getCustomName();
        return s;
    }
    public void setStringValue(String s,int i) {
        if(i==1) text=s;
        else if(i==2) custom_name=s;
    }
}