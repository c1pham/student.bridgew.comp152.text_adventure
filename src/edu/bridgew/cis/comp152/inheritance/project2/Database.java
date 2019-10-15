package edu.bridgew.cis.comp152.inheritance.project2;

import edu.bridgew.cis.comp152.inheritance.project2.ability.Ability;
import edu.bridgew.cis.comp152.inheritance.project2.ability.ArmorUp;
import edu.bridgew.cis.comp152.inheritance.project2.ability.BulletStorm;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Camoflage;
import edu.bridgew.cis.comp152.inheritance.project2.ability.ChargedBlast;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Cleanse;
import edu.bridgew.cis.comp152.inheritance.project2.ability.ElementalBarrage;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Enrage;
import edu.bridgew.cis.comp152.inheritance.project2.ability.FinalStorm;
import edu.bridgew.cis.comp152.inheritance.project2.ability.FireArrow;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Fireball;
import edu.bridgew.cis.comp152.inheritance.project2.ability.MachineUpgrade;
import edu.bridgew.cis.comp152.inheritance.project2.ability.MagicalArrowBarrage;
import edu.bridgew.cis.comp152.inheritance.project2.ability.MagicalThorns;
import edu.bridgew.cis.comp152.inheritance.project2.ability.MassLevels;
import edu.bridgew.cis.comp152.inheritance.project2.ability.MechaRoar;
import edu.bridgew.cis.comp152.inheritance.project2.ability.MechanicalReproduction;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Restore;
import edu.bridgew.cis.comp152.inheritance.project2.ability.SmackDown;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Taunt;
import edu.bridgew.cis.comp152.inheritance.project2.ability.ThunderBolt;


// commented
public class Database {
	// enemy types
	public static enum enemies { ARCHER, WARRIOR, DROID, MECHA_ANIMAL, DARK_ELF, SHIRE_ELF, TITANIUM_HYDRA};
	// name of stats
	public static enum statName { ATTACK, DEFENSE, MAGICAL_DEFENSE, SPEED, HEALTH, ACCURACY,
		CRITICAL_DAMAGE, CRITICAL_RATE, SHIELD_OR_ENERGY_DMG};
		// types of players
		public static enum playerType {WARRIOR, ARCHER, GUNNER};
		// types of elements
		public static enum element {FIRE, ICE, LIGHTING, NORMAL};
		// resistence for each element
		public static enum Resistance {FIRE_RESIST, ICE_RESIST, LIGHTING_RESIST};
		// name for each sword
		public static enum swordType {HERO_BLADE, SHARP_STICK, SHARK_STICK, EXECUTIONER_BLADE, BAMBOO_STICK,
			ELVEN_SWORD, METEOR_BLADE, GUARDIAN_BLADE, TWIG};
			// name for each sword
			public static enum bowType {LONG_BOW, POWER_BOW, ELVEN_BOW, OLD_BOW, GUARDIAN_BOW};
			// name for each gun
			public static enum gunType {ION_CANNON, PLASMATA_RIFLE, COCONUT_LAUNCHER, WATER_GUN, MISSLE_LAUNCHER, REVOLVER,
				GUARDIAN_REVOLVER, ELVEN_SHOTGUN};
				// name for each club
				public static enum clubType {TREE_BRANCH, ROBOT_ARM, ELVEN_CLUB, FIREWOOD, BIG_TUNA,
					DEER_BONE, ROBOT_LEG, BRONZE_LEG, IRON_MACE};
					// name of each ability
					public static enum ability{FIRE_ARROW, ENRAGE, RESTORE, ELEMENTAL_BARRAGE,
						CAMOFLAGE, CHARGED_BLAST, MACHINE_UPGRADE, MECHA_ROAR, MAGICAL_THORNS,
						MAGICAL_ARROW_BARRAGE, ARMOR_UP, THUNDER_BOLT, FIRE_BALL, TAUNT,
						FINAL_STORM, SMACK_DOWN, MECHANICAL_REPRODUCTION, MASS_LEVEL, COMBUSTION,
						CLEANSE, BULLET_STORM}
					// stats for abilities
					public static enum abilityStat{REQUIRE_STAMINA, ABILITY_STAT}

