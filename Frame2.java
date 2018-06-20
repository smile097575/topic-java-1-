impimport javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Frame2 extends JFrame {
    static int mapHeight = 9, mapWidth = 9;
    static int frameWidth = 400, frameHeight = 400;
    private JButton[][] jbtn = new JButton[mapHeight][mapWidth];
    private JButton[][] jbtnbomb = new JButton[mapHeight][mapWidth];
    //    private int bombCount = 10;
    private int bombCount = Math.round(((mapHeight * mapWidth) * 3) * 10 / 100f);
    private JLabel bombmany = new JLabel("目前炸彈數：" + bombCount);
    private int map[][] = new int[mapHeight][mapWidth];
    private boolean buttonpull[][] = new boolean[mapHeight][mapWidth];
    //    private int mapAroundBomb[][] = new int[mapHeight][mapWidth];
//    private int direct[][]={{0,0},{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}}; //8方位。
    private int timecount = 0;
    private int timeContinue = 1;
    private int count = 0;
    public boolean bomb = true;


    //    private int bombcount1 = Math.round(((mapHeight*mapWidth)*3)*10/100f);
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
//        ------------------上面介面------------------------------
        JPanel topPanel = new JPanel();
        bombmany.setText("目前炸彈有：" + bombCount);
        topPanel.add(bombmany);
        JButton restart = new JButton("新局");
        restart.setActionCommand("r");
//        restart.addMouseListener(this);  做錯惹
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                System.out.println("A");  開新局
                timecount = 1;
                timeContinue = 1;
                for (int i = 0; i < mapHeight; i++) Arrays.fill(map[i], 0);
                for (int i = 0; i < mapHeight; i++) Arrays.fill(buttonpull[i], false);
                for (int i = 0; i < mapHeight; i++) {
                    for (int j = 0; j < mapWidth; j++) {
                        jbtn[i][j].setText("");
                        jbtn[i][j].setBackground(Color.white);
                    }
                }
                setMap();
//                aroundBomb();
                bombCount = bombCount;
                bombmany.setText("目前炸彈數：" + bombCount);
            }
        });
        topPanel.add(restart);
        JLabel time = new JLabel("已經過了：0");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (timeContinue == 1) time.setText("已經過：" + (timecount++) + "秒");
            }
        };
        new Timer().scheduleAtFixedRate(timerTask, 0, 1000);
        topPanel.add(time);
        add(topPanel, BorderLayout.NORTH);


//        ----------------建立地圖-------------------------
        JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.setLayout(new GridLayout(mapHeight, mapWidth));
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                jbtn[i][j] = new JButton();
                jbtn[i][j].setActionCommand(i + " " + j);
//                jbtn[i][j].addMouseListener(this);  做錯惹  QuQ
                jbtn[i][j].addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton jtmpBtn = (JButton) e.getSource();
                        System.out.println((int) (Math.random() * 10));
//          ----------------------按鈕id--------------------------------------
                        int idI, idJ;
                        for (int i1 = 0; i1 < mapWidth; i1++) {
                            for (int j1 = 0; j1 < mapHeight; j1++) {
                                if (jtmpBtn.equals(jbtn[i1][j1])) {
                                    idI = i1;
                                    idJ = j1;
                                    System.out.println(jbtn[idI][idJ]);
                                }
                            }
                        }
//                        jbtnbomb[idI][idJ]=;

//        ---------------亂數炸彈
                        int a = (int) (Math.random() * 10);
                        int b = (int) (Math.random() * 10);
                        for (int i=0; i <= bombCount; i++) {
                            jbtnbomb[a][b] = new JButton();
                            count = 1;
                        }

//                        jtmpBtn.setBackground(Color.WHITE);
//                        if (bomb){
//                                jtmpBtn.setBackground(Color.BLUE);
//                            Frame3 frm1 = new Frame3();
//                            frm1.setVisible(true);
//                            Frame2.this.setVisible(false);
//                            }else {
//                            jtmpBtn.setBackground(Color.WHITE);
//                        }
                        if (bomb) {
                            count = 1;
                        } else {
                            count = 0;
                        }
                        switch (count) {
                            case 0:
                                jbtn[a][b].setBackground(Color.WHITE);
                                break;
                            case 1:
                                jbtn[a][b].setBackground(Color.BLUE);
                                    System.out.println(jbtn[a][b]);
//                                Frame3 frm1 = new Frame3();
//                                frm1.setVisible(true);
//                                Frame2.this.setVisible(false);

                        }
                    }
                });
                centerButtonPanel.add(jbtn[i][j]);
            }
        }
        add(centerButtonPanel, BorderLayout.CENTER);
        //        ---------------設定地圖----------------------------------------
        bombCount = bombCount;
        setMap();
//        aroundBomb();
        setVisible(true);
    }

    //    private void setMap() {
//        int count = 0;
//        while (count != bombCount) {
//            int a = (int) (Math.random() * 9);   //設定炸彈座標,1=有
//            int b = (int) (Math.random() * 9);
//            if (map[a][b] == 0) {
//                bomb = false;
//                count++;
//            }else if (map[a][b]==1)
//                bomb = true;
//        }
//    }
    private void setMap() {
        int count = 0;
        while (count != bombCount) {
            int a = (int) (Math.random() * 9);   //設定炸彈座標,1=有
            int b = (int) (Math.random() * 9);
            if (map[a][b] == 0) {
                map[a][b] = 1;
                count++;
            }
        }
    }

//    private void aroundBomb() {
//        for (int i = 0; i < mapHeight; i++) {
//            for (int j = 0; j < mapWidth; j++) {
//                if (map[i][j] == 1) {
//                    mapAroundBomb[i][j] = -1;
//                }else {
//                    for (int k = 0 ;k<direct.length;k++){
//                        int Height = i + direct[k][0], Width = j + direct[k][1];
//                        if ((Height>=0&&Height<mapWidth&&Width>=0&&Width<mapWidth)&&map[Height][Width]==1)mapAroundBomb[i][j]++;
//                    }
//                }
//            }
//        }
//    }

//    private void restart(){
//        timecount = 1;
//        timeContinue = 1;
//        for (int i = 0;i<mapHeight;i++) Arrays.fill(map[i],0);
//        for (int i = 0;i<mapHeight;i++) Arrays.fill(buttonpull[i],false);
//        for (int i = 0;i<mapHeight;i++){
//            for (int j = 0;j<mapWidth;j++){
//                jbtn[i][j].setBackground(Color.WHITE);
//                jbtn[i][j].setText("");
//            }
//        }
//        setMap();
//        aroundBomb();
//        bombCount = bombCount;
//        bombmany.setText("目前炸彈數："+bombCount);
//    }
}
