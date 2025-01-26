execute store result storage display_modifier:data $(uuid) byte 1 run data get entity @s weapon.components."minecraft:custom_data"."uuid" 1
execute at @a[scores={display_setting_axis=0,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp_storage_1_scale {data_path:"shadow_radius",a:1}
execute at @a[scores={display_setting_axis=1,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp {data_path:"shadow_strength",a:1}
execute at @a[scores={display_setting_axis=2,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp {data_path:"brightness.sky",a:1}
execute at @a[scores={display_setting_axis=3,display_setting_type=4}] as @n[type=item_frame] run function display_modifier:dmp {data_path:"brightness.block",a:1}
#Update Lore
item modify entity @n[type=item_frame] inventory.0 display_modifier:update_lore