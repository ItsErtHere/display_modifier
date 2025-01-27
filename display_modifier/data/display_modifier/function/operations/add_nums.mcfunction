$execute as @s store result score @s temp_obj run data get entity @s $(path_1) 10000
$execute as @s store result score @s value_adder run data get entity @s $(path_2) 10000
scoreboard players operation @s temp_obj += @s value_adder
return run scoreboard players get @s temp_obj