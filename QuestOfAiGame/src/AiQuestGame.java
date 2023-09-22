import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AiQuestGame {
	static int playerLives = 3;
	static boolean gadgetsAvailable = true;
	static boolean laptopAvailable = true;
	static char victoryHints[] = { 'n', 'n', 's', 'e', 'n', 'n', 'n', 'n', 'n' };
	static int deNearbyCr[] = { 2, 2, 3, 5, 6, 6, 7, 8, 9 };
	static int crCoordinates[][] = { { 8, 0 }, { 10, 0 }, { 2, 4 }, { 4, 8 }, { 7, 8 }, { 9, 8 }, { 4, 10 }, { 2, 10 },
			{ 0, 6 } };
	static char crDefaultDir[] = { 'w', 's', 'e', 'e', 's', 's', 'e', 'n', 'w' };
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		String crStatements[] = { "On South, there is a Retina scan,and On East, there is an Open route",
				"On North, there is an Open route, and On South, there is a route locked by a Fingerprint Scanner",
				"On East, there is a route locked by High Frequency rays, and On South, there is an Open route",
				"On South, there is a Retina scan, and On East, there is an Open route",
				"On North, there is a massive Door locked by a super complex algorithm, and On East, there is a route locked by High Frequency rays" };

		String deStatements[] = {
				"Oh No!, Your wicked Eye did not matched, and the Scanner turned flames burnt your eyes. So, you can't go further. "
						+ "You are eliminated",
				"Mayday!, You have been vapourized to Death by high energy Gamma rays",
				"Oh Shit!, You have been crushed to death by a Mega Robot",
				"Oh No!, You are in midst of a High Electric field. Enjoying Body Massage;)!! You are losing your breaths. "
						+ "Mayday..Mayday.., you lost your last breath",
				"Oh No!, Your wicked Eye did not matched, and the Scanner turned flames burnt your eyes. So, you can't go further. "
						+ "You are eliminated",
				"Oh Shit!, You have stepped on a sensor activating a Crusher. Enjoy being crushed. RIP!",
				"Watch out! You are at end of the Cliff! Damn! You slipped. Thank God!, You got saved in the nick of "
						+ "the time but you have lost your Gadgets",
				"Watch out! You are at end of the Cliff! Damn! You slipped. Thank God!, You got saved in the nick of "
						+ "the time but you have lost your Laptop",
				"Mayday..Mayday.., You have got surrounded by millions of Digital scary screens, and they are coming"
						+ " closer and closer to you. Screens are closing on you and oh no! oh no! you are suffocating to "
						+ "death. RIP! May 'God bless you'!" };

		String[][] arr = {
				{ "Turn 10", " ", " ", " ", "Turn 9", "F", "Cross Road 9", " ", " ", " ", "Turn 21", "F",
						"8" + deStatements[7] },
				{ " ", "F", "F", "F", " ", "F", " ", "F", "F", "F", " ", "F", " " },
				{ "3" + deStatements[2], "F", "Turn 8", " ", "Cross Road 3. " + crStatements[1], "F", "Turn 22", " ",
						"9" + deStatements[8], "F", "Cross Road 8. " + crStatements[4], " ", "Turn 20" },
				{ "F", "F", " ", "F", " ", "F", "F", "F", "F", "F", " ", "F", "F" },
				{ "Turn 6", " ", "Turn 7", "F", "Turn 11", " ", " ", " ", "Cross Road 4. " + crStatements[2], " ",
						"Cross Road 7", " ", "Turn 17" },
				{ " ", "F", "F", "F", "F", "F", "F", "F", " ", "F", "F", "F", " " },
				{ " ", "F", "4" + deStatements[3], " ", " ", " ", "Turn 13", "F", " ", "F", "7" + deStatements[6], "F",
						" " },
				{ " ", "F", "F", "F", "F", "F", "Turn 12", " ", "Cross Road 5", "F", "Turn 19", " ", "Turn 18" },
				{ "Cross Road 1", " ", " ", " ", "Turn 3", "F", "F", "F", " ", "F", "F", "F", "F" },
				{ " ", "F", "F", "F", "Turn 2", "Turn 1", "F", "F", "Cross Road 6. " + crStatements[3], " ", "Turn 14",
						"F", "6" + deStatements[5] },
				{ "Cross Road 2. " + crStatements[0], " ", "Turn 4", "F", "F", " ", "F", "F", " ", "F", " ", "F", " " },
				{ "1" + deStatements[0], "F", "Turn 5", "2" + deStatements[1], "F", "0" + "You have reached Start", "F",
						"F", "5" + deStatements[4], "F", "Turn 15", " ", "Turn 16" } };

		System.out.println("Welcome to the Quest of AI");
		System.out.println("Please Enter your name");

		String userName = scan.nextLine();
		System.out.println("Hi, " + userName
				+ "!! You have got powers of John McCarthy, and your mission is to find Artificial Intelligence(AI) which"
				+ " lies hidden in the depths of a digital realm. This forest is rumored to be a place where magic "
				+ "and technology intertwine, and where ancient wisdom waits to be discovered. Armed with your trusty"
				+ " laptop and a backpack filled with gadgets, you set off on a journey. Explore in directions north,"
				+ " south, east, west (n,s,e,w). You have got 3 lives.");

		System.out.println("Start exploration by going north. Provide your first direction to move");
		char userMove = Character.toLowerCase(scan.next().charAt(0));
		while (userMove != 'n') {
			System.out.println("Please start by going north");
			userMove = Character.toLowerCase(scan.next().charAt(0));
		}

		// initially by default have moved user 1 step north from start
		moveUser(10, 5, 'n', arr);
	}

	static void moveUser(int row, int col, char curDirection, String[][] arr) {
		if (playerLives <= 0) {
			return;
		}
		if (row == -1 && col == 6) {
			System.out.println(
					"Hooray:)<3, You have found AI after a battle of wits and determination. AI was waiting for someone"
							+ " worthy of unlocking its potential. You and Your Quest for the AI has become a tale of inspiration"
							+ " for generations to come");
			return;
		}
		if (col >= arr[0].length || col < 0 || row >= arr.length || row < 0) {
			return;
		}

		List<Character> nextPossibleMoves = aheadMoves(row, col, arr);
		boolean askInput = false;

		if (nextPossibleMoves.size() == 1) {
			System.out.println(arr[row][col].substring(1));

			if (row == 11 && col == 5) {
				askInput = true;
			} else if (Integer.parseInt(arr[row][col].substring(0, 1)) == 7) {
				gadgetsAvailable = false;
				askInput = true;
			} else if (Integer.parseInt(arr[row][col].substring(0, 1)) == 8) {
				laptopAvailable = false;
				askInput = true;
			} else {
				playerLives--;

				if (playerLives > 0) {
					System.out.println("\nYou are left with " + playerLives
							+ " live(s). You have been revived and you have gained your lost resources (if any lost)\n");

					laptopAvailable = true;
					gadgetsAvailable = true;

					int deNo = Integer.parseInt(arr[row][col].substring(0, 1));
					int nearbyCrNo = deNearbyCr[deNo - 1];
					int newRow = crCoordinates[nearbyCrNo - 1][0];
					int newCol = crCoordinates[nearbyCrNo - 1][1];
					moveUser(newRow, newCol, crDefaultDir[nearbyCrNo - 1], arr);
				} else {
					System.out.println("You are out of lives:( Please play again");
				}
			}
		} else if (nextPossibleMoves.size() == 2) {
			if (!nextPossibleMoves.contains(curDirection)) {
				System.out.println("You have reached at " + arr[row][col]);
				askInput = true;
			} else {
				dirBasedMove(row, col, curDirection, arr);
			}
		} else if (nextPossibleMoves.size() >= 3) {
			System.out.println("You have reached at " + arr[row][col]);

			boolean giveHint = (int) (Math.random() * 2) == 1 ? true : false;
			if (giveHint && laptopAvailable) {
				System.out.println("Do you want to solve a complex algorithm for earning a hint?(y/n)");

				char takeHint = scan.next().charAt(0);
				while (!(takeHint == 'y' || takeHint == 'n')) {
					System.out.println("Invalid Command. Please enter 'y' or 'n'");
					takeHint = scan.next().charAt(0);
				}

				if (takeHint == 'y') {
					String crossRoadValue = arr[row][col];
					int crossRoadNumber = Integer.parseInt(crossRoadValue.substring(11, 12));
					System.out.println(
							"Great:), Using your Laptop you solved a complex algorithm, and Earned a Hint. You should go towards '"
									+ victoryHints[crossRoadNumber - 1] + "'");
				}
			}
			askInput = true;
		}

		if (askInput) {
			getUserCommand(row, col, nextPossibleMoves, arr);
		}
	}

	static List<Character> aheadMoves(int row, int col, String[][] arr) {
		List<Character> nextPossibleMoves = new ArrayList<>();

		if (row - 1 >= 0 && !arr[row - 1][col].equals("F")) {
			nextPossibleMoves.add('n');
		}
		if (row + 1 < arr.length && !arr[row + 1][col].equals("F")) {
			nextPossibleMoves.add('s');
		}
		if (col - 1 >= 0 && !arr[row][col - 1].equals("F")) {
			nextPossibleMoves.add('w');
		}
		if (col + 1 < arr[0].length && !arr[row][col + 1].equals("F")) {
			nextPossibleMoves.add('e');
		}

		if (-1 == row - 1 && 6 == col) {
			nextPossibleMoves.add('n');
		}

		return nextPossibleMoves;
	}

	static void getUserCommand(int row, int col, List<Character> nextPossibleMoves, String[][] arr) {
		String validMoves = nextPossibleMoves.stream().map((x) -> "'" + x.toString() + "'")
				.collect(Collectors.joining(" or "));
		System.out.println("Which direction you want to go? You can go in " + validMoves + " direction");

		char userDir = scan.next().charAt(0);
		while (!nextPossibleMoves.contains(userDir)) {
			System.out.println("Invalid Move. Please go in " + validMoves + " direction");
			userDir = scan.next().charAt(0);
		}

		if (row == 2 && col == 4 && userDir == 's') {
			System.out.println("Great! You faked fingerprint of John McCarthy. Route Unlocks!!");
		} else if ((row == 4 && col == 8 && userDir == 'e') || (row == 2 && col == 10 && userDir == 'e')) {
			if (gadgetsAvailable) {
				System.out.println("Amazing! Your Gadgets have proved helpful in disabling High Frequency rays");
			} else {
				System.out.println(
						"Sorry to say, but you have lost your gadgets, and you can't go past High Frequency rays");
				getUserCommand(row, col, nextPossibleMoves, arr);
				return;
			}
		} else if (row == 2 && col == 10) {
			if (userDir == 'n') {
				if (laptopAvailable) {
					System.out.println("woohoo! You have got amazing skills. You solved a really complex algorithm");
				} else {
					System.out.println(
							"Shit! You have lost your Laptop. Oh no! What are you doing, opening this door forcefully is impossible."
									+ " Ohh! Door breaks. Route unlocks!! You are truly a brawny");
				}
			}
		}
		dirBasedMove(row, col, userDir, arr);
	}

	static void dirBasedMove(int row, int col, char givenDir, String[][] arr) {
		givenDir = Character.toLowerCase(givenDir);
		switch (givenDir) {
		case 'n':
			moveUser(row - 1, col, 'n', arr);
			break;
		case 's':
			moveUser(row + 1, col, 's', arr);
			break;
		case 'w':
			moveUser(row, col - 1, 'w', arr);
			break;
		case 'e':
			moveUser(row, col + 1, 'e', arr);
			break;
		}
	}
}