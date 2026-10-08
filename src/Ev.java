import java.awt.Color;
import java.awt.Graphics2D;

/**
 * EV SINIFI
 * "BOŞLUK" yazan yerleri doldurun. Diğer satırlar hazır, onları okuyup anlamaya çalışın.
 */
public class Ev {

    // Sınıfa ait sayaç: kaç ev oluşturulduğunu tutar (static: bütün evler için tek bir tane)
    private static int evSayisi = 0;

    // Her evin kendine ait özellikleri (private: dışarıdan doğrudan değiştirilemez)
    private int x;               // evin sol kenarı
    private int y;               // evin zemine oturduğu çizgi
    private int genislik;
    private int yukseklik;
    private int pencereSayisi;
    private Color duvarRengi;
    private Color catiRengi;
    private boolean isikAcik;    // gece ışıklar yanar

    // Yapıcı 1: bütün özellikleri parametre olarak alır
    public Ev(int x, int y, int genislik, int yukseklik, Color duvarRengi, Color catiRengi) {
        this.x = x;
        this.y = y;
        // BOŞLUK 1: genislik ve yukseklik parametrelerini alanlara atayın.
        //           Yukarıdaki iki satıra benzer şekilde: this.alan = parametre;
        // ... buraya yazın ...
        this.duvarRengi = duvarRengi;
        this.catiRengi = catiRengi;
        this.pencereSayisi = 2;
        // BOŞLUK 2: Yeni bir ev oluştu. evSayisi değerini 1 artırın.
        // ... buraya yazın ...
    }

    // Yapıcı 2: yalnızca konum alır, gerisini hazır değerlerle doldurur
    public Ev(int x, int y) {
        // BOŞLUK 3: this(...) ile Yapıcı 1'i çağırın. Değerler:
        //           genişlik 120, yükseklik 110,
        //           duvar new Color(230, 200, 160), çatı new Color(170, 60, 50)
        // ... buraya yazın ...
    }

    public void setPencereSayisi(int sayi) {
        if (sayi < 1) {
            sayi = 1;
        }
        // BOŞLUK 4: sayi 4'ten büyükse 4 yapın. Yukarıdaki if'e bakın.
        // ... buraya yazın ...
        this.pencereSayisi = sayi;
    }

    public void setIsikAcik(boolean isikAcik) {
        this.isikAcik = isikAcik;
    }

    public static int getEvSayisi() {
        // BOŞLUK 5: 0 yerine evSayisi değerini döndürün.
        return 0;
    }

    // Evi çizer. Bu metot hazır.
    public void ciz(Graphics2D g) {
        int ust = y - yukseklik;

        // Duvar
        g.setColor(duvarRengi);
        g.fillRect(x, ust, genislik, yukseklik);

        // Çatı: üçgen
        g.setColor(catiRengi);
        int[] xler = {x - 10, x + genislik + 10, x + genislik / 2};
        int[] yler = {ust, ust, ust - genislik / 2};
        g.fillPolygon(xler, yler, 3);

        // Kapı
        g.setColor(new Color(100, 60, 30));
        g.fillRect(x + genislik / 2 - 12, y - 40, 24, 40);

        // Pencereler: ışık açıksa sarı, değilse mavi
        if (isikAcik) {
            g.setColor(new Color(255, 220, 90));
        } else {
            g.setColor(new Color(170, 210, 240));
        }
        int aralik = genislik / (pencereSayisi + 1);
        for (int i = 1; i <= pencereSayisi; i++) {
            g.fillRect(x + i * aralik - 10, ust + 20, 20, 20);
        }
    }
}
