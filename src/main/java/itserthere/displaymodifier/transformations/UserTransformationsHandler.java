package itserthere.displaymodifier.transformations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import itserthere.displaymodifier.Reference;
import itserthere.displaymodifier.platform.Services;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserTransformationsHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final File PRESET_FOLDER = new File(Services.PLATFORM.getUserPresetFolder().toFile() + "/armorposer");
    public static final File PRESET_FILE = new File(PRESET_FOLDER, "User_poses.json");

    public static void initializePresets() {
        if (!PRESET_FOLDER.exists() || !PRESET_FILE.exists()) {
            PRESET_FOLDER.mkdirs();

            UserTransformations userPresets = new UserTransformations(Reference.userTransformations);
            try (FileWriter writer = new FileWriter(PRESET_FILE)) {
                GSON.toJson(userPresets, writer);
                writer.flush();
            } catch (IOException e) {
                Reference.LOGGER.error("Failed to user presets {}", e.getMessage());
            }
        }
    }

    public static void saveUserPoses() {
        if (!PRESET_FOLDER.exists()) {
            PRESET_FOLDER.mkdirs();
        }

        UserTransformations userPresets = new UserTransformations(Reference.userTransformations);
        try (FileWriter writer = new FileWriter(PRESET_FILE)) {
            GSON.toJson(userPresets, writer);
            writer.flush();
        } catch (IOException e) {
            Reference.LOGGER.error("Failed to user presets {}", e.getMessage());
        }
    }

    public static void loadUserPoses() {
        if (!PRESET_FOLDER.exists() || !PRESET_FILE.exists()) {
            initializePresets();
        }

        Reference.userTransformations.clear();
        String fileName = PRESET_FILE.getName();
        try (FileReader json = new FileReader(PRESET_FILE)) {
            final UserTransformations userPoses = GSON.fromJson(json, UserTransformations.class);
            if (userPoses != null) {
                Reference.userTransformations.addAll(userPoses.userTransformations());
            } else {
                Reference.LOGGER.error("Could not load user poses from {}.", fileName);
            }
        } catch (final Exception e) {
            Reference.LOGGER.error("Unable to load file {}. Please make sure it's a valid json.", fileName);
            Reference.LOGGER.trace("Exception: ", e);
        }
    }
}
