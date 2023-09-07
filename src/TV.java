import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class TV implements SuperHome {
    private JFrame frame;
    private String status;
    private JLabel statusLabel;
    @Override
    public void operate(String status) {
        if(status.equals("ON")){
            statusLabel.setText("ON");
            System.out.println("Tv Updated - ON");
        }else{
            statusLabel.setText("OFF");
            System.out.println("Tv Updated - OFF");
        }
    }
    TV(String name ,int x,int y){
        frame = new JFrame();
        frame.setSize(300,100);
        frame.setTitle(name);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLocation(x,y);

        statusLabel = new JLabel();
        statusLabel.setText("OFF");
        statusLabel.setFont(new Font("",1,20));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add("Center",statusLabel);

        frame.setVisible(true);

    }
    @Override
    public String getName() {
        return frame.getTitle();
    }
}
