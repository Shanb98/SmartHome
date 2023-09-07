public class SuperHomeController implements SuperHome{
    private SuperHome[] switches = new SuperHome[100];
    private int nextIndex;
    private String status;
    public void addComponent(SuperHome ob) {
        switches[nextIndex++] = ob;
    }
    public void notifyComponents() {
        for (int i = 1; i < nextIndex; i++) {
            switches[i].operate(status);
        }
    }
    public int getNextIndex() {
        return nextIndex;
    }
    public String[] getListComponents() {
        String[] names = new String[nextIndex];
        for (int i = 0; i < nextIndex; i++) {
            names[i] = switches[i].getName();
        }
        return names;
    }

    @Override
    public String getName() {
        return null;
    }
    @Override
    public void operate(String status) {
        this.status = status;
        notifyComponents();
    }
    public void sendCommand(String status,String name){
        for (int i = 1; i < nextIndex; i++){
            if (switches[i].getName().equals(name)){
                switches[i].operate(status);
            }
        }
    }
}
