data modify storage spawnegg mobNBT set from entity @s
$summon item ~ ~ ~ {Item:{id:bee_spawn_egg,components:{entity_data:$(mobNBT),enchantment_glint_override:true,custom_data:{is_displ:true}}}}
kill @e[type=item_display,distance=..1]
kill @s