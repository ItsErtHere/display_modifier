execute as @p if entity @s[scores={display_setting_type=0,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."scale_x" set value ""
execute as @p if entity @s[scores={display_setting_type=0,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."scale_y" set value ""
execute as @p if entity @s[scores={display_setting_type=0,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."scale_z" set value ""

execute as @p if entity @s[scores={display_setting_type=1,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."trans_x" set value ""
execute as @p if entity @s[scores={display_setting_type=1,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."trans_y" set value ""
execute as @p if entity @s[scores={display_setting_type=1,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."trans_z" set value ""

execute as @p if entity @s[scores={display_setting_type=2,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."l_rot_x" set value ""
execute as @p if entity @s[scores={display_setting_type=2,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."l_rot_y" set value ""
execute as @p if entity @s[scores={display_setting_type=2,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."l_rot_z" set value ""
execute as @p if entity @s[scores={display_setting_type=2,display_setting_axis=3}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."l_rot_w" set value ""

execute as @p if entity @s[scores={display_setting_type=3,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."r_rot_x" set value ""
execute as @p if entity @s[scores={display_setting_type=3,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."r_rot_y" set value ""
execute as @p if entity @s[scores={display_setting_type=3,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."r_rot_z" set value ""
execute as @p if entity @s[scores={display_setting_type=3,display_setting_axis=3}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."r_rot_w" set value ""

execute as @p if entity @s[scores={display_setting_type=4,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."glow_r" set value ""
execute as @p if entity @s[scores={display_setting_type=4,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."glow_g" set value ""
execute as @p if entity @s[scores={display_setting_type=4,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."glow_b" set value ""
execute as @p if entity @s[scores={display_setting_type=4,display_setting_axis=3}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."view_r" set value ""

execute as @p if entity @s[scores={display_setting_type=5,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."shadow_r" set value ""
execute as @p if entity @s[scores={display_setting_type=5,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."shadow_s" set value ""
execute as @p if entity @s[scores={display_setting_type=5,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."bright_s" set value ""
execute as @p if entity @s[scores={display_setting_type=5,display_setting_axis=3}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."bright_b" set value ""

execute as @p if entity @s[scores={display_setting_type=6,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."interp_s" set value ""
execute as @p if entity @s[scores={display_setting_type=6,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."interp_d" set value ""
execute as @p if entity @s[scores={display_setting_type=6,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."telep_d" set value ""

execute as @p if entity @s[scores={display_setting_type=7,display_setting_axis=0}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."pos_x" set value ""
execute as @p if entity @s[scores={display_setting_type=7,display_setting_axis=1}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."pos_y" set value ""
execute as @p if entity @s[scores={display_setting_type=7,display_setting_axis=2}] run data modify entity @n[type=item_frame] Item.components."minecraft:custom_data"."rel_dir"."pos_z" set value ""
#Update Lore
item modify entity @n[type=item_frame] container.0 display_modifier:update_lore_frame