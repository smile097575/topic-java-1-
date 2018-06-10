import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Frame2 extends JFrame {
    static int mapHeight = 9, mapWidth = 9, mapRow = 9, mapCol = 9;
    static int frameWidth = 400, frameHeight = 400;
    private JButton[][] jbtn;
    private int bombCount = 10;
    private JLabel bombmany = new JLabel("目前炸彈數：" + bombCount);
    private boolean buttonpull[][] = new boolean[mapHeight][mapWidth];
    private int timecount = 0;
    private int timeContinue = 1;
    private JLabel jlb = new JLabel("已經過時間：0");
    private JPanel centerButtonPanel;
    private JPanel topPanel;
    private int map[][] = new int[mapRow][mapCol];
    private int mapAroundBomb[][] = new int[mapRow][mapCol];

    public Frame2(int v1, int v2) {
        mapHeight = v1;
        mapWidth = v2;
        ex2();
    }

    public void ex2() {
        this.setBounds(100, 100, frameWidth, frameHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setTitle("踩地雷");
//        ----------------建立地圖-------------------------
        jbtn = new JButton[mapHeight][mapWidth];
        centerButtonPanel = new JPanel();
        centerButtonPanel.setLayout(new GridLayout(mapHeight, mapWidth));
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                jbtn[i][j] = new JButton();
                centerButtonPanel.add(jbtn[i][j]);
            }
        }
//        ------------------上面介面------------------------------
        add(centerButtonPanel, BorderLayout.CENTER);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (timeContinue == 1) jlb.setText("已經過：" + (timecount++) + "秒");
            }
        };
        new Timer().scheduleAtFixedRate(timerTask, 0, 1000);
        topPanel = new JPanel();
        topPanel.add(jlb);
        bombmany.setText("目前炸彈有：" + bombCount);
        JButton restart = new JButton("新局");
        restart.setActionCommand("");
        add(topPanel, BorderLayout.NORTH);
        topPanel.add(restart);
        topPanel.add(bombmany);
//        ---------------------按鈕設定------------------------------
        JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.setLayout(new GridLayout(mapHeight, mapWidth));
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                jbtn[i][j] = new JButton();
                jbtn[i][j].setBackground(Color.WHITE);
                jbtn[i][j].setActionCommand(i + "" + j);
//                jbtn [i][j].addActionListener(this);
                centerButtonPanel.add(jbtn[i][j]);
            }
        }
        add(centerButtonPanel, BorderLayout.CENTER);
//        ---------------新局----------------------------------
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timecount = 1;
                timeContinue = 1;
                //for (int i = 0;i<mapHeight;i++) Arrays.fill(jbtn[i],0);//*****************
                for (int i = 0; i < mapHeight; i++) Arrays.fill(buttonpull[i], false);
                for (int i = 0; i < mapHeight; i++) {
                    for (int j = 0; j < mapWidth; j++) {
                        jbtn[i][j].setBackground(Color.WHITE);
                        jbtn[i][j].setText("");
                    }
                }
                setMap();
                aroundBomb();
                bombCount = 10;
                bombmany.setText("目前炸彈數：" + bombCount);
            }
        });
//        ---------------設定地圖----------------------------------------
        setMap();
        aroundBomb();
        setVisible(true);
    }

    private void setMap() {
        int count = 0;
        while (count != 10) {
            int a = (int) (Math.random() * 9);   //設定炸彈座標,1=有
            int b = (int) (Math.random() * 9);
            if (map[a][b] == 0) {
                map[a][b] = 1;
                count++;
            }
        }
    }

    private void aroundBomb() {
        for (int i = 0; i < mapRow; i++) {
            for (int j = 0; j < mapCol; j++) {
                if (map[i][j] == 1) {
                    mapAroundBomb[i][j] = -1;
//                }else {
//                    for (int x = 0 ; )
                }
            }
        }
    }
}
