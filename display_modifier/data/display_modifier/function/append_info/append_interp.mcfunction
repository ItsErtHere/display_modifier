execute if entity @s[scores={display_setting_axis=0,display_setting_type=6}] run function display_modifier:dmp_storage_1_scale {"path":"start_interpolation"}
execute if entity @s[scores={display_setting_axis=1,display_setting_type=6}] run function display_modifier:dmp_storage_1_scale {"path":"interpolation_duration"}
execute if entity @s[scores={display_setting_axis=2,display_setting_type=6}] run function display_modifier:dmp_storage_1_scale {"path":"teleport_duration"}
#Update Lore
item modify entity @n[type=item_frame] inventory.0 display_modifier:update_lore