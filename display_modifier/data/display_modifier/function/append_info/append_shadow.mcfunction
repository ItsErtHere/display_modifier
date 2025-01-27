scoreboard players set @s display_setting_type 4
$scoreboard players set @p display_setting_axis $(x)
$execute if entity @p[scores={display_setting_axis=0,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp_storage_1_scale {"path":"shadow_radius","a":$(a)}
$execute if entity @p[scores={display_setting_axis=1,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp_storage_1_scale {"path":"shadow_strength","a":$(a)}
$execute if entity @p[scores={display_setting_axis=2,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp_storage_1_scale {"path":"brightness.sky","a":$(a)}
$execute if entity @p[scores={display_setting_axis=3,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp_storage_1_scale {"path":"brightness.block","a":$(a)}
#Update Lore
item modify entity @n[type=item_frame] container.0 display_modifier:update_lore_frame