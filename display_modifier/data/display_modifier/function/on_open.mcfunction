scoreboard players reset @s used.written_book
execute as @s store result score @s display_glow_red run data get entity @s weapon.components."minecraft:custom_data".display_settings.glow_color 0.0000152587890625
execute as @s store result score @s display_glow_green run data get entity @s weapon.components."minecraft:custom_data".display_settings.glow_color 0.00390625
execute as @s store result score @s display_glow_blue run data get entity @s weapon.components."minecraft:custom_data".display_settings.glow_color 1
scoreboard players set @s temp_obj 256
scoreboard players operation @s display_glow_red *= @s temp_obj
scoreboard players operation @s display_glow_green -= @s display_glow_red
scoreboard players operation @s display_glow_green *= @s temp_obj
scoreboard players operation @s display_glow_blue -= @s display_glow_green
scoreboard players operation @s display_glow_green /= @s temp_obj
scoreboard players operation @s display_glow_red /= @s temp_obj