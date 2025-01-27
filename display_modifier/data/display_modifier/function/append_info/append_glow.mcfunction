scoreboard players set @s display_setting_type 4
$scoreboard players set @s display_setting_axis $(x)
#Set current scoreboards
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
#Add to rgb by (a)
$scoreboard players set @s temp_obj $(a)
execute if score @s display_setting_axis matches 0 run scoreboard players operation @s display_glow_red += @s temp_obj
execute if score @s display_setting_axis matches 1 run scoreboard players operation @s display_glow_green += @s temp_obj
execute if score @s display_setting_axis matches 2 run scoreboard players operation @s display_glow_blue += @s temp_obj
$execute if score @s display_setting_axis matches 3 run function display_modifier:dmp_storage_1_scale {"a":$(a),"path":"view_range"}
#Set range of scoreboards
scoreboard players set @s temp_obj 256
execute if score @s display_glow_red >= @s temp_obj run scoreboard players set @s display_glow_red 255
execute if score @s display_glow_green >= @s temp_obj run scoreboard players set @s display_glow_green 255
execute if score @s display_glow_blue >= @s temp_obj run scoreboard players set @s display_glow_blue 255
scoreboard players set @s temp_obj 0
execute if score @s display_glow_red < @s temp_obj run scoreboard players set @s display_glow_red 0
execute if score @s display_glow_green < @s temp_obj run scoreboard players set @s display_glow_green 0
execute if score @s display_glow_blue < @s temp_obj run scoreboard players set @s display_glow_blue 0
#Add to frame
scoreboard players set @s value_adder 0
scoreboard players set @s temp_obj 65536
scoreboard players operation @s display_glow_red *= @s temp_obj
scoreboard players operation @s value_adder += @s display_glow_red
scoreboard players operation @s display_glow_red /= @s temp_obj
scoreboard players set @s temp_obj 256
scoreboard players operation @s display_glow_green *= @s temp_obj
scoreboard players operation @s value_adder += @s display_glow_green
scoreboard players operation @s display_glow_green /= @s temp_obj
scoreboard players operation @s value_adder += @s display_glow_blue
execute store result entity @n[type=item_frame] Item.components."minecraft:custom_data"."display_settings"."glow_color_override" int 1 run scoreboard players get @s value_adder
#Update Lore
item modify entity @n[type=item_frame] container.0 display_modifier:update_lore_frame
tellraw @s [{"text":"Set glow color to: "},{"score": {"name": "@s","objective": "display_glow_red"},"color": "red"},{"text":", "},{"score": {"name": "@s","objective": "display_glow_green"},"color": "green"},{"text":", "},{"score": {"name": "@s","objective": "display_glow_blue"},"color": "blue"}]