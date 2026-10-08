import java.awt.Color;
import java.awt.Graphics2D;

/**
 * BULUT SINIFI
 * "BOŞLUK" yazan yerleri doldurun. Diğer satırlar hazır, onları okuyup anlamaya çalışın.
 */
public class Bulut {

    private double x;            // double: bulut çok yavaş (ör. 0.4 piksel) ilerler
    private int y;
    private int boyut;
    private double hiz;
    private boolean yagmurlu;

    // Yapıcı 1
    public Bulut(int x, int y, int boyut, double hiz) {
        this.x = x;
        this.y = y;
        this.boyut = boyut;
        this.hiz = hiz;
        this.yagmurlu = false;
    }

    // Yapıcı 2: yalnızca konum alır
    public Bulut(int x, int y) {
        // BOŞLUK 1: this(...) ile Yapıcı 1'i çağırın. Boyut 90, hız 0.4
        // ... buraya yazın ...
    }

    public void setYagmurlu(boolean yagmurlu) {
        this.yagmurlu = yagmurlu;
    }

    // Her 30 milisaniyede bir çağrılır.
    public void hareketEt(int panelGenisligi) {
        // BOŞLUK 2: x'i hiz kadar artırın.
        // ... buraya yazın ...
        if (x > panelGenisligi) {
            x = -2 * boyut;              // sağdan çıktı, soldan girsin
        }
    }

    // Bulutu çizer.
    public void ciz(Graphics2D g) {
        if (yagmurlu) {
            // BOŞLUK 3: Yağmur bulutu gri olsun. Rengi new Color(150, 150, 160) olarak seçin.
            //           Aşağıdaki else bloğunda rengin nasıl seçildiğine bakın.
            // ... buraya yazın ...
        } else {
            g.setColor(Color.WHITE);
        }
        int bx = (int) x;
        int b = boyut;
        g.fillOval(bx, y, b, b * 6 / 10);
        g.fillOval(bx + b * 4 / 10, y - b * 3 / 10, b, b * 8 / 10);
        g.fillOval(bx + b * 9 / 10, y, b, b * 6 / 10);
    }
}