					public static enum roomNames {BEACHSHORE, WRECKAGE, BEACH_VILLAGE, BATTLEFIELD, MOUNTAIN,
						ARCHER_WAR_CAMP, MAIN_TENT, FIELD , PINEWOOD_FOREST, ELVEN_FOREST, SHIRE_WOODS, FRONT_LINES,
						GOLDEN_ARMY_STRONGHOLD}
					// name for each container
					public static enum containers {CHEST, POCKET, BOX, CABINET, CRATE, ABANDON_HUT,
						PILE_OF_COCONUTS, TREE_TRUNK, BAG, WELL, SCRAP_METAL, BARREL, FIREPIT,
						RACK, TOMB, STONE, BUSH, DEAD_DEER, POND, TRENCH, STATUE, HYDRA_HUSK}

					public static enum directions {NORTH, SOUTH, EAST, WEST}

					// if the container needs to be open before searching
					public static boolean[] containerNeedOpen = {
							// chest
							true,
							//  pocket
							true,
							// box
							true,
							// cabinet
							true,
							// crate
							false,
							// abandon hut
							false,
							// pile of coconuts
							false,
							// tree trunk
							false,
							// bag
							true,
							// well
							false,
							// scrap
							false,
							// barrel
							true,
							//firebit
							false,
							// weapon rack
							false,
							// tomb 
							true,
							// stone
							false,
							// bush
							false,
							// deer
							true,
							// pond
							false,
							//trench
							false,
							// statue
							false,
							// husk
							false
					};

					// the description for each container
					public static String[] containerDescript = {
							// chest description
							" There is a big chest to your right.",
							// dead body description
							" There is a dead body on the ground with a leather jacket. You notice it has a nice gold buttoned pocket.",
							// box description
							" There is a wooden box to the corner of your eye.",
							// cabinet description
							" There is a broken cabinet amongst the debris.",
							// crate description
							" You see a opened crate that says 'SUPPLIES' on side.",
							// abandon hut
							" You see a abandon hut next to you.",
							// pile of coconuts
							" There is a pile of coconuts next to the abandoned hut.",
							// hollow trunk
							" You see a tree truck to your left. It look's like it's been decaying for some time.",
							// bag
							" You see a dead archer with a bag on him.",
							// well
							" There is a well to your left.",
							// scrap metal
							" You see the scrap metal leftover from the battle.",
							// barrel
							" You see a big barrel with a lid.",
							// fire pit
							" There is a firepit that seems like it was recently used.",
							// weapon rack
							" You see a rack in the corner. It's hard to see what's on it.",
							" You see a tomb with golden engraving on it. It says Grand Elf Morpheous: The Strongest of the Shire Elves.",
							// stone 
							" The stone is carved and looks uncomfortable to sit on.",
							// bush
							" You see large bush in the forest.",
							// deer
							" You see a dead deer on the ground. It's been decaying for some time.",
							// pond
							" You see a pond with water that looks dirty. You can't see through it.",
							// trench
							" You see a trench. You can hear movement in them.",
							// statue
							" You see one of the statues are made of bronze.",
							// husk
							" You see the Mother Hydra's husk. You see wires and sparks everywhere coming out of the hydra husk."
					};


