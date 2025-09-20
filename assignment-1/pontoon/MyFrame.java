import java.awt.*;
import javax.swing.*;
import java.util.*;

// functions needed:
// need count for current points* 
// need condition for game start and game over*
// need switch player*
// need check win condition* (if condition is 21 for current player, current player win, if over 21, current player lose)

public class MyFrame extends JFrame{
    private int globalPoints = 0;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver = false;

    public class Player {
        private int id;
        private Color color;
        private int points;
        private boolean winStatus;
        
        public Player(int id) {
            this.id = id;
            this.color = (id == 1) ? Color.BLUE : Color.RED;
            this.points = 0;
            this.winStatus = false;
        }
        
        public int getId() { return id; }
        public Color getColor() { return color; }
        public int getPoints() { return points; }
        public void setPoints(int points) { this.points = points; }
        public void addPoints(int points) { this.points += points; }
        public void setWin(boolean winStatus) { this.winStatus = winStatus; }
    }
    
    private void countPoints(int points){
        globalPoints += points;
    }
    
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private boolean checkWin(){
        if(globalPoints == 21){
            currentPlayer.winStatus = true;
            return true; 
        } else if(globalPoints > 21){
            if(currentPlayer == player1){
                player2.winStatus = true;
            } else {
                player1.winStatus = true;
            }
            return true; 
        }
        return false; 
    }

    private void resetGame(){
        System.out.println("New Game Started!");
        globalPoints = 0;
        gameOver = false;
        Random rand = new Random();
        currentPlayer = rand.nextBoolean() ? player1 : player2;
        // currentPlayer = player1;
        player1.winStatus = false;
        player2.winStatus = false;
        pointsLabel.setText("Points: 0");
        turnLabel.setText("Player " + currentPlayer.getId() + "'s Turn");
        
        Random random = new Random();
        for(int i = 0; i < 25; i++){
            int newValue = random.nextInt(5) + 1;
            buttons[i].setText(Integer.toString(newValue));
            buttons[i].setActionCommand(Integer.toString(newValue));
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
            buttons[i].setForeground(null);
        }
    }

    // private boolean isGameOver (int currentPoints){
    //     if (currentPoints >= 21){
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    private JLabel pointsLabel;
    private JLabel turnLabel;
    private JButton newGameButton;
    private JButton[] buttons = new JButton[25];

    
    public MyFrame() {
        player1 = new Player(1);
        player2 = new Player(2);
        
        Random rand = new Random();
        currentPlayer = rand.nextBoolean() ? player1 : player2;
        
        setTitle("Pontoon Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel infoPanel = new JPanel();
        pointsLabel = new JLabel("Points: 0");
        turnLabel = new JLabel("Player " + currentPlayer.getId() + "'s Turn");
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> resetGame());
        infoPanel.add(pointsLabel);
        infoPanel.add(turnLabel);
        infoPanel.add(newGameButton);
        add(infoPanel, BorderLayout.NORTH);
        
        this.setSize(500, 500);

        JPanel gridPanel = new JPanel(new GridLayout(5, 5, 5, 5));

        Random random = new Random();

        for(int i = 0; i < 25; i++){
            // random int (range 1-5)
            int random_int = random.nextInt(5) + 1;

            JButton button = new JButton(random_int + "");
            button.setActionCommand(random_int + "");
            buttons[i] = button;
            button.addActionListener(e -> {
                if(gameOver) return;
                
                int buttonValue = Integer.parseInt(e.getActionCommand());
                countPoints(buttonValue);

                button.setBackground(currentPlayer.getColor());
                button.setForeground(Color.WHITE);
                button.setEnabled(false);
                pointsLabel.setText("Points: " + globalPoints);
                
                System.out.println("Player " + currentPlayer.getId() + " clicked " + buttonValue);
                System.out.println("Total: " + globalPoints);
   
                if(checkWin()){
                    gameOver = true;
                    if(player1.winStatus){
                        System.out.println("Player 1 Wins!");
                        turnLabel.setText("Player 1 Wins!");
                    } else if(player2.winStatus){
                        System.out.println("Player 2 Wins!");
                        turnLabel.setText("Player 2 Wins!");
                    }
                    return;
                }

                switchPlayer();
                turnLabel.setText("Player " + currentPlayer.getId() + "'s Turn");
            });
            gridPanel.add(button);
        }

        add(gridPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }


    
}