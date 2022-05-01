
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Sixx6 extends javax.swing.JFrame {

    
    public Sixx6() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon("images\\memory.png").getImage());
        pane();
        labelIcons();
        buttonIcon();
        timer();
    }
    
    int x;
    int moves = 0;
    JButton fb;
    JButton sb;
    String fCode, sCode;
    void play(java.awt.event.ActionEvent evt){
        if(x==2){
            sb = (JButton) evt.getSource();
            JLabel slabel = (JLabel) sb.getParent().getComponent(1);
            sCode = hash(slabel.hashCode()+"");
            JLabel flabel = (JLabel) fb.getParent().getComponent(1);
            fCode = hash(flabel.hashCode()+"");

            if(sCode.equals(fCode)){
                lmove.setText("Move "+ ++moves);
                if(win()){
                    t.stop();
                    JOptionPane.showMessageDialog(null, "CONGRATS! you won in "+(100-sec)+" seconds with "+moves+" moves", "",JOptionPane.PLAIN_MESSAGE);
                    int option = JOptionPane.showConfirmDialog(null, "Do you want play again?","",JOptionPane. YES_NO_OPTION);
                    if(option==JOptionPane.YES_OPTION){
                        close();
                        new Fourx5().setVisible(true);
                    }else{
                        close();
                        new LevelFrame().setVisible(true);
                    }
                }
            }
            else{
                JButton[] obj = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22
                                ,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36};
                for (int i = 0; i < obj.length; i++) {
                    obj[i].setEnabled(false);
                }
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                        Thread.sleep(400);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                        fb.setVisible(true);
                        sb.setVisible(true);
                        JButton[] obj1 = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22
                                         ,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36};
                        for (int i = 0; i < obj.length; i++) {
                            obj1[i].setEnabled(true);
                        }
                    }
                });
                t.start();
                lmove.setText("Move "+ ++moves);
            }
            x=0;
        }else{
            fb = (JButton) evt.getSource();
        }
    }
    
    String hash(String obj){
        String path = null;
        for(int i = 0; i < paths.length; i++){
            if(paths[i][0].equals(obj)){
                path = paths[i][1];
                break;
            }
        }
        return path;
    }         
    
    String paths [][] = new String[36][2];
    void labelIcons(){
        JLabel [] obj = {l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22
                        ,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35,l36};
        String [] arr = {"chameleon","chicken","cow","dog","fish","horse","ladybug","lama","lion","panda","parrot","penguin","pig","platypus","rabbit","sheep","snake","zebra",
                        "chameleon","chicken","cow","dog","fish","horse","ladybug","lama","lion","panda","parrot","penguin","pig","platypus","rabbit","sheep","snake","zebra"};
        ArrayList<Integer> randList = new ArrayList<>(36);
        int i=0;
        while(i<arr.length){
            int r = (int) (Math.random()*36);
            if(!randList.contains(r)){
                randList.add(r);
            }else{
                continue;
            }
            ImageIcon icon = new ImageIcon("6x6\\"+arr[r]+".png");
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(obj[i].getWidth(), obj[i].getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            obj[i].setIcon(scaledIcon);
            /////////////
            paths[i][0] = obj[i].hashCode()+"";
            paths[i][1] = arr[r];
            ////////////
            i++;
        }
    }
    
    Timer t;
    int sec = 40;
    int min = 1;
    void timer(){
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sec--;
                ltime.setText("Timer: "+String.format("%02d", min)+":"+String.format("%02d", sec));
                if(sec==0&&min==1){
                    min=0;
                    sec=60;
                }
                if(min==0&&sec==0){
                    t.stop();
                    JOptionPane.showMessageDialog(null, "GAME OVER!", "",JOptionPane.ERROR_MESSAGE);
                    int option = JOptionPane.showConfirmDialog(null, "Do you want play again?","",JOptionPane. YES_NO_OPTION);
                    if(option==JOptionPane.YES_OPTION){
                        close();
                        new Fourx5().setVisible(true);
                    }else{
                        close();
                        new LevelFrame().setVisible(true);
                    }
                }
            }
        });
        t.start();
    }
    
    boolean win(){
        boolean b = true;
        JButton [] obj = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22
                         ,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36};
        for(int i=0; i<obj.length; i++){
            if(obj[i].isVisible()){
                b = false;
                break;
            }
        }
        return b;
    }
    
    void buttonIcon(){
        JButton [] obj = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22
                         ,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36};
        ImageIcon icon = new ImageIcon("images\\1.png");
        Image img = icon.getImage();
        ImageIcon icon2 = new ImageIcon("images\\2.png");
        Image img2 = icon2.getImage();
        Image scaledImg = img2.getScaledInstance(l1.getWidth(), l1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        Image scaledImg2 = img.getScaledInstance(l1.getWidth(), l1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        for(int i=0; i<obj.length; i++){
            if(i%2==0){
                obj[i].setIcon(scaledIcon);
            }else{
                obj[i].setIcon(scaledIcon2);
            }
        }
    }
    
    void pane(){
        pane1.moveToBack(l1);
        pane1.moveToFront(b1);
        pane2.moveToBack(l2);
        pane2.moveToFront(b2);
        pane3.moveToBack(l3);
        pane3.moveToFront(b3);
        pane4.moveToBack(l4);
        pane4.moveToFront(b4);
        pane5.moveToBack(l5);
        pane5.moveToFront(b5);
        pane6.moveToBack(l6);
        pane6.moveToFront(b6);
        pane7.moveToBack(l7);
        pane7.moveToFront(b7);
        pane8.moveToBack(l8);
        pane8.moveToFront(b8);
        pane9.moveToBack(l9);
        pane9.moveToFront(b9);
        pane10.moveToBack(l10);
        pane10.moveToFront(b10);
        pane11.moveToBack(l11);
        pane11.moveToFront(b11);
        pane12.moveToBack(l12);
        pane12.moveToFront(b12);
        pane13.moveToBack(l13);
        pane13.moveToFront(b13);
        pane14.moveToBack(l14);
        pane14.moveToFront(b14);
        pane15.moveToBack(l15);
        pane15.moveToFront(b15);
        pane16.moveToBack(l16);
        pane16.moveToFront(b16);
        pane17.moveToBack(l17);
        pane17.moveToFront(b17);
        pane18.moveToBack(l18);
        pane18.moveToFront(b18);
        pane19.moveToBack(l19);
        pane19.moveToFront(b19);
        pane20.moveToBack(l20);
        pane20.moveToFront(b20);
        pane21.moveToBack(l21);
        pane21.moveToFront(b21);
        pane22.moveToBack(l22);
        pane22.moveToFront(b22);
        pane23.moveToBack(l23);
        pane23.moveToFront(b23);
        pane24.moveToBack(l24);
        pane24.moveToFront(b24);
        pane25.moveToBack(l25);
        pane25.moveToFront(b25);
        pane26.moveToBack(l26);
        pane26.moveToFront(b26);
        pane27.moveToBack(l27);
        pane27.moveToFront(b27);
        pane28.moveToBack(l28);
        pane28.moveToFront(b28);
        pane29.moveToBack(l29);
        pane29.moveToFront(b29);
        pane30.moveToBack(l30);
        pane30.moveToFront(b30);
        pane31.moveToBack(l31);
        pane31.moveToFront(b31);
        pane32.moveToBack(l32);
        pane32.moveToFront(b32);
        pane33.moveToBack(l33);
        pane33.moveToFront(b33);
        pane34.moveToBack(l34);
        pane34.moveToFront(b34);
        pane35.moveToBack(l35);
        pane35.moveToFront(b35);
        pane36.moveToBack(l36);
        pane36.moveToFront(b36);
    }
    
    public void close(){
            WindowEvent we = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pane1 = new javax.swing.JLayeredPane();
        b1 = new javax.swing.JButton();
        l1 = new javax.swing.JLabel();
        pane2 = new javax.swing.JLayeredPane();
        b2 = new javax.swing.JButton();
        l2 = new javax.swing.JLabel();
        pane3 = new javax.swing.JLayeredPane();
        b3 = new javax.swing.JButton();
        l3 = new javax.swing.JLabel();
        pane4 = new javax.swing.JLayeredPane();
        b4 = new javax.swing.JButton();
        l4 = new javax.swing.JLabel();
        pane5 = new javax.swing.JLayeredPane();
        b5 = new javax.swing.JButton();
        l5 = new javax.swing.JLabel();
        pane6 = new javax.swing.JLayeredPane();
        b6 = new javax.swing.JButton();
        l6 = new javax.swing.JLabel();
        pane7 = new javax.swing.JLayeredPane();
        b7 = new javax.swing.JButton();
        l7 = new javax.swing.JLabel();
        pane8 = new javax.swing.JLayeredPane();
        b8 = new javax.swing.JButton();
        l8 = new javax.swing.JLabel();
        pane9 = new javax.swing.JLayeredPane();
        b9 = new javax.swing.JButton();
        l9 = new javax.swing.JLabel();
        pane10 = new javax.swing.JLayeredPane();
        b10 = new javax.swing.JButton();
        l10 = new javax.swing.JLabel();
        pane11 = new javax.swing.JLayeredPane();
        b11 = new javax.swing.JButton();
        l11 = new javax.swing.JLabel();
        pane12 = new javax.swing.JLayeredPane();
        b12 = new javax.swing.JButton();
        l12 = new javax.swing.JLabel();
        pane13 = new javax.swing.JLayeredPane();
        b13 = new javax.swing.JButton();
        l13 = new javax.swing.JLabel();
        pane14 = new javax.swing.JLayeredPane();
        b14 = new javax.swing.JButton();
        l14 = new javax.swing.JLabel();
        pane15 = new javax.swing.JLayeredPane();
        b15 = new javax.swing.JButton();
        l15 = new javax.swing.JLabel();
        pane16 = new javax.swing.JLayeredPane();
        b16 = new javax.swing.JButton();
        l16 = new javax.swing.JLabel();
        pane17 = new javax.swing.JLayeredPane();
        b17 = new javax.swing.JButton();
        l17 = new javax.swing.JLabel();
        pane18 = new javax.swing.JLayeredPane();
        b18 = new javax.swing.JButton();
        l18 = new javax.swing.JLabel();
        pane19 = new javax.swing.JLayeredPane();
        b19 = new javax.swing.JButton();
        l19 = new javax.swing.JLabel();
        pane20 = new javax.swing.JLayeredPane();
        b20 = new javax.swing.JButton();
        l20 = new javax.swing.JLabel();
        pane21 = new javax.swing.JLayeredPane();
        b21 = new javax.swing.JButton();
        l21 = new javax.swing.JLabel();
        pane22 = new javax.swing.JLayeredPane();
        b22 = new javax.swing.JButton();
        l22 = new javax.swing.JLabel();
        pane23 = new javax.swing.JLayeredPane();
        b23 = new javax.swing.JButton();
        l23 = new javax.swing.JLabel();
        pane24 = new javax.swing.JLayeredPane();
        b24 = new javax.swing.JButton();
        l24 = new javax.swing.JLabel();
        pane25 = new javax.swing.JLayeredPane();
        b25 = new javax.swing.JButton();
        l25 = new javax.swing.JLabel();
        pane26 = new javax.swing.JLayeredPane();
        b26 = new javax.swing.JButton();
        l26 = new javax.swing.JLabel();
        pane27 = new javax.swing.JLayeredPane();
        b27 = new javax.swing.JButton();
        l27 = new javax.swing.JLabel();
        pane28 = new javax.swing.JLayeredPane();
        b28 = new javax.swing.JButton();
        l28 = new javax.swing.JLabel();
        pane29 = new javax.swing.JLayeredPane();
        b29 = new javax.swing.JButton();
        l29 = new javax.swing.JLabel();
        pane30 = new javax.swing.JLayeredPane();
        b30 = new javax.swing.JButton();
        l30 = new javax.swing.JLabel();
        pane31 = new javax.swing.JLayeredPane();
        b31 = new javax.swing.JButton();
        l31 = new javax.swing.JLabel();
        pane32 = new javax.swing.JLayeredPane();
        b32 = new javax.swing.JButton();
        l32 = new javax.swing.JLabel();
        pane33 = new javax.swing.JLayeredPane();
        b33 = new javax.swing.JButton();
        l33 = new javax.swing.JLabel();
        pane34 = new javax.swing.JLayeredPane();
        b34 = new javax.swing.JButton();
        l34 = new javax.swing.JLabel();
        pane35 = new javax.swing.JLayeredPane();
        b35 = new javax.swing.JButton();
        l35 = new javax.swing.JLabel();
        pane36 = new javax.swing.JLayeredPane();
        b36 = new javax.swing.JButton();
        l36 = new javax.swing.JLabel();
        ltime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lmove = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MemoryGame");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(6, 6, 5, 5));

        pane1.setBackground(new java.awt.Color(255, 255, 255));
        pane1.setOpaque(true);

        b1.setBackground(new java.awt.Color(68, 81, 216));
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setFocusable(false);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane1.setLayer(b1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane1.setLayer(l1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane1Layout = new javax.swing.GroupLayout(pane1);
        pane1.setLayout(pane1Layout);
        pane1Layout.setHorizontalGroup(
            pane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane1Layout.setVerticalGroup(
            pane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane1);

        pane2.setBackground(new java.awt.Color(255, 255, 255));
        pane2.setOpaque(true);

        b2.setBackground(new java.awt.Color(255, 255, 255));
        b2.setFocusable(false);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        l2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane2.setLayer(b2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane2.setLayer(l2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane2Layout = new javax.swing.GroupLayout(pane2);
        pane2.setLayout(pane2Layout);
        pane2Layout.setHorizontalGroup(
            pane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane2Layout.setVerticalGroup(
            pane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane2);

        pane3.setBackground(new java.awt.Color(255, 255, 255));
        pane3.setOpaque(true);

        b3.setBackground(new java.awt.Color(68, 81, 216));
        b3.setFocusable(false);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        l3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane3.setLayer(b3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane3.setLayer(l3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane3Layout = new javax.swing.GroupLayout(pane3);
        pane3.setLayout(pane3Layout);
        pane3Layout.setHorizontalGroup(
            pane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane3Layout.setVerticalGroup(
            pane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane3);

        pane4.setBackground(new java.awt.Color(255, 255, 255));
        pane4.setOpaque(true);

        b4.setBackground(new java.awt.Color(255, 255, 255));
        b4.setFocusable(false);
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        l4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane4.setLayer(b4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane4.setLayer(l4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane4Layout = new javax.swing.GroupLayout(pane4);
        pane4.setLayout(pane4Layout);
        pane4Layout.setHorizontalGroup(
            pane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane4Layout.setVerticalGroup(
            pane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane4);

        pane5.setBackground(new java.awt.Color(255, 255, 255));
        pane5.setOpaque(true);

        b5.setBackground(new java.awt.Color(68, 81, 216));
        b5.setFocusable(false);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        l5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane5.setLayer(b5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane5.setLayer(l5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane5Layout = new javax.swing.GroupLayout(pane5);
        pane5.setLayout(pane5Layout);
        pane5Layout.setHorizontalGroup(
            pane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane5Layout.setVerticalGroup(
            pane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane5);

        pane6.setBackground(new java.awt.Color(255, 255, 255));
        pane6.setOpaque(true);

        b6.setBackground(new java.awt.Color(255, 255, 255));
        b6.setFocusable(false);
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        l6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane6.setLayer(b6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane6.setLayer(l6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane6Layout = new javax.swing.GroupLayout(pane6);
        pane6.setLayout(pane6Layout);
        pane6Layout.setHorizontalGroup(
            pane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane6Layout.setVerticalGroup(
            pane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane6);

        pane7.setBackground(new java.awt.Color(255, 255, 255));
        pane7.setOpaque(true);

        b7.setBackground(new java.awt.Color(68, 81, 216));
        b7.setFocusable(false);
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        l7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane7.setLayer(b7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane7.setLayer(l7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane7Layout = new javax.swing.GroupLayout(pane7);
        pane7.setLayout(pane7Layout);
        pane7Layout.setHorizontalGroup(
            pane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b7, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane7Layout.setVerticalGroup(
            pane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b7, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane7);

        pane8.setBackground(new java.awt.Color(255, 255, 255));
        pane8.setOpaque(true);

        b8.setBackground(new java.awt.Color(255, 255, 255));
        b8.setFocusable(false);
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        l8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane8.setLayer(b8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane8.setLayer(l8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane8Layout = new javax.swing.GroupLayout(pane8);
        pane8.setLayout(pane8Layout);
        pane8Layout.setHorizontalGroup(
            pane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b8, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane8Layout.setVerticalGroup(
            pane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b8, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane8);

        pane9.setBackground(new java.awt.Color(255, 255, 255));
        pane9.setOpaque(true);

        b9.setBackground(new java.awt.Color(68, 81, 216));
        b9.setFocusable(false);
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });

        l9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane9.setLayer(b9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane9.setLayer(l9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane9Layout = new javax.swing.GroupLayout(pane9);
        pane9.setLayout(pane9Layout);
        pane9Layout.setHorizontalGroup(
            pane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b9, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane9Layout.setVerticalGroup(
            pane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b9, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane9);

        pane10.setBackground(new java.awt.Color(255, 255, 255));
        pane10.setOpaque(true);

        b10.setBackground(new java.awt.Color(255, 255, 255));
        b10.setFocusable(false);
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });

        l10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane10.setLayer(b10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane10.setLayer(l10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane10Layout = new javax.swing.GroupLayout(pane10);
        pane10.setLayout(pane10Layout);
        pane10Layout.setHorizontalGroup(
            pane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b10, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane10Layout.setVerticalGroup(
            pane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b10, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane10);

        pane11.setBackground(new java.awt.Color(255, 255, 255));
        pane11.setOpaque(true);

        b11.setBackground(new java.awt.Color(68, 81, 216));
        b11.setFocusable(false);
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });

        l11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane11.setLayer(b11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane11.setLayer(l11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane11Layout = new javax.swing.GroupLayout(pane11);
        pane11.setLayout(pane11Layout);
        pane11Layout.setHorizontalGroup(
            pane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b11, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(l11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane11Layout.setVerticalGroup(
            pane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b11, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addComponent(l11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(pane11);

        pane12.setBackground(new java.awt.Color(255, 255, 255));
        pane12.setOpaque(true);

        b12.setBackground(new java.awt.Color(255, 255, 255));
        b12.setFocusable(false);
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });

        l12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane12.setLayer(b12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane12.setLayer(l12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane12Layout = new javax.swing.GroupLayout(pane12);
        pane12.setLayout(pane12Layout);
        pane12Layout.setHorizontalGroup(
            pane12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b12, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane12Layout.setVerticalGroup(
            pane12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b12, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane12);

        pane13.setBackground(new java.awt.Color(255, 255, 255));
        pane13.setOpaque(true);

        b13.setBackground(new java.awt.Color(68, 81, 216));
        b13.setForeground(new java.awt.Color(255, 255, 255));
        b13.setFocusable(false);
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });

        l13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane13.setLayer(b13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane13.setLayer(l13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane13Layout = new javax.swing.GroupLayout(pane13);
        pane13.setLayout(pane13Layout);
        pane13Layout.setHorizontalGroup(
            pane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b13, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane13Layout.setVerticalGroup(
            pane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b13, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane13);

        pane14.setBackground(new java.awt.Color(255, 255, 255));
        pane14.setOpaque(true);

        b14.setBackground(new java.awt.Color(255, 255, 255));
        b14.setForeground(new java.awt.Color(255, 255, 255));
        b14.setFocusable(false);
        b14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b14ActionPerformed(evt);
            }
        });

        l14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane14.setLayer(b14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane14.setLayer(l14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane14Layout = new javax.swing.GroupLayout(pane14);
        pane14.setLayout(pane14Layout);
        pane14Layout.setHorizontalGroup(
            pane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b14, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane14Layout.setVerticalGroup(
            pane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b14, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane14);

        pane15.setBackground(new java.awt.Color(255, 255, 255));
        pane15.setOpaque(true);

        b15.setBackground(new java.awt.Color(68, 81, 216));
        b15.setForeground(new java.awt.Color(255, 255, 255));
        b15.setFocusable(false);
        b15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b15ActionPerformed(evt);
            }
        });

        l15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane15.setLayer(b15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane15.setLayer(l15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane15Layout = new javax.swing.GroupLayout(pane15);
        pane15.setLayout(pane15Layout);
        pane15Layout.setHorizontalGroup(
            pane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b15, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane15Layout.setVerticalGroup(
            pane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b15, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane15);

        pane16.setBackground(new java.awt.Color(255, 255, 255));
        pane16.setOpaque(true);

        b16.setBackground(new java.awt.Color(255, 255, 255));
        b16.setForeground(new java.awt.Color(255, 255, 255));
        b16.setFocusable(false);
        b16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b16ActionPerformed(evt);
            }
        });

        l16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane16.setLayer(b16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane16.setLayer(l16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane16Layout = new javax.swing.GroupLayout(pane16);
        pane16.setLayout(pane16Layout);
        pane16Layout.setHorizontalGroup(
            pane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b16, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane16Layout.setVerticalGroup(
            pane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b16, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane16);

        pane17.setBackground(new java.awt.Color(255, 255, 255));
        pane17.setOpaque(true);

        b17.setBackground(new java.awt.Color(68, 81, 216));
        b17.setForeground(new java.awt.Color(255, 255, 255));
        b17.setFocusable(false);
        b17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b17ActionPerformed(evt);
            }
        });

        l17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane17.setLayer(b17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane17.setLayer(l17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane17Layout = new javax.swing.GroupLayout(pane17);
        pane17.setLayout(pane17Layout);
        pane17Layout.setHorizontalGroup(
            pane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b17, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane17Layout.setVerticalGroup(
            pane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b17, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane17);

        pane18.setBackground(new java.awt.Color(255, 255, 255));
        pane18.setOpaque(true);

        b18.setBackground(new java.awt.Color(255, 255, 255));
        b18.setForeground(new java.awt.Color(255, 255, 255));
        b18.setFocusable(false);
        b18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b18ActionPerformed(evt);
            }
        });

        l18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane18.setLayer(b18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane18.setLayer(l18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane18Layout = new javax.swing.GroupLayout(pane18);
        pane18.setLayout(pane18Layout);
        pane18Layout.setHorizontalGroup(
            pane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b18, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane18Layout.setVerticalGroup(
            pane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b18, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane18);

        pane19.setBackground(new java.awt.Color(255, 255, 255));
        pane19.setOpaque(true);

        b19.setBackground(new java.awt.Color(68, 81, 216));
        b19.setForeground(new java.awt.Color(255, 255, 255));
        b19.setFocusable(false);
        b19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b19ActionPerformed(evt);
            }
        });

        l19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane19.setLayer(b19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane19.setLayer(l19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane19Layout = new javax.swing.GroupLayout(pane19);
        pane19.setLayout(pane19Layout);
        pane19Layout.setHorizontalGroup(
            pane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b19, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane19Layout.setVerticalGroup(
            pane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b19, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane19);

        pane20.setBackground(new java.awt.Color(255, 255, 255));
        pane20.setOpaque(true);

        b20.setBackground(new java.awt.Color(255, 255, 255));
        b20.setForeground(new java.awt.Color(255, 255, 255));
        b20.setFocusable(false);
        b20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b20ActionPerformed(evt);
            }
        });

        l20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane20.setLayer(b20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane20.setLayer(l20, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane20Layout = new javax.swing.GroupLayout(pane20);
        pane20.setLayout(pane20Layout);
        pane20Layout.setHorizontalGroup(
            pane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b20, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane20Layout.setVerticalGroup(
            pane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b20, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane20);

        pane21.setBackground(new java.awt.Color(255, 255, 255));
        pane21.setOpaque(true);

        b21.setBackground(new java.awt.Color(68, 81, 216));
        b21.setForeground(new java.awt.Color(255, 255, 255));
        b21.setFocusable(false);
        b21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b21ActionPerformed(evt);
            }
        });

        l21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane21.setLayer(b21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane21.setLayer(l21, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane21Layout = new javax.swing.GroupLayout(pane21);
        pane21.setLayout(pane21Layout);
        pane21Layout.setHorizontalGroup(
            pane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b21, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane21Layout.setVerticalGroup(
            pane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b21, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane21);

        pane22.setBackground(new java.awt.Color(255, 255, 255));
        pane22.setOpaque(true);

        b22.setBackground(new java.awt.Color(255, 255, 255));
        b22.setForeground(new java.awt.Color(255, 255, 255));
        b22.setFocusable(false);
        b22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b22ActionPerformed(evt);
            }
        });

        l22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane22.setLayer(b22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane22.setLayer(l22, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane22Layout = new javax.swing.GroupLayout(pane22);
        pane22.setLayout(pane22Layout);
        pane22Layout.setHorizontalGroup(
            pane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b22, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane22Layout.setVerticalGroup(
            pane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b22, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane22);

        pane23.setBackground(new java.awt.Color(255, 255, 255));
        pane23.setOpaque(true);

        b23.setBackground(new java.awt.Color(68, 81, 216));
        b23.setForeground(new java.awt.Color(255, 255, 255));
        b23.setFocusable(false);
        b23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b23ActionPerformed(evt);
            }
        });

        l23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane23.setLayer(b23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane23.setLayer(l23, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane23Layout = new javax.swing.GroupLayout(pane23);
        pane23.setLayout(pane23Layout);
        pane23Layout.setHorizontalGroup(
            pane23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b23, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane23Layout.setVerticalGroup(
            pane23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b23, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane23);

        pane24.setBackground(new java.awt.Color(255, 255, 255));
        pane24.setOpaque(true);

        b24.setBackground(new java.awt.Color(255, 255, 255));
        b24.setForeground(new java.awt.Color(255, 255, 255));
        b24.setFocusable(false);
        b24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b24ActionPerformed(evt);
            }
        });

        l24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane24.setLayer(b24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane24.setLayer(l24, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane24Layout = new javax.swing.GroupLayout(pane24);
        pane24.setLayout(pane24Layout);
        pane24Layout.setHorizontalGroup(
            pane24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b24, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane24Layout.setVerticalGroup(
            pane24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b24, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane24);

        pane25.setBackground(new java.awt.Color(255, 255, 255));
        pane25.setOpaque(true);

        b25.setBackground(new java.awt.Color(68, 81, 216));
        b25.setForeground(new java.awt.Color(255, 255, 255));
        b25.setFocusable(false);
        b25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b25ActionPerformed(evt);
            }
        });

        l25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane25.setLayer(b25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane25.setLayer(l25, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane25Layout = new javax.swing.GroupLayout(pane25);
        pane25.setLayout(pane25Layout);
        pane25Layout.setHorizontalGroup(
            pane25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b25, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane25Layout.setVerticalGroup(
            pane25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b25, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane25);

        pane26.setBackground(new java.awt.Color(255, 255, 255));
        pane26.setOpaque(true);

        b26.setBackground(new java.awt.Color(255, 255, 255));
        b26.setForeground(new java.awt.Color(255, 255, 255));
        b26.setFocusable(false);
        b26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b26ActionPerformed(evt);
            }
        });

        l26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane26.setLayer(b26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane26.setLayer(l26, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane26Layout = new javax.swing.GroupLayout(pane26);
        pane26.setLayout(pane26Layout);
        pane26Layout.setHorizontalGroup(
            pane26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b26, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane26Layout.setVerticalGroup(
            pane26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b26, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane26);

        pane27.setBackground(new java.awt.Color(255, 255, 255));
        pane27.setOpaque(true);

        b27.setBackground(new java.awt.Color(68, 81, 216));
        b27.setForeground(new java.awt.Color(255, 255, 255));
        b27.setFocusable(false);
        b27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b27ActionPerformed(evt);
            }
        });

        l27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane27.setLayer(b27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane27.setLayer(l27, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane27Layout = new javax.swing.GroupLayout(pane27);
        pane27.setLayout(pane27Layout);
        pane27Layout.setHorizontalGroup(
            pane27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b27, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane27Layout.setVerticalGroup(
            pane27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b27, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane27);

        pane28.setBackground(new java.awt.Color(255, 255, 255));
        pane28.setOpaque(true);

        b28.setBackground(new java.awt.Color(255, 255, 255));
        b28.setForeground(new java.awt.Color(255, 255, 255));
        b28.setFocusable(false);
        b28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b28ActionPerformed(evt);
            }
        });

        l28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane28.setLayer(b28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane28.setLayer(l28, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane28Layout = new javax.swing.GroupLayout(pane28);
        pane28.setLayout(pane28Layout);
        pane28Layout.setHorizontalGroup(
            pane28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b28, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane28Layout.setVerticalGroup(
            pane28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b28, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane28);

        pane29.setBackground(new java.awt.Color(255, 255, 255));
        pane29.setOpaque(true);

        b29.setBackground(new java.awt.Color(68, 81, 216));
        b29.setForeground(new java.awt.Color(255, 255, 255));
        b29.setFocusable(false);
        b29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b29ActionPerformed(evt);
            }
        });

        l29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane29.setLayer(b29, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane29.setLayer(l29, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane29Layout = new javax.swing.GroupLayout(pane29);
        pane29.setLayout(pane29Layout);
        pane29Layout.setHorizontalGroup(
            pane29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b29, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane29Layout.setVerticalGroup(
            pane29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b29, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane29);

        pane30.setBackground(new java.awt.Color(255, 255, 255));
        pane30.setOpaque(true);

        b30.setBackground(new java.awt.Color(255, 255, 255));
        b30.setForeground(new java.awt.Color(255, 255, 255));
        b30.setFocusable(false);
        b30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b30ActionPerformed(evt);
            }
        });

        l30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane30.setLayer(b30, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane30.setLayer(l30, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane30Layout = new javax.swing.GroupLayout(pane30);
        pane30.setLayout(pane30Layout);
        pane30Layout.setHorizontalGroup(
            pane30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b30, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane30Layout.setVerticalGroup(
            pane30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b30, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane30);

        pane31.setBackground(new java.awt.Color(255, 255, 255));
        pane31.setOpaque(true);

        b31.setBackground(new java.awt.Color(68, 81, 216));
        b31.setForeground(new java.awt.Color(255, 255, 255));
        b31.setFocusable(false);
        b31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b31ActionPerformed(evt);
            }
        });

        l31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane31.setLayer(b31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane31.setLayer(l31, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane31Layout = new javax.swing.GroupLayout(pane31);
        pane31.setLayout(pane31Layout);
        pane31Layout.setHorizontalGroup(
            pane31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b31, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane31Layout.setVerticalGroup(
            pane31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b31, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane31);

        pane32.setBackground(new java.awt.Color(255, 255, 255));
        pane32.setOpaque(true);

        b32.setBackground(new java.awt.Color(255, 255, 255));
        b32.setForeground(new java.awt.Color(255, 255, 255));
        b32.setFocusable(false);
        b32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b32ActionPerformed(evt);
            }
        });

        l32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane32.setLayer(b32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane32.setLayer(l32, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane32Layout = new javax.swing.GroupLayout(pane32);
        pane32.setLayout(pane32Layout);
        pane32Layout.setHorizontalGroup(
            pane32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b32, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane32Layout.setVerticalGroup(
            pane32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b32, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane32);

        pane33.setBackground(new java.awt.Color(255, 255, 255));
        pane33.setOpaque(true);

        b33.setBackground(new java.awt.Color(68, 81, 216));
        b33.setForeground(new java.awt.Color(255, 255, 255));
        b33.setFocusable(false);
        b33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b33ActionPerformed(evt);
            }
        });

        l33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane33.setLayer(b33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane33.setLayer(l33, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane33Layout = new javax.swing.GroupLayout(pane33);
        pane33.setLayout(pane33Layout);
        pane33Layout.setHorizontalGroup(
            pane33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b33, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane33Layout.setVerticalGroup(
            pane33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b33, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane33);

        pane34.setBackground(new java.awt.Color(255, 255, 255));
        pane34.setOpaque(true);

        b34.setBackground(new java.awt.Color(255, 255, 255));
        b34.setForeground(new java.awt.Color(255, 255, 255));
        b34.setFocusable(false);
        b34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b34ActionPerformed(evt);
            }
        });

        l34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane34.setLayer(b34, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane34.setLayer(l34, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane34Layout = new javax.swing.GroupLayout(pane34);
        pane34.setLayout(pane34Layout);
        pane34Layout.setHorizontalGroup(
            pane34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b34, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane34Layout.setVerticalGroup(
            pane34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b34, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane34);

        pane35.setBackground(new java.awt.Color(255, 255, 255));
        pane35.setOpaque(true);

        b35.setBackground(new java.awt.Color(68, 81, 216));
        b35.setForeground(new java.awt.Color(255, 255, 255));
        b35.setFocusable(false);
        b35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b35ActionPerformed(evt);
            }
        });

        l35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane35.setLayer(b35, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane35.setLayer(l35, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane35Layout = new javax.swing.GroupLayout(pane35);
        pane35.setLayout(pane35Layout);
        pane35Layout.setHorizontalGroup(
            pane35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b35, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane35Layout.setVerticalGroup(
            pane35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b35, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane35);

        pane36.setBackground(new java.awt.Color(255, 255, 255));
        pane36.setOpaque(true);

        b36.setBackground(new java.awt.Color(255, 255, 255));
        b36.setForeground(new java.awt.Color(255, 255, 255));
        b36.setFocusable(false);
        b36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b36ActionPerformed(evt);
            }
        });

        l36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pane36.setLayer(b36, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane36.setLayer(l36, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pane36Layout = new javax.swing.GroupLayout(pane36);
        pane36.setLayout(pane36Layout);
        pane36Layout.setHorizontalGroup(
            pane36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b36, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        pane36Layout.setVerticalGroup(
            pane36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b36, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel2.add(pane36);

        ltime.setBackground(new java.awt.Color(255, 255, 255));
        ltime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ltime.setForeground(new java.awt.Color(82, 94, 221));
        ltime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltime.setText("Timer: 1:40");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(82, 94, 221));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("MemoryGame");

        lmove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lmove.setForeground(new java.awt.Color(82, 94, 221));
        lmove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lmove.setText("Move:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ltime, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lmove, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                        .addGap(140, 140, 140))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lmove, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(ltime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
        b1.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
        b2.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
        b3.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:
        b4.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
        b5.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
        b6.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
        b7.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
        b8.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:
        b9.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b9ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        // TODO add your handling code here:
        b10.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b10ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        // TODO add your handling code here:
        b11.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b11ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        // TODO add your handling code here:
        b12.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b12ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
        // TODO add your handling code here:
        b13.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b13ActionPerformed

    private void b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14ActionPerformed
        // TODO add your handling code here:
        b14.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b14ActionPerformed

    private void b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15ActionPerformed
        // TODO add your handling code here:
        b15.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b15ActionPerformed

    private void b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16ActionPerformed
        // TODO add your handling code here:
        b16.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b16ActionPerformed

    private void b17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b17ActionPerformed
        // TODO add your handling code here:
        b17.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b17ActionPerformed

    private void b18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b18ActionPerformed
        // TODO add your handling code here:
        b18.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b18ActionPerformed

    private void b19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b19ActionPerformed
        // TODO add your handling code here:
        b19.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b19ActionPerformed

    private void b20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b20ActionPerformed
        // TODO add your handling code here:
        b20.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b20ActionPerformed

    private void b21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b21ActionPerformed
        // TODO add your handling code here:
        b21.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b21ActionPerformed

    private void b22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b22ActionPerformed
        // TODO add your handling code here:
        b22.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b22ActionPerformed

    private void b23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b23ActionPerformed
        // TODO add your handling code here:
        b23.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b23ActionPerformed

    private void b24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b24ActionPerformed
        // TODO add your handling code here:
        b24.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b24ActionPerformed

    private void b25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b25ActionPerformed
        // TODO add your handling code here:
        b25.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b25ActionPerformed

    private void b26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b26ActionPerformed
        // TODO add your handling code here:
        b26.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b26ActionPerformed

    private void b27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b27ActionPerformed
        // TODO add your handling code here:
        b27.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b27ActionPerformed

    private void b28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b28ActionPerformed
        // TODO add your handling code here:
        b28.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b28ActionPerformed

    private void b29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b29ActionPerformed
        // TODO add your handling code here:
        b29.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b29ActionPerformed

    private void b30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b30ActionPerformed
        // TODO add your handling code here:
        b30.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b30ActionPerformed

    private void b31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b31ActionPerformed
        // TODO add your handling code here:
        b31.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b31ActionPerformed

    private void b32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b32ActionPerformed
        // TODO add your handling code here:
        b32.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b32ActionPerformed

    private void b33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b33ActionPerformed
        // TODO add your handling code here:
        b33.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b33ActionPerformed

    private void b34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b34ActionPerformed
        // TODO add your handling code here:
        b34.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b34ActionPerformed

    private void b35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b35ActionPerformed
        // TODO add your handling code here:
        b35.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b35ActionPerformed

    private void b36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b36ActionPerformed
        // TODO add your handling code here:
        b36.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b36ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sixx6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sixx6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sixx6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sixx6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sixx6().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b17;
    private javax.swing.JButton b18;
    private javax.swing.JButton b19;
    private javax.swing.JButton b2;
    private javax.swing.JButton b20;
    private javax.swing.JButton b21;
    private javax.swing.JButton b22;
    private javax.swing.JButton b23;
    private javax.swing.JButton b24;
    private javax.swing.JButton b25;
    private javax.swing.JButton b26;
    private javax.swing.JButton b27;
    private javax.swing.JButton b28;
    private javax.swing.JButton b29;
    private javax.swing.JButton b3;
    private javax.swing.JButton b30;
    private javax.swing.JButton b31;
    private javax.swing.JButton b32;
    private javax.swing.JButton b33;
    private javax.swing.JButton b34;
    private javax.swing.JButton b35;
    private javax.swing.JButton b36;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l10;
    private javax.swing.JLabel l11;
    private javax.swing.JLabel l12;
    private javax.swing.JLabel l13;
    private javax.swing.JLabel l14;
    private javax.swing.JLabel l15;
    private javax.swing.JLabel l16;
    private javax.swing.JLabel l17;
    private javax.swing.JLabel l18;
    private javax.swing.JLabel l19;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l20;
    private javax.swing.JLabel l21;
    private javax.swing.JLabel l22;
    private javax.swing.JLabel l23;
    private javax.swing.JLabel l24;
    private javax.swing.JLabel l25;
    private javax.swing.JLabel l26;
    private javax.swing.JLabel l27;
    private javax.swing.JLabel l28;
    private javax.swing.JLabel l29;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l30;
    private javax.swing.JLabel l31;
    private javax.swing.JLabel l32;
    private javax.swing.JLabel l33;
    private javax.swing.JLabel l34;
    private javax.swing.JLabel l35;
    private javax.swing.JLabel l36;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JLabel lmove;
    private javax.swing.JLabel ltime;
    private javax.swing.JLayeredPane pane1;
    private javax.swing.JLayeredPane pane10;
    private javax.swing.JLayeredPane pane11;
    private javax.swing.JLayeredPane pane12;
    private javax.swing.JLayeredPane pane13;
    private javax.swing.JLayeredPane pane14;
    private javax.swing.JLayeredPane pane15;
    private javax.swing.JLayeredPane pane16;
    private javax.swing.JLayeredPane pane17;
    private javax.swing.JLayeredPane pane18;
    private javax.swing.JLayeredPane pane19;
    private javax.swing.JLayeredPane pane2;
    private javax.swing.JLayeredPane pane20;
    private javax.swing.JLayeredPane pane21;
    private javax.swing.JLayeredPane pane22;
    private javax.swing.JLayeredPane pane23;
    private javax.swing.JLayeredPane pane24;
    private javax.swing.JLayeredPane pane25;
    private javax.swing.JLayeredPane pane26;
    private javax.swing.JLayeredPane pane27;
    private javax.swing.JLayeredPane pane28;
    private javax.swing.JLayeredPane pane29;
    private javax.swing.JLayeredPane pane3;
    private javax.swing.JLayeredPane pane30;
    private javax.swing.JLayeredPane pane31;
    private javax.swing.JLayeredPane pane32;
    private javax.swing.JLayeredPane pane33;
    private javax.swing.JLayeredPane pane34;
    private javax.swing.JLayeredPane pane35;
    private javax.swing.JLayeredPane pane36;
    private javax.swing.JLayeredPane pane4;
    private javax.swing.JLayeredPane pane5;
    private javax.swing.JLayeredPane pane6;
    private javax.swing.JLayeredPane pane7;
    private javax.swing.JLayeredPane pane8;
    private javax.swing.JLayeredPane pane9;
    // End of variables declaration//GEN-END:variables
}
