#Set range of scoreboards
scoreboard players set @s temp_obj 256
execute if score @s display_glow_red >= @s temp_obj run scoreboard players set @s display_glow_red 255
execute if score @s display_glow_green >= @s temp_obj run scoreboard players set @s display_glow_green 255
execute if score @s display_glow_blue >= @s temp_obj run scoreboard players set @s display_glow_blue 255
scoreboard players set @s temp_obj 0
execute if score @s display_glow_red < @s temp_obj run scoreboard players set @s display_glow_red 0
execute if score @s display_glow_green < @s temp_obj run scoreboard players set @s display_glow_green 0
execute if score @s display_glow_blue < @s temp_obj run scoreboard players set @s display_glow_blue 0
#Add to frame
scoreboard players set @s value_adder 0
scoreboard players set @s temp_obj 65536
scoreboard players operation @s display_glow_red *= @s temp_obj
scoreboard players operation @s value_adder += @s display_glow_red
scoreboard players operation @s display_glow_red /= @s temp_obj
scoreboard players set @s temp_obj 256
scoreboard players operation @s display_glow_green *= @s temp_obj
scoreboard players operation @s value_adder += @s display_glow_green
scoreboard players operation @s display_glow_green /= @s temp_obj
scoreboard players operation @s value_adder += @s display_glow_blue
execute store result entity @s glow_color_override int 1 run scoreboard players get @s value_adder