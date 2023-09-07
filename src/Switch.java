import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
class Switch implements SuperHome {

    private JButton btnOnOff;
    private JButton btnSettings;
    private JSpinner hoursSpinner;
    private JSpinner minutesSpinner;
    private JLabel hoursLabel;
    private JLabel minutesLabel;
    private JFrame frame;
    private String status;
    private JList<String> componentList;
    private DefaultListModel<String> componentListModel;
    private JButton functionButton;
    private JLabel StartHoursLabel;
    private JLabel StartMinutesLabel;
    private JLabel EndHoursLabel;
    private JLabel EndMinutesLabel;
    private JSpinner StartHoursSpinner;
    private JSpinner StartMinutesSpinner;
    private JSpinner EndMinutesSpinner;
    private JSpinner EndHoursSpinner;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    @Override
    public void operate(String status) {
        this.status = status;
        System.out.println("Switch Updated");
    }

    Switch(SuperHomeController superHomeController, String name) {
        frame = new JFrame();
        frame.setSize(300, 170);
        frame.setTitle(name);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLocation(100, 100);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1));

        btnOnOff = new JButton("ON");
        btnOnOff.setFont(new Font("", 1, 30));
        btnOnOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (btnOnOff.getText().equals("ON")) {
                    btnOnOff.setText("OFF");
                    status = "ON";
                    superHomeController.operate(status);
                } else {
                    btnOnOff.setText("ON");
                    status = "OFF";
                    superHomeController.operate(status);
                }
            }
        });
        topPanel.add(btnOnOff);
        frame.add(topPanel);

        btnSettings = new JButton("Settings");
        btnSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFrame settingsFrame = new JFrame();
                settingsFrame.setSize(300, 300);
                settingsFrame.setTitle("Controller");
                settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                settingsFrame.setLocationRelativeTo(null);
                settingsFrame.setLocation(750,100);

                componentListModel = new DefaultListModel<>();
                componentList = new JList<>(componentListModel);

                String[] listNames = superHomeController.getListComponents();
                for (int i = 0; i < superHomeController.getNextIndex(); i++) {
                    componentListModel.addElement(listNames[i]);
                }

                componentList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            String selectedComponent = componentList.getSelectedValue();
                            System.out.println(selectedComponent + " Selected");
                            if (selectedComponent != null) {
                                JFrame selectedFrame = new JFrame();
                                selectedFrame.setSize(550, 220);
                                selectedFrame.setTitle(selectedComponent);
                                selectedFrame.setLocation(400,800);
                                JPanel topPanel = new JPanel(new BorderLayout());
                                listModel = new DefaultListModel<>();
                                list = new JList<>(listModel);

                                topPanel.add(list, BorderLayout.CENTER);
                                selectedFrame.add("North", topPanel);
                                JPanel bottomPanel = new JPanel(new FlowLayout());
                                topPanel.add(new JScrollPane(list), BorderLayout.CENTER);

                                SpinnerModel valueStartHours = new SpinnerNumberModel(0, 0, 23, 1);
                                StartHoursLabel = new JLabel("Start: Hours");
                                StartHoursSpinner = new JSpinner(valueStartHours);

                                SpinnerModel valueStartMinutes = new SpinnerNumberModel(0, 0, 59, 1);
                                StartMinutesLabel = new JLabel("Minutes");
                                StartMinutesSpinner = new JSpinner(valueStartMinutes);

                                SpinnerModel valueEndHours = new SpinnerNumberModel(0, 0, 23, 1);
                                EndHoursLabel = new JLabel("End: Hours");
                                EndHoursSpinner = new JSpinner(valueEndHours);

                                SpinnerModel valueEndMinutes = new SpinnerNumberModel(0, 0, 59, 1);
                                EndMinutesLabel = new JLabel("Minutes");
                                EndMinutesSpinner = new JSpinner(valueEndMinutes);

                                bottomPanel.add(StartHoursLabel);
                                bottomPanel.add(StartHoursSpinner);
                                bottomPanel.add(StartMinutesLabel);
                                bottomPanel.add(StartMinutesSpinner);

                                bottomPanel.add(EndHoursLabel);
                                bottomPanel.add(EndHoursSpinner);
                                bottomPanel.add(EndMinutesLabel);
                                bottomPanel.add(EndMinutesSpinner);

                                functionButton = new JButton("Set");

                                functionButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent evt) {
                                        int selectedIndex = componentListModel.indexOf(selectedComponent); // index of the selected Component
                                        System.out.println("index of the element " + selectedIndex);
                                        int startHours = (int) StartHoursSpinner.getValue();
                                        int startMinutes = (int) StartMinutesSpinner.getValue();
                                        int endHours = (int) EndHoursSpinner.getValue();
                                        int endMinutes = (int) EndMinutesSpinner.getValue();
                                        String time = "Start at: " + startHours + "." + startMinutes + "    End at: " + endHours + "." + endMinutes;
                                        System.out.println(time);
                                        listModel.addElement(time);

                                        ChangeListener minutesChangeListener = new ChangeListener() {
                                            @Override
                                            public void stateChanged(ChangeEvent e) {
                                                int hours = (int) hoursSpinner.getValue();
                                                int minutes = (int) minutesSpinner.getValue();
                                                System.out.println("Hours: " + hours);
                                                System.out.println("Minutes: " + minutes);

                                                if (startHours == hours && startMinutes == minutes) {
                                                    status = "OFF";
                                                    superHomeController.sendCommand( status,selectedComponent);
                                                }
                                                if (endHours == hours && endMinutes == minutes) {
                                                    status = "ON";
                                                    superHomeController.sendCommand( status,selectedComponent);
                                                }
                                            }
                                        };

                                        ChangeListener hoursChangeListener = new ChangeListener() {
                                            @Override
                                            public void stateChanged(ChangeEvent e) {
                                                int hours = (int) hoursSpinner.getValue();
                                                int minutes = (int) minutesSpinner.getValue();
                                                System.out.println("Hours: " + hours);
                                                System.out.println("Minutes: " + minutes);

                                                if (startHours == hours && startMinutes == minutes) {
                                                    status = "OFF";
                                                    superHomeController.sendCommand( status,selectedComponent);
                                                }
                                                if (endHours == hours && endMinutes == minutes) {
                                                    status = "ON";
                                                    superHomeController.sendCommand( status,selectedComponent);
                                                }
                                            }
                                        };

                                        minutesSpinner.addChangeListener(minutesChangeListener);
                                        hoursSpinner.addChangeListener(hoursChangeListener);
                                    }
                                });
                                functionButton.setFont(new Font("", 1, 20));
                                bottomPanel.add(functionButton);

                                selectedFrame.add("South", bottomPanel);

                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setVisible(true);
                            }
                        }
                    }
                });

                settingsFrame.add(componentList);
                settingsFrame.setVisible(true);
            }
        });

        btnSettings.setFont(new Font("", 1, 20));
        topPanel.add(btnSettings);

        frame.add("North", topPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        SpinnerModel valueHours = new SpinnerNumberModel(0, 0, 23, 1);
        hoursLabel = new JLabel("Hours");
        hoursSpinner = new JSpinner(valueHours);

        SpinnerModel valueMinutes = new SpinnerNumberModel(0, 0, 59, 1);
        minutesLabel = new JLabel("Minutes");
        minutesSpinner = new JSpinner(valueMinutes);

        bottomPanel.add(hoursLabel);
        bottomPanel.add(hoursSpinner);
        bottomPanel.add(minutesLabel);
        bottomPanel.add(minutesSpinner);
        topPanel.add(bottomPanel);
        frame.add(topPanel);

        frame.setVisible(true);
    }

    @Override
    public String getName() {
        return frame.getTitle();
    }
}

