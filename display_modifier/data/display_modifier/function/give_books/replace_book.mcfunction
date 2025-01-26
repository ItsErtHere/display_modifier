execute as @a if items entity @s container.* minecraft:written_book[minecraft:written_book_content~{title:"Display Creator"},!custom_data] run tag @s add to_craft
execute as @a[tag=to_craft] run clear @s minecraft:written_book[minecraft:written_book_content~{title:"Display Creator"},!custom_data] 1
execute as @a[tag=to_craft] run loot give @s loot display_modifier:modif_book
tag @a[tag=to_craft] remove to_craft