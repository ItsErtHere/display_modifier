execute store result score @s[type=item_frame] display_glow_red run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.0000152587890625
execute store result score @s[type=item_frame] display_glow_green run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.00390625
execute store result score @s[type=item_frame] display_glow_blue run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 1
execute store result score @s[type=player] display_glow_red run data get entity @s Inventory[{Slot:-106b}].components."minecraft:custom_data"."display_settings".glow_color_override 0.0000152587890625
execute store result score @s[type=player] display_glow_green run data get entity @s Inventory[{Slot:-106b}].components."minecraft:custom_data"."display_settings".glow_color_override 0.00390625
execute store result score @s[type=player] display_glow_blue run data get entity @s Inventory[{Slot:-106b}].components."minecraft:custom_data"."display_settings".glow_color_override 1
execute store result score @s[type=item_display] display_glow_red run data get entity @s glow_color_override 0.0000152587890625
execute store result score @s[type=item_display] display_glow_green run data get entity @s glow_color_override 0.00390625
execute store result score @s[type=item_display] display_glow_blue run data get entity @s glow_color_override 1
scoreboard players set @s player_id 256
scoreboard players operation @s display_glow_green *= @s player_id
scoreboard players operation @s display_glow_blue -= @s display_glow_green
scoreboard players operation @s display_glow_green /= @s player_id
scoreboard players operation @s display_glow_red *= @s player_id
scoreboard players operation @s display_glow_green -= @s display_glow_red
scoreboard players operation @s display_glow_red /= @s player_id