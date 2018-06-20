import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame1 extends JFrame {
    private JButton jbtn = new JButton("登入");
    private JButton jbtn1 = new JButton("退出");
    private JTextField jtf = new JTextField();
    private JPasswordField jtf1 = new JPasswordField();
    private JLabel jib = new JLabel("帳號");
    private JLabel jib1 = new JLabel("密碼");
    public MainDL(){
        ex1();
    }
    private void ex1(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(100,100,600,500);
        jtf.setBounds(100,100,200,50);
        jtf1.setBounds(100,200,200,50);
        jbtn.setBounds(100,300,100,50);
        jbtn1.setBounds(200,300,100,50);
        jib.setBounds(100,50,100,50);
        jib1.setBounds(100,150,100,50);
        this.add(jbtn);
        this.add(jbtn1);
        this.add(jtf);
        this.add(jtf1);
        this.add(jib);
        this.add(jib1);
        jbtn1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (jtf.getText().equals("tim")&&(new String(jtf1.getPassword()).equals("123456"))){
                    MainDF frm = new MainDF();
                    frm.setVisible(true);
                    MainDL.this.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
        });
    }
}
