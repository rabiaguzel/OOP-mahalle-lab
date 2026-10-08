import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Programın başlangıç noktası.
 * BU DOSYAYI DEĞİŞTİRMEYİN — yalnızca GRUP_ADI sabitine grubunuzun adını yazın.
 */
public class Main {

    // Grubunuzun adı pencerenin başlığında görünecek.
    public static final String GRUP_ADI = "Grup Adı";

    public static void main(String[] args) {
        // Pencereyi Swing'in kendi iş parçacığında açıyoruz (anonim sınıf örneği).
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame pencere = new JFrame("Mahalle - " + GRUP_ADI);
                SahnePaneli panel = new SahnePaneli();
                pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pencere.setResizable(false);
                pencere.add(panel);
                pencere.pack();
                pencere.setLocationRelativeTo(null);
                pencere.setVisible(true);
                panel.requestFocusInWindow();
            }
        });
    }
}
