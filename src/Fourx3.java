
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.sound.sampled.*;
import javax.swing.*;

public class Fourx3 extends javax.swing.JFrame {
    
    public Fourx3() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon("images\\memory.png").getImage());
        pane();
        labelIcons();
        buttonIcon();
        timer();
    }
    
    int x = 0;
    int moves = 0;
    JButton fb;
    JButton sb;
    String fCode, sCode;

    void play(java.awt.event.ActionEvent evt) {
        if (x == 2) {
            sb = (JButton) evt.getSource();
            JLabel slabel = (JLabel) sb.getParent().getComponent(1);
            sCode = hash(slabel.hashCode() + "");
            JLabel flabel = (JLabel) fb.getParent().getComponent(1);
            fCode = hash(flabel.hashCode() + "");
            
            if (sCode.equals(fCode)) {
                lmove.setText("Move " + ++moves);
                playSound("sounds\\match.wav");
                if (win()) {
                    playSound("sounds\\win.wav");
                    t.stop();
                    JOptionPane.showMessageDialog(null, "CONGRATS! you won in " + (30 - sec) + " seconds with " + moves + " moves", "", JOptionPane.PLAIN_MESSAGE);
                    int option = JOptionPane.showConfirmDialog(null, "Do you want play again?", "", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        close();
                        new Fourx3().setVisible(true);
                    } else {
                        close();
                        new LevelFrame().setVisible(true);
                    }
                }
            } else {
                JButton[] obj = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};
                for (int i = 0; i < obj.length; i++) {
                    obj[i].setEnabled(false);
                }
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(350);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        fb.setVisible(true);
                        sb.setVisible(true);
                        JButton[] obj1 = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};
                        for (int i = 0; i < obj.length; i++) {
                            obj1[i].setEnabled(true);
                        }
                    }
                });
                t.start();
                lmove.setText("Move " + ++moves);
            }
            x = 0;
        } else {
            fb = (JButton) evt.getSource();
        }
    }
    
    String hash(String obj) {
        String path = null;
        for (int i = 0; i < paths.length; i++) {
            if (paths[i][0].equals(obj)) {
                path = paths[i][1];
                break;
            }
        }
        return path;
    }    
    
    String paths[][] = new String[12][2];

    void labelIcons() {
        JLabel[] obj = {l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12};
        String[] arr = {"finn", "freddys", "smurf", "kermit", "jake", "simpson", "finn", "freddys", "smurf", "kermit", "jake", "simpson"};
        ArrayList<Integer> randList = new ArrayList<>(12);
        int i = 0;
        while (i < arr.length) {
            int r = (int) (Math.random() * 12);
            if (!randList.contains(r)) {
                randList.add(r);
            } else {
                continue;
            }
            ImageIcon icon = new ImageIcon("4x3\\" + arr[r] + ".png");
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(obj[i].getWidth(), obj[i].getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            obj[i].setIcon(scaledIcon);
            /////////////
            paths[i][0] = obj[i].hashCode() + "";
            paths[i][1] = arr[r];
            ////////////
            i++;
        }
    }
    
    Timer t;
    int sec = 30;
    int min = 0;

    void timer() {
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sec--;
                ltime.setText("Timer: " + String.format("%02d", min) + ":" + String.format("%02d", sec));
                if (sec == 0) {
                    playSound("sounds\\game-over.wav");
                    t.stop();
                    JOptionPane.showMessageDialog(null, "GAME OVER!", "", JOptionPane.ERROR_MESSAGE);
                    int option = JOptionPane.showConfirmDialog(null, "Do you want play again?", "", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        close();
                        new Fourx3().setVisible(true);
                    } else {
                        close();
                        new LevelFrame().setVisible(true);
                    }
                }
            }
        });
        t.start();
    }
    
    boolean win() {
        boolean b = true;
        JButton[] obj = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};
        for (int i = 0; i < obj.length; i++) {
            if (obj[i].isVisible()) {
                b = false;
                break;
            }
        }
        return b;
    }
    
    void buttonIcon() {
        JButton[] obj = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};
        ImageIcon icon = new ImageIcon("images\\1.png");
        Image img = icon.getImage();
        ImageIcon icon2 = new ImageIcon("images\\2.png");
        Image img2 = icon2.getImage();
        Image scaledImg = img2.getScaledInstance(l1.getWidth(), l1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        Image scaledImg2 = img.getScaledInstance(l1.getWidth(), l1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        for (int i = 0; i < obj.length; i++) {
            if (i % 2 == 0) {
                obj[i].setIcon(scaledIcon);
            } else {
                obj[i].setIcon(scaledIcon2);
            }
        }
    }
    
    void pane() {
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
    }
    
    public void close() {
        WindowEvent we = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
    }
    
    void playSound(String soundFile) {
        try {
            File f = new File("./" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());            
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.out.print(e);
        }
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
        ltime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lmove = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MemoryGame");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(3, 4, 5, 5));

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
            .addComponent(b1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        pane1Layout.setVerticalGroup(
            pane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane2Layout.setVerticalGroup(
            pane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane3Layout.setVerticalGroup(
            pane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane4Layout.setVerticalGroup(
            pane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane5Layout.setVerticalGroup(
            pane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane6Layout.setVerticalGroup(
            pane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b7, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane7Layout.setVerticalGroup(
            pane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b7, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b8, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane8Layout.setVerticalGroup(
            pane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b8, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b9, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane9Layout.setVerticalGroup(
            pane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b9, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b10, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        pane10Layout.setVerticalGroup(
            pane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b10, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b11, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
            .addComponent(l11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pane11Layout.setVerticalGroup(
            pane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b11, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
            .addComponent(b12, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        pane12Layout.setVerticalGroup(
            pane12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b12, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel2.add(pane12);

        ltime.setBackground(new java.awt.Color(255, 255, 255));
        ltime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ltime.setForeground(new java.awt.Color(82, 94, 221));
        ltime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltime.setText("Timer:");

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
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ltime, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lmove, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        playSound("sounds\\open.wav");
        b1.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b2.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b3.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b4.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b5.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b6.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b7.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b8.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:-=09
        playSound("sounds\\open.wav");
        b9.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b9ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b10.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b10ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b11.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b11ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        // TODO add your handling code here:
        playSound("sounds\\open.wav");
        b12.setVisible(false);
        x++;
        play(evt);
    }//GEN-LAST:event_b12ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Fourx3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fourx3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fourx3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fourx3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fourx3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
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
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
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
    private javax.swing.JLayeredPane pane2;
    private javax.swing.JLayeredPane pane3;
    private javax.swing.JLayeredPane pane4;
    private javax.swing.JLayeredPane pane5;
    private javax.swing.JLayeredPane pane6;
    private javax.swing.JLayeredPane pane7;
    private javax.swing.JLayeredPane pane8;
    private javax.swing.JLayeredPane pane9;
    // End of variables declaration//GEN-END:variables
}
