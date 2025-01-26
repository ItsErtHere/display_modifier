$execute store result score @s value_adder run data get entity @n[type=item_frame] Item.components."minecraft:custom_data".display_settings.$(path) 10000
$scoreboard players set @s temp_obj $(a)
scoreboard players operation @s temp_obj += @s value_adder
$execute store result entity @n[type=item_frame] Item.components.minecraft:custom_data.display_settings.$(path) float 0.0001 run scoreboard players get @s temp_obj