#Frame Glow Color
execute store result score @s display_glow_red run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.0000152587890625
execute store result score @s display_glow_green run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 0.00390625
execute store result score @s display_glow_blue run data get entity @s Item.components."minecraft:custom_data"."display_settings".glow_color_override 1
function display_modifier:display_creation/rel_display_per_condition
#Set each rgb
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),dy=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"~","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),dx=$(x),y=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"~","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),dy=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":"~"}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),dz=$(z)] run function display_modifier:display_creation/rel_display_per_condition
$execute if items entity @s container.0 *[custom_data~{"rel_dir":{"pos_x":"","pos_y":"","pos_z":""}}] run execute as @e[type=item_display,name=$(name),x=$(x),y=$(y),z=$(z)] run function display_modifier:display_creation/rel_display_per_condition