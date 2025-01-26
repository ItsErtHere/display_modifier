execute store result score @s value_adder run data get storage display_modifier:data $(a) 10000
$execute store result score @s temp_obj run data get entity $(uuid) Item.components.minecraft:custom_data.$(data_path) 10000
scoreboard players operation @s temp_obj += @s value_adder
$execute store result entity $(uuid) Item.components.minecraft:custom_data.$(data_path) float 0.0001 run scoreboard players get @s temp_obj