					// these strings will be interpreted and turn into a item
					public static String[][] containerItem = {
							// chest items "
							{"Large Health Potion", "Long Bow", "Medium Stamina Potion"},
							// pocket item
							{"Small Stamina Potion", "Pernament Health Potion"},
							// box items
							{"Executioner Blade"},
							// broken cabinet items
							{"Power Bow"},
							// crate item
							{"Medium Health Potion", "Small Health Potion", "Small Stamina Potion" ,"Small Stamina Potion", "Super Health Potion"},
							// abandon hut
							{"Sharp Stick", "Bamboo Stick", "Small Stamina Potion"},
							// pile of coconuts
							{"Coconut Launcher", "Pernament Health Potion"},
							// tree trunk
							{"Sharp Stick", "Tree Branch"},
							// bag
							{"Large Health Potion", "Large Health Potion", "Large Health Potion"},
							// well
							{"Water Gun", "Small Health Potion", "Pernament Health Potion"},
							// scrap metal 
							{"Robot Arm", "Large Stamina Potion"},
							// barrel
							{ "Big Tuna"},
							// firepit
							{"Firewood"},
							// rack
							{ "Elven Club", "Elven Bow", "Hero Blade", "Elven Sword", "Elven Shotgun"},
							// tomb 
							{"Guardian Blade", "Pernament Health Potion", "Large Stamina Potion", "Super Health Potion", 
								"Super Health Potion", "Guardian Bow", "Guardian Revolver"},
							// stone 
							{"Large Health Potion", "Pernament Health Potion", "Super Health Potion"},
							// bush
							{"Twig", "Large Stamina Potion"},
							// dead bear
							{"Deer Bone"},
							// pond
							{"Big Tuna"},
							// trench
							{"Medium Health Potion", "Small Health Potion", "Small Stamina Potion" ,"Medium Stamina Potion", "Sharp Stick", "Elven Sword", "Robot Leg",
								"Large Temporary Health Potion", "Large Temporary Health Potion", "Large Temporary Health Potion", "Large Temporary Health Potion",
							"Super Health Potion"},
							// statue
							{"Bronze Leg", "Pernament Health Potion", "Pernament Health Potion", "Super Health Potion"},
							// husk
							{"Missle Launcher", "Meteor Blade"}
					};

					// this contains names for the containers for each room then it will turn into containers
					public static String[][] roomContainers = {
							//beachshore 
							{"Pocket", "Box"},
							//wreckage
							{"Chest", "Crate", "Cabinet"},
							// beach village
							{"Abandon Hut", "Pile Of Coconuts"},
							// battlefield
							{"Tree Trunk", "Bag"},
							// mountain
							{"Well", "Scrap Metal"},
							// archer camp 
							{"Crate", "Well", "Barrel", "Firepit"},
							// main tent
							{"Chest", "Rack"},
							// field 
							{ "Stone", "Tomb", "Tree Trunk", "Firepit", "Well", "Chest"},
							// pinewoodforest
							{"Tree Trunk", "Bush", "Dead Deer"},
							// elven woods
							{"Tree Trunk", "Bush", "Well", "Scrap Metal", "Abandon Hut"},
							// shire woods
							{"Scrap Metal", "Firepit", "Bag", "Pond"},
							// frontlines
							{"Scrap Metal", "Bag", "Barrel", "Trench", "Crate"},
							// golden army stronghold
							{"Scrap Metal", "Statue", "Chest", "Hydra Husk"}
					};

