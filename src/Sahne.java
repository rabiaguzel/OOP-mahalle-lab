import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Sahnedeki bütün nesneleri oluşturan ve tutan sınıf.
 * Burada yalnızca en alttaki iki BOŞLUK var: grubunuzun kendi arabası ve bulutu.
 * Nesnelerin nasıl oluşturulduğunu (new) ve listelere nasıl eklendiğini okuyun.
 */
public class Sahne {

    private ArrayList<Ev> evler = new ArrayList<Ev>();
    private ArrayList<Agac> agaclar = new ArrayList<Agac>();
    private ArrayList<Araba> arabalar = new ArrayList<Araba>();
    private ArrayList<Bulut> bulutlar = new ArrayList<Bulut>();
    private Gunes gunes;
    private boolean gece = false;

    public Sahne() {
        sahneyiKur();
    }

    private void sahneyiKur() {
        // Güneş
        gunes = new Gunes(790, 85, 45);

        // Bulutlar: iki farklı yapıcı
        bulutlar.add(new Bulut(80, 60));
        Bulut yagmurBulutu = new Bulut(600, 40, 70, 0.3);
        yagmurBulutu.setYagmurlu(true);
        bulutlar.add(yagmurBulutu);

        // Evler: iki farklı yapıcı
        evler.add(new Ev(40, SahnePaneli.EV_HATTI));
        evler.add(new Ev(250, SahnePaneli.EV_HATTI, 170, 130,
                new Color(200, 220, 240), new Color(60, 80, 140)));
        Ev apartman = new Ev(510, SahnePaneli.EV_HATTI, 120, 190,
                new Color(245, 225, 180), new Color(120, 70, 40));
        apartman.setPencereSayisi(6);    // 6 istiyoruz ama en fazla 4 olabilir
        evler.add(apartman);

        // Ağaçlar: dizi ve for döngüsüyle
        int[] agacX = {195, 455, 680, 750};
        for (int i = 0; i < agacX.length; i++) {
            String tur;
            if (i % 2 == 0) {
                tur = "yuvarlak";
            } else {
                tur = "cam";
            }
            agaclar.add(new Agac(agacX[i], SahnePaneli.EV_HATTI, 90 + i * 20, tur));
        }
        agaclar.add(new Agac(815, SahnePaneli.EV_HATTI, 400, "yuvarlak"));  // 400 çok büyük!

        // Arabalar
        arabalar.add(new Araba(100, SahnePaneli.ALT_SERIT, Color.RED));
        arabalar.add(new Araba(500, SahnePaneli.ALT_SERIT, new Color(40, 90, 200), 5, true));
        arabalar.add(new Araba(700, SahnePaneli.UST_SERIT, new Color(250, 190, 30), 4, false));

        // ===== GRUBUNUZUN NESNELERİ =====
        // BOŞLUK S1: Kendi arabanızı ekleyin. Rengini, hızını ve yönünü siz seçin.
        //            Örnek: arabalar.add(new Araba(300, SahnePaneli.UST_SERIT, Color.GREEN, 6, false));
        // ... buraya yazın ...

        // BOŞLUK S2: Kendi bulutunuzu ekleyin. Konumunu, boyutunu ve hızını siz seçin.
        //            Örnek: bulutlar.add(new Bulut(250, 160, 60, 0.8));
        // ... buraya yazın ...

        System.out.println("Ev: " + Ev.getEvSayisi() + ", Ağaç: " + Agac.getAgacSayisi()
                + ", Araba: " + Araba.getArabaSayisi());
    }

    // Fareyle tıklanınca çağrılır: gökyüzüne bulut, çimene ağaç ekler.
    public void tiklandi(int x, int y) {
        if (y < SahnePaneli.UFUK) {
            bulutlar.add(new Bulut(x - 50, y - 20));
        } else {
            agaclar.add(new Agac(x, y));
        }
    }

    // Tuşa basılınca çağrılır.  G: gece/gündüz   H: hızlan   Y: yön değiştir   B: ağaçlar büyüsün
    public void tusaBasildi(char tus) {
        if (tus == 'g') {
            gece = !gece;
            for (Ev e : evler) {
                e.setIsikAcik(gece);
            }
            if (gece) {
                gunes.setRenk(new Color(235, 235, 220));
                gunes.setIsinSayisi(0);
            } else {
                gunes.setRenk(new Color(255, 200, 40));
                gunes.setIsinSayisi(12);
            }
        } else if (tus == 'h') {
            for (Araba a : arabalar) {
                a.hizlan();
            }
        } else if (tus == 'y') {
            for (Araba a : arabalar) {
                a.yonDegistir();
            }
        } else if (tus == 'b') {
            for (Agac a : agaclar) {
                a.buyu();
            }
        }
    }

    // ---- Aşağıdaki metotlar SahnePaneli tarafından çağrılır ----
    public void guncelle(int panelGenisligi) {
        for (Araba a : arabalar) {
            a.hareketEt(panelGenisligi);
        }
        for (Bulut b : bulutlar) {
            b.hareketEt(panelGenisligi);
        }
    }

    public void ciz(Graphics2D g) {
        if (gunes != null) {
            gunes.ciz(g);
        }
        for (Bulut b : bulutlar) {
            b.ciz(g);
        }
        for (Ev e : evler) {
            e.ciz(g);
        }
        for (Agac a : agaclar) {
            a.ciz(g);
        }
        for (Araba a : arabalar) {
            a.ciz(g);
        }
    }

    public boolean isGece() {
        return gece;
    }

    public boolean bosMu() {
        return gunes == null && evler.isEmpty() && agaclar.isEmpty()
                && arabalar.isEmpty() && bulutlar.isEmpty();
    }
}
