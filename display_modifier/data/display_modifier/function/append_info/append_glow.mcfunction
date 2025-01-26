#Set current scoreboards
execute store result score @s display_glow_red run data get entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings".glow_color 0.0000152587890625
execute store result score @s display_glow_green run data get entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings".glow_color 0.00390625
execute store result score @s display_glow_blue run data get entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings".glow_color 1
scoreboard players set @s temp_obj 256
scoreboard players operation @s display_glow_green *= @s temp_obj
scoreboard players operation @s display_glow_blue -= @s display_glow_green
scoreboard players operation @s display_glow_green /= @s temp_obj
scoreboard players operation @s display_glow_red *= @s temp_obj
scoreboard players operation @s display_glow_green -= @s display_glow_red
scoreboard players operation @s display_glow_red /= @s temp_obj
#Add to rgb by (a)
$execute as @s[scores={display_setting_axis=0},tag=displ_summoned] run scoreboard players operation @s display_glow_red += $(a) display_glow_red
$execute as @s[scores={display_setting_axis=1},tag=displ_summoned] run scoreboard players operation @s display_glow_green += $(a) display_glow_green
$execute as @s[scores={display_setting_axis=2},tag=displ_summoned] run scoreboard players operation @s display_glow_blue += $(a) display_glow_blue
#Set range of scoreboards
execute as @s[scores={display_setting_axis=0,display_glow_red=..-1},tag=displ_summoned] run scoreboard players set @s display_glow_red 0
execute as @s[scores={display_setting_axis=0,display_glow_green=..-1},tag=displ_summoned] run scoreboard players set @s display_glow_green 0
execute as @s[scores={display_setting_axis=0,display_glow_blue=..-1},tag=displ_summoned] run scoreboard players set @s display_glow_blue 0
execute as @s[scores={display_setting_axis=0,display_glow_red=256..},tag=displ_summoned] run scoreboard players set @s display_glow_red 255
execute as @s[scores={display_setting_axis=0,display_glow_green=256..},tag=displ_summoned] run scoreboard players set @s display_glow_green 255
execute as @s[scores={display_setting_axis=0,display_glow_blue=256..},tag=displ_summoned] run scoreboard players set @s display_glow_blue 255
#Add to frame
execute store result entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings"."glow_color" int 65536 run scoreboard players get @s display_glow_red
execute store result entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings"."glow_color" int 256 run scoreboard players get @s display_glow_green
execute store result entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings"."glow_color" int 1 run scoreboard players get @s display_glow_blue
#Update Lore
item modify entity @n[type=item_frame] inventory.0 display_modifier:update_lore