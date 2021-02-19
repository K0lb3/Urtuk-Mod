# Urtuk-Mod
 This repo is a mod of the game [Urtuk - The Desolation](https://store.steampowered.com/app/1181830/Urtuk_The_Desolation/), which unlocks everything and adds 4 new starting campaign factions:
 * Scavengers
 * Cultists
 * Grand Bosses
 * All (all units in the game)

![Start Campaign Screen](https://media.discordapp.net/attachments/522938372600365066/812123010735013908/unknown.png?width=1191&height=670)

This is achieved by simply loading the game as a library and extending various internal classes,
which can be spied via Java decompilers like [Java Decompiler](http://java-decompiler.github.io/) or the internal one of IDEA, which was used to build this little project/mod.

## how to use

1. download UrtokMod.jar from [Releases](releases) or build it yourself
2. drop it into the directory of Urtuk - The Desolation
3. execute UrtokMod.jar 

## known issues
* returning to the main Screen from crashes the game (internal screens link to the internal main screen and not the modified one -> all of them have to be edited to fix this)
