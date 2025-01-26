scoreboard players reset @s used.written_book
summon item_display ~ ~ ~ {item:{id:grass_block},transformation:{scale:[1,1,1],translation:[0,0,0],left_rotation:[0,0,0,1],right_rotation:[0,0,0,1]},Tags:[new_displ]}
execute if items entity @s[tag=displ_summoned] weapon.mainhand written_book[custom_data={"datapack":"Display Creator"}] run data modify entity @e[type=item_display,distance=0..1,tag=new_displ,limit=1] item.id set from entity @s Inventory[{Slot:-106b}].id
execute if items entity @s[tag=displ_summoned] weapon.mainhand written_book[custom_data={"datapack":"Display Creator"}] run data modify entity @e[type=item_display,distance=0..1,tag=new_displ,limit=1] item.components set from entity @s Inventory[{Slot:-106b}].components
scoreboard players operation @s player_id = @e[type=item_display,tag=new_displ,distance=0..1] player_id
execute if items entity @s[tag=displ_summoned] weapon.mainhand written_book[custom_data={"datapack":"Display Creator"}] as @e[type=item_display,tag=new_displ] if score @s player_id = @p player_id run tag @s remove new_displ
execute if items entity @s[tag=displ_summoned] weapon.mainhand written_book[custom_data={"datapack":"Display Creator"}] as @p run tag @s add displ_summoned
execute as @s store result storage display_modifier:data display_settings.glow_color[0] int 0.0000152587890625 run data get entity @s weapon.components."minecraft:custom_data".display_settings.glow_color
execute as @s store result storage display_modifier:data display_settings.glow_color[1] int 0.00390625 run data get entity @s weapon.components."minecraft:custom_data".display_settings.glow_color
execute as @s store result storage display_modifier:data display_settings.glow_color[2] int 1 run data get entity @s weapon.components."minecraft:custom_data".display_settings.glow_color
execute as @s store result score @s display_glow_red run data get storage display_modifier:data display_settings.glow_color[0] 1
execute as @s store result score @s display_glow_green run data get storage display_modifier:data display_settings.glow_color[1] 256
execute as @s store result score @s display_glow_blue run data get storage display_modifier:data display_settings.glow_color[2] 1
scoreboard players operation @s display_glow_blue -= @s display_glow_green
execute as @s store result score @s display_glow_green run data get storage display_modifier:data display_settings.glow_color[1] 1
scoreboard players operation @s display_glow_green -= @s display_glow_red