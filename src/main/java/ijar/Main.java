package ijar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Main extends JPanel implements ActionListener {

    private BufferedImage canvas;

    public void actionPerformed(ActionEvent e) {
        long startTime = System.currentTimeMillis();
        //"raindrops"
        for(int i = 0; i < 10; i++) {
            int r = (int)(Math.random() * ((w*h)-(w*2)));
            r += w;
            data[r]     = -1;
            data[r + w] = -1;
            data[r + 1] = -1;
            data[r - 1] = -1;
            data[r - w] = -1;
        }
        //"goop"
        for(int i = w*2; i < (w*h) - (w*2); i++) {
            //falling animation
            if(data[i] > max) max = data[i];

            int avg = data[i - w]
                + data[i - w*2]
                + data[i - 2]
                + data[i - 1]
                + data[i]
                + data[i + 1]
                + data[i + 2]
                + data[i + w]
                + data[i + w*2];

            avg = avg / 9;

            if(avg > max / 2)
                data[i - w*2] = avg;
            data[i - w] = avg;
            data[i - 2] = avg;
            data[i - 1] = avg;
            data[i]     = avg;
            data[i + 1] = avg;
            data[i + 2] = avg;
            data[i + w] = avg;
            if(avg < max / 2)
                data[i + w*2] = avg;


        }
        //"wipers"
        for(int i=0; i<20;i++) {
            wx += (Math.random() * 4 - 2);
            wy += (Math.random() * 4 - 2);
            if(wx < 0) {
                wx = w - 2;
            } else if(wx >= w) {
                wx = 0;
            }
            if(wy < 0) {
                wy = h - 2;
            } else if(wy >= h) {
                wy = 0;
            }
            data[wx*wy] = -1;
        }
        canvas.setRGB(0,0,w,h,data,0,w);
        repaint();
        long endTime = System.currentTimeMillis();
        //System.out.println(endTime - startTime);
    }
    private int w;
    private int h;
    private int wx = 0;
    private int wy = 0;
    private int data[];
    private int max = 0;
    public Main(int width, int height) {
        Timer timer = new Timer(0, this);
        this.w = width;
        this.h = height;
        data = new int[w * h];
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < w*h; i++) {
            int r = (int)(Math.random() * 255);
            int g = (int)(Math.random() * 255);
            int b = (int)(Math.random() * 255);
            data[i] = new Color(r,g,b).getRGB();
        }
        timer.start();

    }

    public Dimension getPreferredSize() {
            return new Dimension(canvas.getWidth(), canvas.getHeight());
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(canvas, null, null);
        }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Fun");
        Main panel = new Main(1024, 768);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
