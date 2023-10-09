package GUI;
import java.awt.*;
import javax.swing.*;
import GUI.NineMenMorrisBoard;

/**
public class NineMenMorrisGUI {
    private JFrame frame;
    private NineMenMorrisBoard board;

    public NineMenMorrisGUI() {
        frame = new JFrame("Nine Men's Morris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        board = new NineMenMorrisBoard();
        frame.add(board);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NineMenMorrisGUI();
            }
        });
    }
}
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NineMenMorrisGUI extends JPanel {
    private NineMenMorrisBoard board; // Reference to the game board

    public NineMenMorrisGUI() {
        board = new NineMenMorrisBoard(); // Initialize the game board

        // Create the graphical user interface
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 700)); // Adjust the width as needed
        leftPanel.setBackground(Color.LIGHT_GRAY);

        // Placeholder label for player 1 information
        JLabel player1Label = new JLabel("Player 1 (Black)");
        leftPanel.add(player1Label);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, 700)); // Adjust the width as needed
        rightPanel.setBackground(Color.LIGHT_GRAY);

        // Placeholder label for player 2 information
        JLabel player2Label = new JLabel("Player 2 (White)");
        rightPanel.add(player2Label);

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw the game board
                int cellWidth = getWidth() / 7;
                int cellHeight = getHeight() / 7;

        
                
              //  g.drawLine(2 * cellWidth, cellHeight, 2 * cellWidth, 6 * cellHeight);
                //g.drawLine(4 * cellWidth, cellHeight, 4 * cellWidth, 6 * cellHeight);
                //g.drawLine(cellWidth, 2 * cellHeight, 6 * cellWidth, 2 * cellHeight);
                //g.drawLine(cellWidth, 4 * cellHeight, 6 * cellWidth, 4 * cellHeight);

                
                int ovalSize = Math.min(cellWidth, cellHeight) - 50; // Adjust size as needed
             
          

            
                
                // Draw lines to connect intersections
                g.setColor(Color.black);

                // Vertical lines
                for (int i = 0; i < 7; i++) {
                    int x = i * cellWidth + cellWidth / 2;
                    int y1 = 1;
                    int y2 = 7 * cellHeight;
                    g.drawLine(x, y1, x, y2);
                }

                // Horizontal lines
                for (int i = 0; i < 7; i++) {
                    int x1 = 0;
                    int x2 = 7 * cellWidth;
                    int y = i * cellHeight + cellHeight / 2; ;
                    g.drawLine(x1, y, x2, y);
                }
                
                
                // Draw pieces on the board based on boardState
                for (int row = 0; row < 7; row++) {
                    for (int col = 0; col < 7; col++) {

                    	
                    	
                        int player = board.getBoardState()[row][col];
                        if (player == 1) {
                            // Draw black piece
                            g.setColor(Color.BLACK);
                            g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                                    row * cellHeight + (cellHeight - ovalSize) / 2,
                                    ovalSize, ovalSize);                            } 
                        else if (player == 2) {
                            // Draw white piece
                            g.setColor(Color.WHITE);
                            g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                                    row * cellHeight + (cellHeight - ovalSize) / 2,
                                    ovalSize, ovalSize);                            }
                        else {
                            // Draw markers for initial piece placement
                        	if(board.isValidIntersecction(row,col)) {
                            g.setColor(Color.CYAN); // Change color as needed
                 
                            g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                                    row * cellHeight + (cellHeight - ovalSize) / 2,
                                    ovalSize, ovalSize);       
                      
                            
                            
                            g.setColor(Color.ORANGE); // Change color as needed

                            // Vertical lines
                            //for (int i =0 ; i < 7;) {
                                int x = col * cellWidth + cellWidth / 2;
                                int y1 = 1;
                                int y2 = 7 * cellHeight;
                                g.drawLine(x, y1, x, y2);
                          //  }

                            // Horizontal lines
                           // for (int i = 0; i < 7; i++) {
                                int x1 = 0;
                                int x2 = 7 * cellWidth;
                                int y = row * cellHeight + cellHeight / 2; ;
                                g.drawLine(x1, y, x2, y);
                           // }
                            
                        
                        	}
                        }
                    }
                }
                
                
                
                
            }
        };

        boardPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked at row " + e);

                int mouseX = e.getX();
                int mouseY = e.getY();
                
                int cellWidth = boardPanel.getWidth() / 7;
                int cellHeight = boardPanel.getHeight() / 7;

                int clickedRow = mouseY / cellHeight;
                int clickedCol = mouseX / cellWidth;
                
                int row=clickedRow;
                int col=clickedCol;

                System.out.println("Mouse clicked at row " + row+ "row - col"+col);
                // Check if the clicked position is valid for placing a piece
                if (board.isValidMove(row, col)) {
                	System.out.println(board.isValidMove(row, col));
                    board.getBoardState()[row][col] = board.getCurrentPlayer();
                    boardPanel.repaint(); // Redraw the board

                    // Toggle players
                    board.setCurrentPlayer((board.getCurrentPlayer() == 1) ? 2 : 1);

                    // Decrease the count of remaining pieces for the current player
                    if (board.getCurrentPlayer() == 1) {
                        board.setBlackPieces(board.getBlackPieces() - 1);
                    } else {
                        board.setWhitePieces(board.getWhitePieces() - 1);
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(boardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Nine Men's Morris");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700); // Adjust the size as needed
            frame.add(new NineMenMorrisGUI());
            frame.setVisible(true);
        });
    }
}
