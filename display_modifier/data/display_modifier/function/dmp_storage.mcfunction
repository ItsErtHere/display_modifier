$execute store result score @s value_adder run data get entity @s Item.components.custom_data.display_settings.$(added_path)
$execute store result score @s temp_obj run data get storage display_modifier:data $(orig_path) 10000
scoreboard players operation @s temp_obj += @s value_adder
$execute store result storage display_modifier:data $(orig_path) float 0.0001 run scoreboard players get @s temp_obj