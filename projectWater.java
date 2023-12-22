import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

    class AlarmFrame extends JFrame implements WaterLevelObserver {
    private JLabel alarmLabel;

    AlarmFrame() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Alarm");

        alarmLabel = new JLabel("Off");
        alarmLabel.setFont(new Font("", 1, 35));
        add(alarmLabel);

        setVisible(true);
    }

    public void update(int waterLevel) {
        alarmLabel.setText(waterLevel > 50 ? "On" : "Off");
    }
}

class SplitterFrame extends JFrame implements WaterLevelObserver {
    private JLabel splitterLabel;

    SplitterFrame() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Splitter");

        splitterLabel = new JLabel("Off");
        splitterLabel.setFont(new Font("", 1, 35));
        add(splitterLabel);

        setVisible(true);
    }

    public void update(int waterLevel) {
        splitterLabel.setText(waterLevel > 75 ? "On" : "Off");
    }
}

class WaterTankFrame extends JFrame {
    private JSlider watereLevelSlider;
    private WaterTankController waterTankController;

    WaterTankFrame(WaterTankController waterTankController) {

        this.waterTankController = waterTankController;
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        watereLevelSlider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
        watereLevelSlider.setMajorTickSpacing(10);
        watereLevelSlider.setPaintLabels(true);

        watereLevelSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int waterLevel = watereLevelSlider.getValue();
                waterTankController.setWaterLevel(waterLevel);
            }
        });

        add(watereLevelSlider);
        setVisible(true);
    }
}

class WaterTankController {
    private WaterLevelObserver[] observerAray = new WaterLevelObserver[0];

    private int waterLevel;

    public void addWaterLevelObserver(WaterLevelObserver waterLevelObserver) {
        WaterLevelObserver[] temp = new WaterLevelObserver[observerAray.length + 1];
        for (int i = 0; i < observerAray.length; i++) {
            temp[i] = observerAray[i];
        }
        temp[temp.length - 1] = waterLevelObserver;
        observerAray = temp;
    }

    public void setWaterLevel(int waterLevel) {
        if (this.waterLevel != waterLevel) {
            this.waterLevel = waterLevel;
            notifyObjects();
        }
    }

    public void notifyObjects() {
        for (WaterLevelObserver waterLevelObserver : observerAray) {
            waterLevelObserver.update(waterLevel);
        }
    }
}




class projectWater {
    public static void main(String[] args){

        WaterTankController waterTankController = new WaterTankController();
        waterTankController.addWaterLevelObserver(new DisplayFrame());
        waterTankController.addWaterLevelObserver(new AlarmFrame());
        waterTankController.addWaterLevelObserver(new SplitterFrame());
        waterTankController.addWaterLevelObserver(new SMSFrame());

        new WaterTankFrame(waterTankController);


    }
    
}