import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * GÜNEŞ SINIFI
 * "BOŞLUK" yazan yerleri doldurun. Diğer satırlar hazır, onları okuyup anlamaya çalışın.
 */
public class Gunes {

    private int x;               // güneşin merkezi
    private int y;
    private int yaricap;
    private int isinSayisi;
    private Color renk;

    public Gunes(int x, int y, int yaricap) {
        this.x = x;
        this.y = y;
        // BOŞLUK 1: yaricap parametresini alana atayın.
        // ... buraya yazın ...
        this.isinSayisi = 12;
        this.renk = new Color(255, 200, 40);
    }

    public void setIsinSayisi(int isinSayisi) {
        if (isinSayisi < 0) {
            isinSayisi = 0;
        }
        this.isinSayisi = isinSayisi;
    }

    // G tuşuyla gece olunca güneşi aya çevirmek için çağrılır.
    public void setRenk(Color renk) {
        // BOŞLUK 2: renk parametresini alana atayın.
        // ... buraya yazın ...
    }

    // Güneşi çizer.
    public void ciz(Graphics2D g) {
        g.setColor(renk);
        g.fillOval(x - yaricap, y - yaricap, 2 * yaricap, 2 * yaricap);

        g.setStroke(new BasicStroke(3));
        // BOŞLUK 3: Döngü kaç kez dönmeli? 0 yerine ışın sayısını tutan alanı yazın.
        for (int i = 0; i < 0; i++) {
            double aci = 2 * Math.PI * i / isinSayisi;
            int x1 = x + (int) (Math.cos(aci) * (yaricap + 8));
            int y1 = y + (int) (Math.sin(aci) * (yaricap + 8));
            int x2 = x + (int) (Math.cos(aci) * (yaricap + 25));
            int y2 = y + (int) (Math.sin(aci) * (yaricap + 25));
            g.drawLine(x1, y1, x2, y2);
        }
        g.setStroke(new BasicStroke(1));
    }
}