					// the description for each room 
					public static String[] roomDescript = {
							//beachshore 
							"\nYou are on the beach. The crash site is to your South. To the North there is the big wooden wall. To " + 
							"the West is a village of locals. East is the ocean.",
							// wreckage
							"\nYou are at the ship wreck. You see debris everywhere. To your North is the shore, to your South, East, and West is just the ocean.",
							// beach village 
							"\nYou are in the beach village. There are houses around but it seems the locals are afraid of you and locked the doors. "
							+ "To your South and West is just water. To the East is the beachshore."
							+ " To the North is a big wooden wall with a vent in the corner. It looks like water is coming out of it.",
							// battlefield
							"\nYou are on the battlefield and see dead bodies everywhere. "
							+ "You see the vent to your South. To the West is the mountains. To your North is a forest. To your East is the forest,",
							// mountain 
							"\nYou are on the mountain. To the North is the forest. To the South you noticed the mountain is too steep to climb."
							+ " To the East is the battlefield. You see the war camp to the West. It's closed of on all sides except at the front gate."
							+ " You notice the mountain has been altered by the battle and that there are no animals here.",
							// archer camp
							"\nYou are in the war camp. To the South and North are steep mountain walls. It seems the war camp is hidden from view by the mountains."
							+ " To the East you see the area of the mountain you came from. To the West you see the a big tent that must be the general's tent.",
							// main tent
							"To the East is the entrance you came. You see to the West the field. The tent around you is massive. There are battle plans all over the place.",
							// field
							"You are in a field. To the West, South, and North, are the mountain wall. To the East is the Main Tent. You see Maximus sitting on a stone.",
							// pinewood forest
							"\n You are in the Pinewood Forest. To your North and West is the ocean. To your South is the mountains. To your East is Elven Forest. You see Pinewood Trees and deers everywhere. ",
							// elven woods
							"\n You are in the Elven Forest and the forest is burning down. To your North is a cliff closed off by a fence. To your South is the battelfield. To your West is the Pinewood Forest. To your East is the frontlines."
							+ " You shadows of elves moving across the treetops. They must be getting ready for battle.",
							// shire woods 
							"\nTo your South is a big wooden wall. To your East is the ocean. To your West is the battlefield. To your North is the frontlines."
							+ " There are dead archers and shire elves all over the place."
							+ "  The enemy has destroyed much of the forest.",
							// front lines
							"\nTo your North is the Golden Armie's Stronghold. You notice it's gates are made of dwarven steel."
							+ " To your South is Shire Woods. To your East is a cliff closed off by a fence. To your West is Elven Woods."
							+ " You see bodies of archers and mechas, elves, and warriors all around.",
							// golden 
							" To the North South and East is the ocean. To the South is the frontlines. You notice the debris from the battle everywhere. Statures are shattered."

					};

