#Get Main x,y,z,r,settings,name
#Get x
execute as @s store result score #a temp_obj run data get entity @s Pos[0] 10000
execute as @s[type=player] store result score #a value_adder run data get entity @s SelectedItem.components."minecraft:custom_data".interaction_location.x 10000
execute as @s[type=item_frame] store result score #a value_adder run data get entity @s Item.components."minecraft:custom_data".interaction_location.x 10000
scoreboard players operation #a temp_obj += #a value_adder
execute store result storage display_modifier:data info.x float 0.0001 run scoreboard players get #a temp_obj
#Get y
execute as @s store result score #a temp_obj run data get entity @s Pos[1] 10000
execute as @s[type=player] store result score #a value_adder run data get entity @s SelectedItem.components."minecraft:custom_data".interaction_location.y 10000
execute as @s[type=item_frame] store result score #a value_adder run data get entity @s Item.components."minecraft:custom_data".interaction_location.y 10000
scoreboard players operation #a temp_obj += #a value_adder
execute store result storage display_modifier:data info.y float 0.0001 run scoreboard players get #a temp_obj
#Get z
execute as @s store result score #a temp_obj run data get entity @s Pos[2] 10000
execute as @s[type=player] store result score #a value_adder run data get entity @s SelectedItem.components."minecraft:custom_data".interaction_location.r 10000
execute as @s[type=item_frame] store result score #a value_adder run data get entity @s Item.components."minecraft:custom_data".interaction_location.r 10000
scoreboard players operation #a temp_obj += #a value_adder
execute store result storage display_modifier:data info.z float 0.0001 run scoreboard players get #a temp_obj
#Get r, name, billboard/display types
execute as @s[type=player] store result storage display_modifier:data info.r float 1 run data get entity @s SelectedItem.components."minecraft:custom_data".interaction_location.r 10000
execute as @s[type=item_frame] store result storage display_modifier:data info.r float 1 run data get entity @s Item.components."minecraft:custom_data".interaction_location.r 10000

execute as @s[type=player] store result storage display_modifier:data info.name byte 1 run data get entity @s SelectedItem.components."minecraft:custom_name" 1
execute as @s[type=item_frame] store result storage display_modifier:data info.name byte 1 run data get entity @s Item.components."minecraft:custom_name" 1

execute as @s[type=player] store result storage display_modifier:data info.billboard byte 1 run data get entity @s SelectedItem.components."minecraft:custom_data".display_settings.billboard 1
execute as @s[type=item_frame] store result storage display_modifier:data info.billboard byte 1 run data get entity @s Item.components."minecraft:custom_data".display_settings.billboard 1

execute as @s[type=player] store result storage display_modifier:data info.type byte 1 run data get entity @s SelectedItem.components."minecraft:custom_data".display_settings.display_type 1
execute as @s[type=item_frame] store result storage display_modifier:data info.type byte 1 run data get entity @s Item.components."minecraft:custom_data".display_settings.display_type 1
#Get Translation values
function display_modifier:display_creation/get_data_type {"path":"transformations.translation[0]","varname":"trans_x"}
function display_modifier:display_creation/get_data_type {"path":"transformations.translation[1]","varname":"trans_y"}
function display_modifier:display_creation/get_data_type {"path":"transformations.translation[2]","varname":"trans_z"}

function display_modifier:display_creation/get_data_type {"path":"transformations.scale[0]","varname":"scale_x"}
function display_modifier:display_creation/get_data_type {"path":"transformations.scale[1]","varname":"scale_y"}
function display_modifier:display_creation/get_data_type {"path":"transformations.scale[2]","varname":"scale_z"}

function display_modifier:display_creation/get_data_type {"path":"transformations.left_rotation[0]","varname":"l_rot_x"}
function display_modifier:display_creation/get_data_type {"path":"transformations.left_rotation[1]","varname":"l_rot_y"}
function display_modifier:display_creation/get_data_type {"path":"transformations.left_rotation[2]","varname":"l_rot_z"}
function display_modifier:display_creation/get_data_type {"path":"transformations.left_rotation[3]","varname":"l_rot_w"}

function display_modifier:display_creation/get_data_type {"path":"transformations.right_rotation[0]","varname":"r_rot_x"}
function display_modifier:display_creation/get_data_type {"path":"transformations.right_rotation[1]","varname":"r_rot_y"}
function display_modifier:display_creation/get_data_type {"path":"transformations.right_rotation[2]","varname":"r_rot_z"}
function display_modifier:display_creation/get_data_type {"path":"transformations.right_rotation[3]","varname":"r_rot_w"}

function display_modifier:display_creation/get_data_type_int {"path":"shadow_radius","varname":"shad_r"}
function display_modifier:display_creation/get_data_type_int {"path":"shadow_strength","varname":"shadow_strength"}
function display_modifier:display_creation/get_data_type_int {"path":"brightness.block","varname":"bright_b"}
function display_modifier:display_creation/get_data_type_int {"path":"brightness.sky","varname":"bright_s"}

function display_modifier:display_creation/get_data_type_int {"path":"start_interpolation","varname":"interp_s"}
function display_modifier:display_creation/get_data_type_int {"path":"interpolation_duration","varname":"interp_d"}
function display_modifier:display_creation/get_data_type_int {"path":"teleport_duration","varname":"telep_d"}

execute as @s[type=player] store result storage display_modifier:data info.$(varname) int 1 run data get entity @s SelectedItem.components."minecraft:custom_data".display_settings.glow_color_override 1
execute as @s[type=item_frame] store result storage display_modifier:data info.$(varname) int 1 run data get entity @s Item.components."minecraft:custom_data".display_settings.glow_color_override 1
scoreboard players set @s temp_obj 256
execute store result score @s display_glow_red run data get storage display_modifier:data info.glow_color_override 0.0000152587890625
execute store result score @s display_glow_green run data get storage display_modifier:data info.glow_color_override 0.00390625
execute store result score @s display_glow_blue run data get storage display_modifier:data info.glow_color_override 1
function display_modifier:display_creation/add_glows
 
execute store result storage display_modifier:data info.displ_tag byte 1 run data get entity @s[type=item_frame] Item.components."minecraft:custom_data".display_settings
execute store result storage display_modifier:data info.displ_tag byte 1 run data get entity @s[type=player] SelectedItem.components."minecraft:custom_data".display_settings
function display_modifier:display_creation/summon_no_other with storage display_modifier:data info