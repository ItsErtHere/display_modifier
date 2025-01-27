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
#Rel Values
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.translation[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"trans_x"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.translation[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"trans_y"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.translation[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"trans_z"}

$function display_modifier:display_creation/rel_display_pl {"path":"transformations.scale[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"scale_x"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.scale[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"scale_y"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.scale[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"scale_z"}

$function display_modifier:display_creation/rel_display_pl {"path":"transformations.left_rotation[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_x"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.left_rotation[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_y"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.left_rotation[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_z"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.left_rotation[3]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_w"}

$function display_modifier:display_creation/rel_display_pl {"path":"transformations.right_rotation[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_x"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.right_rotation[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_y"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.right_rotation[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_z"}
$function display_modifier:display_creation/rel_display_pl {"path":"transformations.right_rotation[3]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_w"}

$function display_modifier:display_creation/rel_display_pl {"path":"shadow_radius","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"shadow_r"}
$function display_modifier:display_creation/rel_display_pl {"path":"shadow_strength","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"shadow_s"}
$function display_modifier:display_creation/rel_display_pl {"path":"brightness.block","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"bright_b"}
$function display_modifier:display_creation/rel_display_pl {"path":"brightness.sky","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"bright_s"}

$function display_modifier:display_creation/rel_display_pl {"path":"start_interpolation","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"interp_s"}
$function display_modifier:display_creation/rel_display_pl {"path":"interpolation_duration","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"interp_d"}
$function display_modifier:display_creation/rel_display_pl {"path":"teleport_duration","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"telep_d"}

#Frame Glow Color
execute store result score @s display_glow_red run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.0000152587890625
execute store result score @s display_glow_green run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.00390625
execute store result score @s display_glow_blue run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 1
function display_modifier:display_creation/rel_display_per_condition
#Set rgb
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$function display_modifier:display_creation/by_condition {"x":$(x),"y":$(y),"z":$(z),"name":$(name),"scoreb":"display_glow_red","rel":"glow_r"}
$function display_modifier:display_creation/by_condition {"x":$(x),"y":$(y),"z":$(z),"name":$(name),"scoreb":"display_glow_green","rel":"glow_g"}
$function display_modifier:display_creation/by_condition {"x":$(x),"y":$(y),"z":$(z),"name":$(name),"scoreb":"display_glow_blue","rel":"glow_b"}
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),z=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),z=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),z=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),z=$(z)] run function display_modifier:glows/set_bounds
#If no entity exists
execute store result storage display_modifier:data $(display_settings) byte 1 run data get entity @s[type=item_frame] Item.components."minecraft:custom_data"."display_settings"
execute store result storage display_modifier:data $(display_settings) byte 1 run data get entity @s[type=player] Inventory[{Slot:-106b}].components."minecraft:custom_data".display_settings
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),dx=$(x),dy=$(y),dz=$(z)] run summon item_display ~$(x) ~$(y) ~$(z) $(display_settings)
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":""}}] unless entity @e[type=item_display,name=$(name),dx=$(x),dy=$(y),z=$(z)] run summon item_display ~$(x) ~$(y) $(z) $(display_settings)
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),dx=$(x),y=$(y),dz=$(z)] run summon item_display ~$(x) $(y) ~$(z) $(display_settings)
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":""}}] unless entity @e[type=item_display,name=$(name),dx=$(x),y=$(y),z=$(z)] run summon item_display ~$(x) $(y) $(z) $(display_settings)
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),x=$(x),dy=$(y),dz=$(z)] run summon item_display $(x) ~$(y) ~$(z) $(display_settings)
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":""}}] unless entity @e[type=item_display,name=$(name),x=$(x),dy=$(y),z=$(z)] run summon item_display $(x) ~$(y) $(z) $(display_settings)
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),x=$(x),y=$(y),dz=$(z)] run summon item_display $(x) $(y) ~$(z) $(display_settings)
$execute if items entity @s weapon.offhand *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":""}}] unless entity @e[type=item_display,name=$(name),x=$(x),y=$(y),z=$(z)] run summon item_display $(x) $(y) $(z) $(display_settings)