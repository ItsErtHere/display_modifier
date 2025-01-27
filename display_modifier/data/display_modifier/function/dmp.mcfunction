$execute store result score @s value_adder run data get entity @s Item.components."minecraft:custom_data".$(path) 10000
$scoreboard players set @s temp_obj $(a)
scoreboard players operation @s temp_obj += @s value_adder
$execute store result entity @s Item.components.minecraft:custom_data.$(data_path) float 0.0001 run scoreboard players get @s temp_obj