					// this is cutscene for entering the room, the array will be run through and be the cutscene
					public static String[][] cutscene = {
							// beach cutscene
							{"You shipwrecked on shore. Nearby warriors come and start killing all the wreckage survivors.",
								"They spot you and raise their weapons.", "You look all around you and realize you can't run away from them.",
								"You decide that battling them is the only way out.", "You see a weapon on the ground and grab it to defend yourself. "},
							// wreckage
							{},
							// beach village 
							{"You notice a local and ask about the warriors.", "YOU: Why were the warriors killing the survivors?",
								"LOCAL: They must of thought you guys were archer reinforcements. They have been blockading the shore for months.", 
								"YOU: Why! We are just lowly fishermen.", "LOCAL: HA! You didn't look that way with how you fought.", "YOU: (SILENCE)",
								"You are surrounded by thieves!" ,"Scene End"},
							// battle field
							{"You approach the end of the vent and hear screaming." , "You peak through the vent cover's holes. " , "ARCHERS: FALL BACK!" ,
									"STRANGE FIGURE: ELEMENTAL BARRAGE!" ,
									"ARCHERS: UGHH! NO!" , "ARCHER 3: FIRE ARROW ATTACK!" , "'Clunk'" , "ARCHER 3: HE'S TOO STRONG!",
									"STRANGE FIGURE: You will remember the name." , "ARCHER 3: Please don't!" , 
									"You wait until the strange figure leaves then after a while you break out the vent.",
									"You see the an archer injured on the ground.",
									"YOU: What happened to you? Who was that?",
									"ARCHER 3: The general of the Golden Army ambushed us. We couldn't run away.",
									"ARCHER 3: Head West to the mountains. The Archer general is there with the High Elves of the Shire.",
									"ARCHER 3: Tell them that they've done. They tamed the Mother Hydra.",
									"You're not alone.", "You see dark elves and warriors running towards you."},
							// mountain 
							{"You see something shiny moving in the distance." , "YOU: What is that?" , "'Hiss noises'",
							"You see something that looks like shiny men and animals coming towards you."},
							// archer camp 
							{"You enter the war camp and ask for the archer general." , "ARCHER 1: Why are you here?", "YOU: I have come to deliver a message.",
								"ARCHER 1: No one sees our leader that easily.", "YOU: It's urgent one of your soldiers told me to tell him.",
								"ARCHER 1: Well what is it?", "YOU: They have tamed the Mother Hydra!" , "ARCHER 1: Goodness ! "
										+ "Go east to the main tent he'll be there. I'll send word."},
							// main tent
							{"High Elf Zeref: Why have you come?", "YOU: I want to end this war! Bring me to the Mother Hydra!", 
											"High Elf Meredith: HA! It can't be done it's too powerful. LEAVE US!",
											"YOU: Believe me!", "Archer General Maximus: Let him speak your majesties.", "YOU: I've taken out every warrior, dark elf,"
													+ " and mecha that has come my way. I can do this.",
													"Maximus: We'll test that. If you can't even beat me then you won't even make it pass the general of the Golden Army."
															+ " Whom you said tamed the Mother Hydra.",
															"Maximus: Defeat the high elves then I will let you battle me.", "YOU: Where are you going?",
							"Maximus: If you have the courage go fight me in the field but I doubt you'll get pass Zeref and Meredith."},
							// field
							{"Maximus: I see you have come.", "YOU: Why wouldn't I? I will defeat you then the Mother Hydra!", "Maximus: Enough talk! BATTLE ME!"},
							// pinewood
							{"A band of thieves have come to rob you." , "THIEF 1: Pretty sword you got there.", "YOU: You can't have it.", "THIEF 2: We'll just take it them ha! Ha!"},
							// elven woods
							{"You hear rustling up onto the trees.", "Dark Elves are mobilizing their forces.", "Some spot you and jump off the trees."},
							// shire woods
							{"You see a massacre and the trees are burning." , "Dark Elves, Mechas, and Warriors have overrun the place.", "They spot you and run towards you for battle."},
							// frontlines
							{"The General: Don't flee cowards!" , "The General kills six archers with his Elemental Barrage." 
								, "YOU: I won't flee." , "The General: Who are you?" , "YOU: You will remember my name.", "The General: We'll see about that! ARGHHH!"},
							// stronghold 
							{"Ground shakes.", "'Hiss'", "Mother Hydra: SCAAAARRRRR!"}
					};

					// this is the cutscene for after the battle
					public static String[][] cutscene2 = {
							// beach
							{},
							// wreckage
							{},
							// beach village
							{},
							// battlefield
							{ "You come to back to the archer he's bleeding out.",
								"ARCHER 4: If they maintain control of the Mother Hydra none of us are going to stand a chance",
								"YOU: Why? could it really be that powerful?"
								, "ARCHER 4: Legend says it's a mec.....", "Archer 4 has passed away."},
							// mountain
							{},
							// archer camp 
							{},
							// main tent
							{"Zeref: You are stronger than we thought.", "Meredith: He won't make it pass Maximus he's slained thousands."},
							//
							{"Maximus: I have not a battle in a thousand years.", "Maximus: In order to beat the Titanium you will need the a weapon of the Guardian's."
								, "Maximus: Legend says that it was made of a meteor and it one of the only few weapons forged that can cut the Titanium Hydra's armored skin."},
							// pinewood forest
							{},
							// elven wood
							{},
							// shire woods
							{},
							// front lines
							{"The General: You think you won? The Titanium Hydra will bow to no one.", "The General: She has awoken from her slumber and will destroy this land without me controlling her."},
							// stronghold
							{"Titanium Hydra mantle's cracks.", "Titanium Hydra begins to malfunction .... and explodes ", "Congratulations Champion the Titanium Hydra is defeated!"
								, "You have beaten D-Legendz"}
					};

