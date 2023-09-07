public class Main {
    public static void main(String[] args) {
        SuperHomeController superHomeController = new SuperHomeController();
        superHomeController.addComponent(new Switch(superHomeController,"Switch"));
        superHomeController.addComponent(new TV("TV Living Room",400,100));
        superHomeController.addComponent(new Sperker("Speaker",400,200));
        superHomeController.addComponent(new Window("Window Living Room",400,300));
        superHomeController.addComponent(new TV("TV Dining Room",400,400));

    }
}