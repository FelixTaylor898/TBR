import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class Frame implements TBRSet {
	static JFrame frame;
	static JPanel panel, body, buttons;

	public Frame() {
		buttons = new JPanel(new GridLayout(0, 6));
		Font font = new Font("Arial", Font.PLAIN, 15);
		JTextArea textField = new JTextArea(TBRSet.tbrString(Flag.AUTHOR));
		textField.setLineWrap(true);
		textField.setWrapStyleWord(true);
		textField.setSize(600, 600);
		JButton regen = new JButton("Randomize");
		regen.setFocusable(false);
		regen.setBackground(new Color(137, 207, 240));
		regen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(TBRSet.tbrString(Flag.RANDOM));
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		JButton title = new JButton("Title");
		title.setFocusable(false);
		title.setBackground(new Color(137, 207, 240));
		title.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(TBRSet.tbrString(Flag.TITLE));
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		JButton author = new JButton("Author");
		author.setFocusable(false);
		author.setBackground(new Color(137, 207, 240));
		author.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(TBRSet.tbrString(Flag.AUTHOR));
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		JButton date = new JButton("Date");
		date.setFocusable(false);
		date.setBackground(new Color(137, 207, 240));
		date.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(TBRSet.tbrString(Flag.DATE));
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		JButton rating = new JButton("Rating");
		rating.setFocusable(false);
		rating.setBackground(new Color(137, 207, 240));
		rating.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(TBRSet.tbrString(Flag.RATING));
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		JButton addBook = new JButton("Add Book");
		addBook.setFocusable(false);
		addBook.setBackground(new Color(137, 207, 240));
		addBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String title = JOptionPane.showInputDialog("Title");
				String last = JOptionPane.showInputDialog("Author Last Name");
				String first = JOptionPane.showInputDialog("Author First Name");

				int month, day, year;

				String date = JOptionPane.showInputDialog("Publication Date (YYYY-MM-DD)");
				String[] d = date.split("-");
				month = Integer.parseInt(d[1]);
				day = Integer.parseInt(d[2]);
				year = Integer.parseInt(d[0]);

				boolean valid = ((day <= 30 && (month == 4 || month == 9 || month == 6 || month == 11))
						|| (day <= 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
								|| month == 12))
						|| (month == 2 && (day <= 28
								|| (day == 29 && ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0))))));

				double rate = Double.parseDouble(JOptionPane.showInputDialog("Goodreads rating"));

				if (valid && rate >= 1 && rate <= 5) {
					TBRSet.list.add(new Book(title, last + ", " + first, d[0] + "-" + d[1] + "-" + d[2], rate));
					textField.setText(TBRSet.tbrString(Flag.AUTHOR));
					SwingUtilities.updateComponentTreeUI(frame);

					try {
						String filename = "src/TBR.txt";
						FileWriter fw = new FileWriter(filename, true);
						fw.write("\n" + title + "--" + last + ", " + first + "--" + d[0] + "-" + d[1] + "-" + d[2] + "--"
								+ rate);
						fw.close();
					} catch (IOException ioe) {
						System.err.println("IOException: " + ioe.getMessage());
					}
				} else JOptionPane.showMessageDialog(null, "Invalid input");
			}
		});

		textField.setFont(font);
		textField.setEditable(false);
		buttons.add(regen);
		buttons.add(title);
		buttons.add(author);
		buttons.add(date);
		buttons.add(rating);

		buttons.add(addBook);
		body = new JPanel();
		body.add(textField);
		panel = new JPanel(new BorderLayout());
		panel.add(buttons, BorderLayout.NORTH);
		panel.add(body, BorderLayout.SOUTH);
		if (frame != null)
			frame.dispose();
		frame = new JFrame("Randomize TBR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(700, 700));

		frame.add(scrollPane);
		frame.pack();
		frame.setPreferredSize(new Dimension(700, 700));
		frame.setVisible(true);
	}

}