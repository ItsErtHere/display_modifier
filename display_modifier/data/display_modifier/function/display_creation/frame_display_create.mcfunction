#Get Main x,y,z,r,settings,name
execute store result storage display_modifier:data $(x) int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.x 1
execute store result storage display_modifier:data $(y) int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.y 1
execute store result storage display_modifier:data $(z) int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.z 1
execute store result storage display_modifier:data $(z) int 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.r 1
execute store result storage display_modifier:data $(name) byte 1 run data get entity @s Item.components."minecraft:custom_name"
#Rel Values
$function display_modifier:display_creation/rel_display {"path":"transformations.translation[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"trans_x"}
$function display_modifier:display_creation/rel_display {"path":"transformations.translation[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"trans_y"}
$function display_modifier:display_creation/rel_display {"path":"transformations.translation[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"trans_z"}

$function display_modifier:display_creation/rel_display {"path":"transformations.scale[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"scale_x"}
$function display_modifier:display_creation/rel_display {"path":"transformations.scale[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"scale_y"}
$function display_modifier:display_creation/rel_display {"path":"transformations.scale[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"scale_z"}

$function display_modifier:display_creation/rel_display {"path":"transformations.left_rotation[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_x"}
$function display_modifier:display_creation/rel_display {"path":"transformations.left_rotation[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_y"}
$function display_modifier:display_creation/rel_display {"path":"transformations.left_rotation[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_z"}
$function display_modifier:display_creation/rel_display {"path":"transformations.left_rotation[3]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"l_rot_w"}

$function display_modifier:display_creation/rel_display {"path":"transformations.right_rotation[0]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_x"}
$function display_modifier:display_creation/rel_display {"path":"transformations.right_rotation[1]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_y"}
$function display_modifier:display_creation/rel_display {"path":"transformations.right_rotation[2]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_z"}
$function display_modifier:display_creation/rel_display {"path":"transformations.right_rotation[3]","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"r_rot_w"}

$function display_modifier:display_creation/rel_display {"path":"shadow_radius","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"shadow_r"}
$function display_modifier:display_creation/rel_display {"path":"shadow_strength","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"shadow_s"}
$function display_modifier:display_creation/rel_display {"path":"brightness.block","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"bright_b"}
$function display_modifier:display_creation/rel_display {"path":"brightness.sky","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"bright_s"}

$function display_modifier:display_creation/rel_display {"path":"start_interpolation","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"interp_s"}
$function display_modifier:display_creation/rel_display {"path":"interpolation_duration","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"interp_d"}
$function display_modifier:display_creation/rel_display {"path":"teleport_duration","x":$(x),"y":$(y),"z":$(z),"r":$(r),"name":$(name),"rel":"telep_d"}

#Frame Glow Color
execute store result score @s display_glow_red run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.0000152587890625
execute store result score @s display_glow_green run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.00390625
execute store result score @s display_glow_blue run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 1
function display_modifier:display_creation/rel_display_per_condition
#Set rgb
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$function display_modifier:display_creation/by_condition {"x":$(x),"y":$(y),"z":$(z),"name":$(name),"scoreb":"display_glow_red","rel":"glow_r"}
$function display_modifier:display_creation/by_condition {"x":$(x),"y":$(y),"z":$(z),"name":$(name),"scoreb":"display_glow_green","rel":"glow_g"}
$function display_modifier:display_creation/by_condition {"x":$(x),"y":$(y),"z":$(z),"name":$(name),"scoreb":"display_glow_blue","rel":"glow_b"}
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),z=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),z=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),z=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),dz=$(z)] run function display_modifier:glows/set_bounds
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),z=$(z)] run function display_modifier:glows/set_bounds
#If no entity exists
execute store result storage display_modifier:data $(display_settings) byte 1 run data get entity @s Item.components."minecraft:custom_data"."display_settings"
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),dx=$(x),dy=$(y),dz=$(z)] run summon item_display ~$(x) ~$(y) ~$(z) $(display_settings)
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":""}}] unless entity @e[type=item_display,name=$(name),dx=$(x),dy=$(y),z=$(z)] run summon item_display ~$(x) ~$(y) $(z) $(display_settings)
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),dx=$(x),y=$(y),dz=$(z)] run summon item_display ~$(x) $(y) ~$(z) $(display_settings)
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":""}}] unless entity @e[type=item_display,name=$(name),dx=$(x),y=$(y),z=$(z)] run summon item_display ~$(x) $(y) $(z) $(display_settings)
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),x=$(x),dy=$(y),dz=$(z)] run summon item_display $(x) ~$(y) ~$(z) $(display_settings)
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":""}}] unless entity @e[type=item_display,name=$(name),x=$(x),dy=$(y),z=$(z)] run summon item_display $(x) ~$(y) $(z) $(display_settings)
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":"~"}}] unless entity @e[type=item_display,name=$(name),x=$(x),y=$(y),dz=$(z)] run summon item_display $(x) $(y) ~$(z) $(display_settings)
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":""}}] unless entity @e[type=item_display,name=$(name),x=$(x),y=$(y),z=$(z)] run summon item_display $(x) $(y) $(z) $(display_settings)