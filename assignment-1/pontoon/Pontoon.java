import javax.swing.*;

public class Pontoon {
    public static void main(String[] args) {
        String intro = "Pontoon\n\n" +
                        "- In a 5x5 grid, each tile holds a value between 1-5 \n" +
                        "- Each player takes turns clicking on those tiles, the first player will be randomized \n" +
                        "- Everytime a player clicks on a button, the value of that button gets added to a global counter \n" +
                        "- When the counter goes above 21 when a player clicks on a button that player loses and the other player wins \n";

        JOptionPane.showMessageDialog(null, intro, "Welcome to Pontoon", JOptionPane.INFORMATION_MESSAGE);       
        
        // new MyFrame();

        SwingUtilities.invokeLater(() -> {
            MyFrame frame = new MyFrame();
            frame.setVisible(true);
        });
    }
}