					// this corelates to north south east west, this is if something needs to open in that direction
					public static boolean[][] roomNeedOpen = {
							//beachshore 
							{false, false, false, false},
							// wreckage
							{false, false, false, false},
							// beach village 
							{true, false, false, false},
							// battlefield
							{false, false, false, false},
							// mountain 
							{false, false, false, true},
							// archer camp 
							{false, false, false, false},
							// main tent
							{false, false, false, false},
							// field
							{false, false, false, false},
							// pinewood forest
							{false, false, false, false},
							// elven wood
							{false, false, false, false},
							// shire woods
							{false, false, false, false},
							// frontlines
							{true, false, false, false},
							// stronghold
							{false, false, false, false}
					};
					// this is the string someone would need to type in to open something to go to the next room
					public static String[][] roomOpenString = {
							// beachshore
							{null, null, null, null},
							// wreckage
							{null, null, null, null},
							// beach village
							{"vent", null, null, null},
							// battlefield
							{null, null, null, null},
							// mountain 
							{null, null, null, "gate"},
							// archer camp
							{null, null, null, null},
							// main tent
							{null, null, null, null},
							// field
							{null, null, null, null},
							// pinewood forest
							{null, null, null, null},
							// elven wood
							{null, null, null, null},
							// shire wood
							{null, null, null, null},
							// frontlines
							{"gate", null, null, null},
							// stronghold
							{null, null, null, null},

					};

					// strings that will be interpreted and turn into enemies through  the enemy factory
					public static String[][] roomEnemies = {
							// beach shore
							{ "warrior@2", "warrior@3", "warrior@2", "warrior@3"},
							// wreckage
							{"warrior@5"},
							// beach village
							{"thief@5", "thief@5", "thiefw@5"},
							// battlefield
							{"warrior@6", "warrior@6", "dark elf@6", "dark elf@6"},
							// mountain
							{"droid@6", "droid@6", "mecha animal@6", "mecha animalf@6"},
							// archer camp 
							{},
							// main tent
							{"high elf zeref@12", "high elf meredith@12"},
							// field 
							{"maximus@40"},
							// pinewood forest
							{"thief@30", "thief@30", "thief@30"},
							// elven woods
							{"dark elf@30", "droid@30", "mecha animalf@30" },
							// shire woods
							{"dark elf@15", "dark elf@15", "droid@15", "mecha animalf@15", "warrior@35", "warrior@35"},
							// frontline
							{"mecha animal@30", "general@30", "dark elf@40"},
							// stronghold
							{"hydra@60"}
					};

					// stats for each ability 
					public static double[][] abilityStatD = {
							// require stat for fire arrow
							{30, 2.6},
							// require stat for enrage
							{50, 2},
							// require stats for restore
							{30, .3},
							// require stat for elemental barrage 
							{50, .7},
							// require stat for camoflage
							{50 , .3},
							// require stat for chargedblast
							{40, 1.1},
							// require stat for machine upgrade
							{100, 5},
							// require stat mecha roar
							{60, .3},
							// require stats for magical thorns
							{30, 1.5},
							// require stats for elemental arrow barrage
							{60, .6},
							// require stats for armor up
							{20, 2},
							// require stats for thunderbolt
							{30, 1},
							// require stats for fireball
							{20, 1},
							// taunt stats
							{30 , .1},
							// final storm
							{100, 1},
							// smackdown
							{40, 2.5},
							// mechanical reproduction
							{55, 4},
							//mass level
							{0, 2},
							// combustion
							{70, 8},
							// cleanse 
							{30, 0},
							// bullet storm
							{50, 1}
					};
					// weapon for player according to class
					public static String[] playerClassWeapon = {
							// weapon for player if I set them to be like a warrior
							"Sharp Stick",
							// weapon for archer warrior 
							"Old Bow",
							// weapon for gunner
							"Revolver"
					};

