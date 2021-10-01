package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.terrains.*;
import game.actors.LordOfCinder;
import game.actors.Player;
import game.actors.Skeleton;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());


			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery(),new Vendor(),new Bonfire(Status.LIGHTED,"Firelink Shrine's Bonfire"));

//"Firelink Shrine's Bonfire"
			// width = 80, height = 26
			List<String> profaneCapital = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++.c....",
					"........................................................................++......",
					".......c.................................................................+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#_______#...............c......+++............",
					"...........++.....................#__FB___#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++..........c...........+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++.................................................c.........+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");

			GameMap gameMap = new GameMap(groundFactory, profaneCapital);
			world.addGameMap(gameMap);


			FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery(),new Vendor(),new Bonfire("Anor Londo’s Bonfire"));
//"Anor Londo"
			List<String> anorLondo = Arrays.asList(
					"..++++++..+++..........................__.........###..........................",
					"........+++++........................___...B...###.................+++++.......",
					"...........+++.......................___.....###.....................+++++.c...",
					".....................................___#####..................c........++.....",
					".......c.............................___.................................+++...",
					"....................c................___..................................+++..",
					".....................................___.......................................",
					".....................................___.....................++++..............",
					".....................................___.....................+++++++...........",
					"..................................###___###...................+++..............",
					"...............................####_______####............c......+++...........",
					"...........++.....................___F_____.......................+............",
					".........+++...................####_______####.....................++..........",
					"............+++...................####_####..........................+.........",
					"..............+......................#.#.............................++........",
					"..............++.....................#.#.......................................",
					"............+++................................................................",
					"+................................+.............................................",
					"+++..............................+.......+++...................................",
					"++++.......++++.................++......++.....................................",
					"#####___#####++++..............+.......+.......................................",
					"_..._....._._#.++................+.....+.......................................",
					"...+.__..+...#+++..............................................................",
					"...+.....+._.#.+.....+++++...++................................................",
					"___.......___#.++++++++++++++.+++..............................................");


			GameMap gameMap2 = new GameMap(groundFactory2,anorLondo);
			world.addGameMap(gameMap2);

			Actor player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));


			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));
			//as the requirement said - manually place several Skeletons
			gameMap.at(38,4).addActor(new Skeleton("Skeleton",38,4));
			gameMap.at(28,17).addActor(new Skeleton("Skeleton",28,17));
			gameMap.at(70,25).addActor(new Skeleton("Skeleton",70,25));
			gameMap.at(55,15).addActor(new Skeleton("Skeleton",55,15));
			gameMap.at(0,0).addActor(new Skeleton("Skeleton",0,0));
			gameMap.at(68,3).addActor(new Skeleton("Skeleton",86,3));

			Location PortalLocation1 = gameMap.at(38,25);
			Location PortalLocation2 = gameMap2.at(38,0);
			PortalLocation1.setGround(new FogDoor(PortalLocation2));
			PortalLocation2.setGround(new FogDoor(PortalLocation1));





			world.run();

	}
}
