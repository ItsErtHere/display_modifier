$scoreboard players set @s display_setting_axis $(x)

$execute if entity @p[scores={display_setting_axis=0,display_setting_type=1}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.translation[0]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=1,display_setting_type=1}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.translation[1]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=2,display_setting_type=1}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.translation[2]","a":$(a)}

$execute if entity @p[scores={display_setting_axis=0,display_setting_type=0}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.scale[0]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=1,display_setting_type=0}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.scale[1]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=2,display_setting_type=0}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.scale[2]","a":$(a)}

$execute if entity @p[scores={display_setting_axis=0,display_setting_type=2}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.left_rotation[0]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=1,display_setting_type=2}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.left_rotation[1]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=2,display_setting_type=2}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.left_rotation[2]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=3,display_setting_type=2}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.left_rotation[3]","a":$(a)}

$execute if entity @p[scores={display_setting_axis=0,display_setting_type=3}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.right_rotation[0]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=1,display_setting_type=3}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.right_rotation[1]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=2,display_setting_type=3}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.right_rotation[2]","a":$(a)}
$execute if entity @p[scores={display_setting_axis=3,display_setting_type=3}] as @n[type=item_frame] run function display_modifier:dmp_storage_same {"path":"transformations.right_rotation[3]","a":$(a)}

$execute if entity @p[scores={display_setting_axis=0,display_setting_type=7}] as @n[type=item_frame] run function display_modifier:dmp {"path":"interaction_location.x","data_path":"interaction_location.x","a":$(a)}
$execute if entity @p[scores={display_setting_axis=1,display_setting_type=7}] as @n[type=item_frame] run function display_modifier:dmp {"path":"interaction_location.y","data_path":"interaction_location.y","a":$(a)}
$execute if entity @p[scores={display_setting_axis=2,display_setting_type=7}] as @n[type=item_frame] run function display_modifier:dmp {"path":"interaction_location.z","data_path":"interaction_location.z","a":$(a)}
$execute if entity @p[scores={display_setting_axis=3,display_setting_type=7}] as @n[type=item_frame] run function display_modifier:dmp {"path":"interaction_location.r","data_path":"interaction_location.r","a":$(a)}
#Update Lore
item modify entity @n[type=item_frame] container.0 display_modifier:update_lore_frame