					// weapon for each enemy
					public static String[] enemyWeapons = {
							// archer weapon
							"Long Bow" ,
							// warrior weapon
							"Robot Leg",
							// droid weapon
							"Plasmata Rifle",
							// mecha animal weapon
							"Ion Cannon",
							// dark elf weapon
							"Power Bow",
							// north shire elf weapon
							"Elven Bow",
							// titanium Hydra
							"Missle Launcher"
					};
					// these are weapons enemy can randomly spawn with 
					public static String[] RandomizeWeaponStrings = {
							"Long Bow", "Shark Stick", "Sharp Stick", "Power Bow", "Tree Branch", "Twig"
					};
					// these are items that can randomly spawn with
					public static String[] RandomizePotionStrings = {
							"Small Health Potion", "Medium Health Potion", "Temporary Health Potion",
							"Small Stamina Potion", "Small Pernament Health Potion",
							"Medium Stamina Potion", "Large Stamina Potion",
							"Medium Pernament Health Potion"
					};
					// these are skills player can randomly spawn with
					public static Ability[][] playerAbilities = {
							// warrior ability
							{new Restore(), new ElementalBarrage(), new ArmorUp(), new Enrage(), new SmackDown(), new Cleanse()},
							// archer ability
							{new Restore(), new Camoflage(), new FireArrow(), new FinalStorm(), new Cleanse()},
							// gunner ability
							{new Restore(), new Camoflage(), new ChargedBlast(), new Cleanse(), new BulletStorm()}
					};
					// these are base stats for if player is set up as warrior
					public static double[][] playerBaseStats = {
							// this is for player when I set them to warrior
							{20, 5, 5, 50, 700, .7f, 100, .5f},
							// player stats as archer
							{10, 5, 5, 80, 550, .7f, 200, .5f},
							// player stats gunner
							{15, 5, 5, 70, 400, .7f, 300, .5f}
					};
					// these are stats player levels up with if a player
					public static double[][] playerLevelUpStat = {
							// warrior
							{15, 10, 5, 10, 50, .03f, 10, .05f},
							// archer
							{10, 5, 5, 20, 40, .04f, 20, .08f},
							// gunner
							{10, 5, 5, 15, 30, .05f, 30, .10f}
					};

