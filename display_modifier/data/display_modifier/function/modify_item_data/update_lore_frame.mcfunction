execute store result score @s display_glow_red run data get entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.0000152587890625
execute store result score @s display_glow_green run data get entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.00390625
execute store result score @s display_glow_blue run data get entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings".glow_color_override 1
scoreboard players set @s temp_obj 256
scoreboard players operation @s display_glow_green *= @s temp_obj
scoreboard players operation @s display_glow_blue -= @s display_glow_green
scoreboard players operation @s display_glow_green /= @s temp_obj
scoreboard players operation @s display_glow_red *= @s temp_obj
scoreboard players operation @s display_glow_green -= @s display_glow_red
scoreboard players operation @s display_glow_red /= @s temp_obj

item modify entity @n[type=item_frame] container.0 display_modifier:update_lore_frame