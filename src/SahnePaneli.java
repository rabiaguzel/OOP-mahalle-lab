import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Gökyüzünü, çimeni ve yolu çizen; animasyonu, fare ve klavyeyi yöneten panel.
 * BU DOSYAYI DEĞİŞTİRMEYİN. Aşağıdaki sabitleri kendi kodunuzda kullanabilirsiniz.
 */
public class SahnePaneli extends JPanel {

    // ---- Sahnenin ölçüleri (piksel) ----
    public static final int GENISLIK = 900;
    public static final int YUKSEKLIK = 600;

    // ---- Yerleşim çizgileri: nesnelerinizi bu çizgilere oturtun ----
    public static final int UFUK = 400;        // gökyüzü burada biter, çimen başlar
    public static final int EV_HATTI = 470;    // evlerin ve ağaçların oturduğu çizgi
    public static final int YOL_UST = 485;     // yolun üst kenarı
    public static final int YOL_ALT = 585;     // yolun alt kenarı
    public static final int UST_SERIT = 530;  // üst şeritteki arabaların teker çizgisi
    public static final int ALT_SERIT = 578;   // alt şeritteki arabaların teker çizgisi

    private Sahne sahne;
    private int[] yildizX = new int[60];
    private int[] yildizY = new int[60];

    public SahnePaneli() {
        setPreferredSize(new Dimension(GENISLIK, YUKSEKLIK));
        setFocusable(true);
        sahne = new Sahne();

        Random r = new Random(7);
        for (int i = 0; i < yildizX.length; i++) {
            yildizX[i] = r.nextInt(GENISLIK);
            yildizY[i] = r.nextInt(UFUK - 20);
        }

        // Her 30 ms'de bir: nesneleri hareket ettir, ekranı yeniden çiz.
        Timer zamanlayici = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sahne.guncelle(GENISLIK);
                repaint();
            }
        });
        zamanlayici.start();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                sahne.tiklandi(e.getX(), e.getY());
                requestFocusInWindow();
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                sahne.tusaBasildi(Character.toLowerCase(e.getKeyChar()));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gökyüzü
        if (sahne.isGece()) {
            g.setPaint(new GradientPaint(0, 0, new Color(10, 18, 48), 0, UFUK, new Color(45, 55, 110)));
        } else {
            g.setPaint(new GradientPaint(0, 0, new Color(110, 180, 245), 0, UFUK, new Color(205, 235, 255)));
        }
        g.fillRect(0, 0, GENISLIK, UFUK);
        if (sahne.isGece()) {
            g.setColor(new Color(255, 255, 230));
            for (int i = 0; i < yildizX.length; i++) {
                g.fillOval(yildizX[i], yildizY[i], 2, 2);
            }
        }

        // Çimen
        g.setColor(sahne.isGece() ? new Color(35, 80, 45) : new Color(110, 180, 90));
        g.fillRect(0, UFUK, GENISLIK, YUKSEKLIK - UFUK);

        // Yol ve şerit çizgisi
        g.setColor(new Color(70, 70, 76));
        g.fillRect(0, YOL_UST, GENISLIK, YOL_ALT - YOL_UST);
        g.setColor(new Color(240, 240, 240));
        g.setStroke(new BasicStroke(4));
        int orta = (YOL_UST + YOL_ALT) / 2;
        for (int x = 0; x < GENISLIK; x += 60) {
            g.drawLine(x, orta, x + 30, orta);
        }
        g.setStroke(new BasicStroke(1));

        // Grubun nesneleri
        sahne.ciz(g);

        if (sahne.bosMu()) {
            g.setColor(new Color(30, 40, 60));
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            String satir1 = "Sahne henüz boş.";
            String satir2 = "Sahne.java içindeki sahneyiKur() metodunu doldurun.";
            int w1 = g.getFontMetrics().stringWidth(satir1);
            int w2 = g.getFontMetrics().stringWidth(satir2);
            g.drawString(satir1, (GENISLIK - w1) / 2, 210);
            g.drawString(satir2, (GENISLIK - w2) / 2, 240);
        }
    }
}
