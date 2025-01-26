execute store result storage display_modifier:data x int 1 run scoreboard players get @s display_loc_x
execute store result storage display_modifier:data y int 1 run scoreboard players get @s display_loc_y
execute store result storage display_modifier:data z int 1 run scoreboard players get @s display_loc_z
execute as @s if items entity @s container.* egg run tag @s add for_egg
execute as @s if items entity @s container.* egg run clear @s egg 1
$summon interaction ~$(x) ~$(y) ~$(z) {NoGravity:true,Tags:[from_player]}
execute at @e[type=interaction,tag=from_player,distance=..1] as @e[type=item_display,distance=..1] run data modify entity @e[type=interaction,tag=from_player,distance=..1,limit=1] Passengers append from entity @s
$execute positioned ~$(x) ~$(y) ~$(z) as @e[type=interaction,tag=from_player,distance=..1] run data modify storage spawnegg mobNBT set from entity @s
$give @s bee_spawn_egg[entity_data=$(mobNBT),enchantment_glint_override=true,custom_data={is_displ:true}]
$execute positioned ~$(x) ~$(y) ~$(z) as @e[type=interaction,tag=from_player,distance=..1] run kill @e[type=item_display,distance=..1]
$execute positioned ~$(x) ~$(y) ~$(z) as @e[type=interaction,tag=from_player,distance=..1] run kill @s