#Get Main x,y,z,r,settings,name
execute store result storage display_modifier:data x int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.x 1
execute store result storage display_modifier:data y int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.y 1
execute store result storage display_modifier:data z int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.z 1
execute store result storage display_modifier:data r int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.r 1
execute store result storage display_modifier:data display_settings byte 1 run data get entity @s Item.components."minecraft:custom_data".display_settings
execute store result storage display_modifier:data name byte 1 run data get entity @s Item.components."minecraft:custom_name"
#Rel/Dir Values
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"x_pos":false}}}}}] run function display_modifier:dmp_storage {"added_path":"Pos[0]","orig_path":"interaction_location.x"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"y_pos":false}}}}}] run function display_modifier:dmp_storage {"added_path":"Pos[1]","orig_path":"interaction_location.y"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"z_pos":false}}}}}] run function display_modifier:dmp_storage {"added_path":"Pos[2]","orig_path":"interaction_location.z"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"x_scale":true}}}}}] run function display_modifier:dmp_storage_same {"path":"scale[0]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"y_scale":true}}}}}] run function display_modifier:dmp_storage_same {"path":"scale[1]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"z_scale":true}}}}}] run function display_modifier:dmp_storage_same {"path":"scale[2]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"x_translation":true}}}}}] run function display_modifier:dmp_storage_same {"path":"translation[0]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"y_translation":true}}}}}] run function display_modifier:dmp_storage_same {"path":"translation[1]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"z_translation":true}}}}}] run function display_modifier:dmp_storage_same {"path":"translation[2]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"x_lrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"left_rotation[0]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"y_lrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"left_rotation[1]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"z_lrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"left_rotation[2]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"w_lrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"left_rotation[3]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"x_rrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"right_rotation[0]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"y_rrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"right_rotation[1]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"z_rrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"right_rotation[2]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"w_rrot":true}}}}}] run function display_modifier:dmp_storage_same {"path":"right_rotation[3]"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"r_shadow":true}}}}}] run function display_modifier:dmp_storage_1_scale {"path":"shadow_radius"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"s_shadow":true}}}}}] run function display_modifier:dmp_storage_1_scale {"path":"shadow_strength"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"s_bright":true}}}}}] run function display_modifier:dmp_storage_1_scale {"path":"brightness.sky"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"b_bright":true}}}}}] run function display_modifier:dmp_storage_1_scale {"path":"brightness.block"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"interp_s":true}}}}}] run function display_modifier:dmp_storage_1_scale {"path":"start_interpolation"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"interp_d":true}}}}}] run function display_modifier:dmp_storage_1_scale {"path":"interpolation_duration"}
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"telep_d":true}}}}}] run function display_modifier:dmp_storage_1_scale {"path":"teleport_duration"}
#Get Glow Color respective
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"glow":true}}}}}] store result score @s value_adder run data get entity @s Item.components.minecraft:custom_data.display_settings.glow_color
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"glow":true}}}}}] store result score @s temp_obj run data get storage display_modifier:data display_settings.glow_color 1
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"glow":true}}}}}] run scoreboard players operation @s temp_obj += @s value_adder
execute if entity @s[nbt={Item:{components:{"minecraft:custom_data":{"rel_dir":{"glow":true}}}}}] store result storage display_modifier:data Item.components.custom_data.display_settings.glow_color int 1 run scoreboard players get @s temp_obj
#Finalize Display Data
$data modify storage display_modifier:data display_settings append value {custom_name:$(name)}
$execute positioned $(x) $(y) $(z) unless entity @e[type=item_display,name=$(name),dx=0,dy=0,dz=0] run summon item_display ~ ~ ~ $(display_settings)
$execute positioned $(x) $(y) $(z) if entity @e[type=item_display,name=$(name),dx=0,dy=0,dz=0] run data merge entity @e[type=item_display,name=$(name),distance=0..$(r),limit=1] $(display_settings)
