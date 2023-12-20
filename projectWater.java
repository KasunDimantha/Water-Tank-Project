import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

interface WaterLevelObserver {
    void update(int waterLevel);
}

class SMSFrame extends JFrame implements WaterLevelObserver {

    private JLabel smsLabel;

    SMSFrame() {
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("SMS");

        smsLabel = new JLabel();
        smsLabel.setFont(new Font("", 1, 35));
        add(smsLabel);

        setVisible(true);
    }

    public void update(int waterLevel){
        smsLabel.setText("Sending SMS : " + waterLevel);
    }
}

class DisplayFrame extends JFrame implements WaterLevelObserver{

    private JLabel displayLabel;

    DisplayFrame() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Display");

        displayLabel = new JLabel("50");
        displayLabel.setFont(new Font("", 1, 35));
        add(displayLabel);

        setVisible(true);
    }

    public void update(int waterLevel) {
        displayLabel.setText(Integer.toString(waterLevel));
    }
}



class projectWater {
    public static void main(String[] args){

    }
    
}