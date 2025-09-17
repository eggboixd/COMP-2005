import java.awt.*;
import javax.swing.*;
import java.util.*;

// functions needed:
// need count for current points* 
// need condition for gameover
// need switch player
// need check win condition (if condition is 21 for current player, current player win, if over 21, current player lose)

public class MyFrame extends JFrame {
    private int currentPoints = 0;
    
    public MyFrame() {
        setTitle("Pontoon Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // for(int i = 0; i < 5; i++){
        //     JButton button = new JButton("Click Me");
        //     button.setBounds(100, 100, 250, 100);
        //     button.addActionListener(e -> System.out.println("Button clicked!"));
        //     add(button);
        // }
        
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(5, 5));

        for(int i = 0; i < 25; i++){
            // random int (range 1-5)
            Random random = new Random();
            int random_int = random.nextInt(5) + 1;
            
            // button
            JButton button = new JButton(random_int + "");
            button.setActionCommand(random_int + "");
            button.addActionListener(e -> {
                int buttonValue = Integer.parseInt(e.getActionCommand());
                currentPoints += buttonValue;
                System.out.println(buttonValue);
                System.out.println(currentPoints);
            });
            frame.add(button);
        }

        frame.setVisible(true);
    }
}