import java.awt.Color;
import java.awt.Graphics2D;

/**
 * AĞAÇ SINIFI
 * "BOŞLUK" yazan yerleri doldurun. Diğer satırlar hazır, onları okuyup anlamaya çalışın.
 */
public class Agac {

    // Sabitler (final: değişmez)
    public static final int MIN_BOY = 60;
    public static final int MAX_BOY = 220;

    private static int agacSayisi = 0;

    private int x;               // gövdenin ortası
    private int y;               // ağacın zemine oturduğu çizgi
    private int boy;
    private String tur;          // "yuvarlak" ya da "cam"
    private Color yaprakRengi;

    // Yapıcı 1
    public Agac(int x, int y, int boy, String tur) {
        this.x = x;
        this.y = y;
        // BOŞLUK 1: boy'u doğrudan atamayın; setBoy(...) metodunu çağırın.
        //           Böylece çok büyük ya da çok küçük boylar düzeltilir.
        // ... buraya yazın ...
        this.tur = tur;
        this.yaprakRengi = new Color(40, 140, 60);
        agacSayisi++;
    }

    // Yapıcı 2: yalnızca konum alır
    public Agac(int x, int y) {
        // BOŞLUK 2: this(...) ile Yapıcı 1'i çağırın. Boy 110, tür "yuvarlak"
        // ... buraya yazın ...
    }

    public void setBoy(int boy) {
        if (boy < MIN_BOY) {
            boy = MIN_BOY;
        }
        // BOŞLUK 3: boy MAX_BOY'dan büyükse MAX_BOY yapın. Yukarıdaki if'e bakın.
        // ... buraya yazın ...
        this.boy = boy;
    }

    // B tuşuna basınca çağrılır
    public void buyu() {
        // BOŞLUK 4: setBoy(...) metodunu kullanarak boyu 10 artırın.
        // ... buraya yazın ...
    }

    public static int getAgacSayisi() {
        return agacSayisi;
    }

    // Ağacı çizer.
    public void ciz(Graphics2D g) {
        // Gövde
        g.setColor(new Color(110, 70, 40));
        int govdeGenisligi = boy / 8;
        g.fillRect(x - govdeGenisligi / 2, y - boy / 2, govdeGenisligi, boy / 2);

        // Yapraklar
        g.setColor(yaprakRengi);
        // BOŞLUK 5: false yerine türün "cam" olup olmadığını kontrol edin.
        //           String karşılaştırırken == değil equals kullanılır: "cam".equals(tur)
        if (false) {
            int[] xler = {x, x - boy / 3, x + boy / 3};
            int[] yler = {y - boy, y - boy / 4, y - boy / 4};
            g.fillPolygon(xler, yler, 3);
        } else {
            g.fillOval(x - boy / 3, y - boy, boy * 2 / 3, boy * 2 / 3);
        }
    }
}
