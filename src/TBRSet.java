import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public interface TBRSet {
	ArrayList<Book> list = new ArrayList<>();

	static void createTBR() {
		Scanner scan = null;
		File file = new File("src/TBR.txt");
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.print("File failure.");
			System.exit(0);
		}
		while (scan.hasNextLine()) {
			String[] s = scan.nextLine().split("--");
			list.add(new Book(s[0], s[1], s[2], Double.parseDouble(s[3])));
		}
		scan.close();
	}

	static String tbrString(Flag flag) {
		switch (flag) {
			case RATING:
				Collections.sort(list, (o1, o2) -> (o1.getRating() <= o2.getRating() ? 1 : -1));
				break;
			case DATE:
				Collections.sort(list, (o1, o2) -> (o1.getDate().compareTo(o2.getDate())));
				break;
			case AUTHOR:
				Collections.sort(list, (o1, o2) -> (o1.getAuthor().compareTo(o2.getAuthor())));
				break;
			case TITLE:
				Collections.sort(list, (o1, o2) -> (o1.getTitle().compareTo(o2.getTitle())));
				break;
			case RANDOM:
				Collections.shuffle(TBRSet.list);
				break;
		}
		String s = "";
		int count = 1;
		for (Book book : TBRSet.list)
			s = s.concat(String.format("%03d: ", count++) + book + "\n");
		return s.trim();
	}
}
