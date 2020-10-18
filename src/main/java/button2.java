import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class button2 implements ActionListener{
    testMainApp superapp;
    public button2(testMainApp app){
        superapp = app;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("button2 action performed");
        superapp.setTestLabel("test");

    }
}
