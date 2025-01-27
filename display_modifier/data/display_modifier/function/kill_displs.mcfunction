#Get Main x,y,z,r,settings,name
# Get x
execute store result score @s temp_obj run data get entity @s Pos[0] 10000
execute store result score @s[type=player] value_adder run data get entity @s Inventory[{Slot:-106b}].components."minecraft:custom_data".interaction_location.x 1
execute store result score @s[type=item_frame] value_adder run data get entity @s Item.components."minecraft:custom_data".interaction_location.x 1
scoreboard players operation @s temp_obj += @s value_adder
execute store result storage display_modifier:data $(x) int 0.0001 run scoreboard players get @s temp_obj
# Get y
execute store result score @s[type=player] temp_obj run data get entity @s Pos[1] 10000
execute store result score @s[type=player] value_adder run data get entity @s Inventory[{Slot:-106b}].components."minecraft:custom_data".interaction_location.y 1
execute store result score @s[type=item_frame] value_adder run data get entity @s Item.components."minecraft:custom_data".interaction_location.y 1
scoreboard players operation @s temp_obj += @s value_adder
execute store result storage display_modifier:data $(y) int 0.0001 run scoreboard players get @s temp_obj
# Get z
execute store result score @s[type=player] temp_obj run data get entity @s Pos[2] 10000
execute store result score @s[type=player] value_adder run data get entity @s Inventory[{Slot:-106b}].components."minecraft:custom_data".interaction_location.z 1
execute store result score @s[type=item_frame] value_adder run data get entity @s Item.components."minecraft:custom_data".interaction_location.z 1
scoreboard players operation @s temp_obj += @s value_adder
execute store result storage display_modifier:data $(z) int 0.0001 run scoreboard players get @s temp_obj
#Get r and name
execute store result storage display_modifier:data $(r) int 1 run data get entity @s[type=player] Inventory[{Slot:-106b}].components."minecraft:custom_data".interaction_location.r 1
execute store result storage display_modifier:data $(name) byte 1 run data get entity @s[type=player] Inventory[{Slot:-106b}].components."minecraft:custom_data".display_settings."custom_name" 1
execute store result storage display_modifier:data $(r) int 1 run data get entity @s[type=item_frame] Item.components."minecraft:custom_data".interaction_location.r 1
execute store result storage display_modifier:data $(name) byte 1 run data get entity @s[type=item_frame] Item.components."minecraft:custom_data".display_settings."custom_name" 1
#Kill displays
$execute if entity @s[type=item_frame] if items entity @s container.0 *[custom_name] as @s positioned $(x) $(y) $(z) run kill @e[type=item_display,distance=0..$(r),name=$(name)]
$execute if entity @s[type=player] if items entity @s weapon.mainhand *[custom_name] as @s positioned $(x) $(y) $(z) run kill @e[type=item_display,distance=0..$(r),name=$(name)]
$execute if entity @s[type=item_frame] unless items entity @s container.0 *[custom_name] as @s positioned $(x) $(y) $(z) run kill @e[type=item_display,distance=0..$(r),!name]
$execute if entity @s[type=player] unless items entity @s weapon.mainhand *[custom_name] as @s positioned $(x) $(y) $(z) run kill @e[type=item_display,distance=0..$(r),!name]