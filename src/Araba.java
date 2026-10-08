import java.awt.Color;
import java.awt.Graphics2D;

/**
 * ARABA SINIFI
 * "BOŞLUK" yazan yerleri doldurun. Diğer satırlar hazır, onları okuyup anlamaya çalışın.
 */
public class Araba {

    public static final int GENISLIK = 100;
    public static final int MAX_HIZ = 12;

    private static int arabaSayisi = 0;

    private int x;
    private int y;               // tekerlerin yola değdiği çizgi
    private Color renk;
    private int hiz;
    private boolean sagaGidiyor;

    // Yapıcı 1
    public Araba(int x, int y, Color renk, int hiz, boolean sagaGidiyor) {
        this.x = x;
        this.y = y;
        this.renk = renk;
        // BOŞLUK 1: hızı setHiz(...) metoduyla atayın (sınır kontrolü için).
        // ... buraya yazın ...
        this.sagaGidiyor = sagaGidiyor;
        // BOŞLUK 2: arabaSayisi değerini 1 artırın.
        // ... buraya yazın ...
    }

    // Yapıcı 2: this(...) örneği — hız 3, sağa gidiyor
    public Araba(int x, int y, Color renk) {
        this(x, y, renk, 3, true);
    }

    public void setHiz(int hiz) {
        if (hiz < 1) {
            hiz = 1;
        }
        if (hiz > MAX_HIZ) {
            hiz = MAX_HIZ;
        }
        this.hiz = hiz;
    }

    // H tuşuna basınca çağrılır
    public void hizlan() {
        setHiz(hiz + 1);
    }

    // Y tuşuna basınca çağrılır
    public void yonDegistir() {
        // BOŞLUK 3: sagaGidiyor değerini tersine çevirin (true ise false, false ise true).
        //           İpucu: ! işareti bir boolean değeri tersine çevirir.
        // ... buraya yazın ...
    }

    public static int getArabaSayisi() {
        return arabaSayisi;
    }

    // Her 30 milisaniyede bir çağrılır.
    public void hareketEt(int panelGenisligi) {
        if (sagaGidiyor) {
            x = x + hiz;
            if (x > panelGenisligi) {
                x = -GENISLIK;           // sağdan çıktı, soldan girsin
            }
        } else {
            // BOŞLUK 4: Sola gidiyor. x'i hiz kadar azaltın.
            //           x, -GENISLIK'ten küçük olursa x = panelGenisligi yapın.
            //           Yukarıdaki sağa gitme kodunu örnek alın.
            // ... buraya yazın ...
        }
    }

    // Arabayı çizer. Bu metot hazır.
    public void ciz(Graphics2D g) {
        g.setColor(renk);
        g.fillRoundRect(x, y - 40, GENISLIK, 25, 12, 12);     // gövde
        g.fillRoundRect(x + 20, y - 60, 55, 25, 12, 12);      // kabin

        g.setColor(new Color(200, 230, 255));                 // camlar
        g.fillRect(x + 27, y - 55, 18, 14);
        g.fillRect(x + 50, y - 55, 18, 14);

        g.setColor(Color.DARK_GRAY);                          // tekerlekler
        g.fillOval(x + 12, y - 25, 24, 24);
        g.fillOval(x + 64, y - 25, 24, 24);

        g.setColor(Color.YELLOW);                             // far: gittiği yönde
        if (sagaGidiyor) {
            g.fillRect(x + GENISLIK - 6, y - 35, 6, 8);
        } else {
            g.fillRect(x, y - 35, 6, 8);
        }
    }
}
