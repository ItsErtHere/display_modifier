$execute store result score @s value_adder run data get entity @s Item.components.minecraft:custom_data.display_settings.$(path) 1
$scoreboard players set @s temp_obj $(a)
scoreboard players operation @s temp_obj += @s value_adder
$execute store result entity @n[type=item_frame] Item.components.minecraft:custom_data.display_settings.$(path) int 1 run scoreboard players get @s temp_obj