					//ATTACK, DEFENSE, MAGICAL_DEFENSE, SPEED, HEALTH, ACCURACY,
					//CRITICAL_DAMAGE, CRITICAL_RATE, SHIELD_OR_ENERGY_DMG
					// stats for them to level up
					public static double[][] enemylevelUpStat = {
							//archer stats
							{15 ,15 ,5 ,30 ,10, .05f, 10, .02f},  
							//warrior stats
							{15, 15, 5, 10, 50, .05f, 10, .05f}, 
							//droid stats
							{15 ,15 ,10 ,5 , 20, .05f, 20, .01f, 10}, 
							//mecha animal stats
							{20 ,15 ,10, 20, 20, .05, 20, .01f, 15},
							// dark elf stats
							{15 ,15 ,50, 20, 20, .05, 20, .01f, .5},
							// shire elf stats
							{15 ,15 ,50, 20, 20, .05, 20, .01f, .25},
							// titanium hydra
							{10 ,10 ,10 ,10 , 10, .01, 10, .01, 5}, 
					};
					// enemy base stats
					public static double[][] enemyBaseStats = {
							//archer stats
							{15,10,50, 100 ,200, .7f, 20, .3f},  
							//warrior stats
							{20,20,5,20,400, .7f, 20, .1f}, 
							//droid stats
							{25,20,0,20,300, .7f, 20, .1f, 10}, 
							//mecha animal stats
							{30,20,0,50,200, .5f, 20, .1f, 10},
							// dark elf stats
							{30,20,100 ,50, 100, .5f, 20, .1f, 1},
							// shire elf
							{30,20,100 ,50, 150, .5f, 20, .1f, 0},
							// titanium hydra
							{600,600,600 , 600, 10000, .7f, 100, .5f, 100}	
					};
					// the resistence for each type of entity
					public static double[][] elementTypeResistance = {
							// element resist for fire type
							{50, 25, 0}, 
							// element resist for ice type
							{25, 50, 0},
							// element resist for lighting type
							{50, 50, 100},
							// element resist for normal type
							{33, 33, 33}
					};
					// skills for each enemy
					public static Ability[][] enemySkills = {
							// archer skills
							{new Camoflage(), new FireArrow()},
							// warrior skill
							{new Enrage(), new Taunt(), new SmackDown()},
							// droid skill
							{new MachineUpgrade(), new ChargedBlast()},
							// mecha animal skill
							{new ChargedBlast(), new MechaRoar()},
							// dark elf skills
							{new MagicalArrowBarrage(), new MagicalThorns(), new ThunderBolt(), new ChargedBlast()},
							// shire elf skills 
							{ new MagicalArrowBarrage(), new Camoflage(), new Fireball(),
								new MagicalThorns(),new ThunderBolt(), new ElementalBarrage()},
							// titanium hydra skill
							{new MechanicalReproduction(), new MassLevels()}
					};
					// swprd stats name
					public static enum swordStat {DAMAGE, ATTACK_CHANGE, CRIT_DMG_CHANGE, SPEED_INCREASE, OPTIONAL_DEFENSE,
						OPTIONAL_HEALTH};
						// sword stats
						public static double[][] swordStatsVar = {
								// Hero's Blade stats
								{60,60,60, 10},
								// Sharp Stick stats
								{1,1,1, 20},
								// Shark Stick stats
								{20,20,20, 10},
								// executioner blade stats
								{25,25,25, 15},
								// bamboo sttick stats
								{5 , 5, 5, 15},
								// elven sword stats
								{40 , 40, 40, 40},
								// meteor blade
								{500, 500, 500, 10},
								// guardian blade
								{200, 500, 500, 10, 2200, 5000},
								// twig stats
								{.5,.5,.5, 50}

						};
						// gun stats enum
						public static enum gunStats {DAMAGE, ATTACK_CHANGE, CRIT_DMG_CHANGE, SCOPE_AC, OPTIONAL_DEFENSE,
							OPTIONAL_HEALTH};
							// gun stats
							public static double[][] gunStatsVar = {
									// ion cannon stat
									{30, 10, 10, .2f},
									// plasmata rifle
									{15, 15, 15, .5f},
									// coconut launcher
									{30, 35, 35, .1f},
									// water gun
									{20, 25, 25, .3f},
									// missle launcher
									{700, 200, 200, .5f},
									// revolver
									{10,10,10, .3f},
									// guardian revolver 
									{200, 500, 1000, 1, 2300, 3500},
									// elven shotgun
									{50, 55, 55, .4f}
							};
							// bow stats enum
							public static enum bowStats {DAMAGE, ATTACK_CHANGE, CRIT_DMG_CHANGE, CRIT_RATE_CHANGE, OPTIONAL_DEFENSE,
								OPTIONAL_HEALTH};
								// bow stats
								public static double[][] bowStatsVar = {
										// long bow
										{40,40,40, .1f},
										// power bow stat
										{20,20,20, .3f},
										// elven bow stats
										{50,50,50, .4f},
										// old bow stats
										{10,10,10, .3f},
										// guardian bow
										{750, 500, 750, 1, 2300, 3500}

								};
								// club stat enum
								public static enum clubStat {DAMAGE, ATTACK_CHANGE, CRIT_DMG_CHANGE, SPEED_DECREASE};
								// club stats
								public static double[][] clubStatsVar = {
										// tree trunk stats
										{30,30,30, -5},
										// robot arm stats
										{50,30,30, -5},
										// elven club stats
										{50,30,30, -1},
										// firewood stats
										{25,25,25, -10},
										// tuna
										{50, 0 , 0 , -1},
										// bone
										{55, 0 , 0 , -10},
										// robot leg
										{55, 10 , 10 , -20},
										// bronze leg
										{30, 40 , 40 , -25},
										// iron mace
										{40, 40 , 40 , -25}
								};

}
