/***************************************
 * Yossi Silberhaft - 210028924
 * Binyamin Greenberg - 200220671
 **************************************/
package gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GoLPanel extends JPanel {
    private static final long serialVersionUID = 7208061402525447771L;

    public static boolean RUNNING = false;
    private GameBoard gameBoard;
    private int counter = 1;

    private final GoLFrame parentFrame;

    public GoLPanel(GoLFrame goLFrame) {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        createAndShowGUI();
        this.parentFrame = goLFrame;
    }

    private void createAndShowGUI() {
        createGameBoard(10, 10);

        final JButton randomizeButton = new JButton("Randomize");
        randomizeButton.setBounds(10, 80, 100, 25);
        randomizeButton.setEnabled((!RUNNING));
        randomizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoLPanel.this.gameBoard.Randomize();
                GoLPanel.this.gameBoard.updateBoard();
            }
        });


        final JButton clearButton = new JButton("Clear");
        clearButton.setSize(50, 25);
        clearButton.setBounds(10, 45, 100, 25);
        clearButton.setEnabled(!RUNNING);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                GoLPanel.this.gameBoard.clearBoard();
            }
        });

        final JComboBox<Integer> sizeXBox = new
                JComboBox<Integer>(createSequentialArray(10, 100));
        sizeXBox.setBounds(10, 80, 75, 25);

        final JComboBox<Integer> sizeYBox = new
                JComboBox<Integer>(createSequentialArray(10, 100));
        sizeYBox.setBounds(80, 80, 75, 25);

        /**Creates the board at the start 100x100**/
        createGameBoard(100,100);

        final JButton setSizeButton = new JButton("Set size");
        setSizeButton.setBounds(10, 115, 100, 25);
        setSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGameBoard(sizeXBox.getItemAt(sizeXBox.getSelectedIndex()),
                        sizeYBox.getItemAt(sizeYBox.getSelectedIndex()));
            }
        });


        final JButton startButton = new JButton("Start");
        startButton.setSize(50, 25);
        startButton.setBounds(10, 10, 100, 25);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                RUNNING = !RUNNING;
                GoLPanel.this.gameBoard.setRunning(RUNNING);
                startButton.setText(RUNNING ? "Stop" : "Start");
                clearButton.setEnabled(!RUNNING);

                sizeXBox.setEnabled(!RUNNING);
                sizeYBox.setEnabled(!RUNNING);
                setSizeButton.setEnabled(!RUNNING);
            }
        });


        String[] tempArrStrings = {"No-Wrap","Wrap"};
        final JComboBox<String>wrapButtonBox = new JComboBox<String>(tempArrStrings);
        wrapButtonBox.setBounds(10, 120, 100, 25);
        wrapButtonBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				boolean wrapped = wrapButtonBox.getSelectedItem().equals("Wrap");
				System.out.println("wrap " + wrapped);
				GoLPanel.this.gameBoard.setWraped(wrapped);
			}
		});

        JLabel lblName = new JLabel("");
        lblName.setText("<html>" + "Created by: Yossi Silberhaft &" +
                " Binyamin Greenberg" + "</html>");
        // this.add(gameBoard);
        this.add(clearButton);
        this.add(startButton);
        //this.add(sizeXBox);
        //this.add(sizeYBox);
        //this.add(setSizeButton);
        this.add(wrapButtonBox);
        this.add(randomizeButton);
        this.add(lblName).setBounds(10, 900, 120, 50);
    }

    private void createGameBoard(int sizeX, int sizeY) {
        if (this.gameBoard != null) {
            this.remove(this.gameBoard);
        }
        this.gameBoard = new GameBoard(sizeX, sizeY);
        final int boardWidth = sizeX * GameBoard.getSquareDisplaySize();
        final int boardHeight = sizeY * GameBoard.getSquareDisplaySize();
        this.gameBoard.setBounds(160, 10, boardWidth, boardHeight);
        this.add(this.gameBoard);
        if (this.parentFrame != null) {
            this.parentFrame.setSize(160 + boardWidth, boardHeight);
            System.out.println(this.parentFrame.getSize());
        }
        this.revalidate();
        this.repaint();
        new Thread(this.gameBoard).start();
    }


    private Integer[] createSequentialArray(int from, int to) {
        if (to < from) {
            return new Integer[0];
        }
        final int size = to - (from - 1);
        final Integer[] rc = new Integer[size];
        for (int i = from, j = 0; i <= to; i++, j++) {
            rc[j] = i;
        }
        return rc;
    }
}