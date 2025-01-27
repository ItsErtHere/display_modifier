$execute if score @s display_setting_axis matches 0 run function display_modifier:dmp_storage_1_scale {"path":"start_interpolation","a":$(a)}
$execute if score @s display_setting_axis matches 1 run function display_modifier:dmp_storage_1_scale {"path":"interpolation_duration","a":$(a)}
$execute if score @s display_setting_axis matches 2 run function display_modifier:dmp_storage_1_scale {"path":"teleport_duration","a":$(a)}
#Update Lore
item modify entity @n[type=item_frame] container.0 display_modifier:update_lore_frame