import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class om het variant_summary bestand te downloaden, unzippen en de
 * checksum te controleren.
 */

public class DownloadVS {

    /**
     * Functie om te bepalen of het variant_summary bestand bestaat.
     */

    public static void fileExists() {
        File file = new File
                ("Eindopdracht/resources/variantSummary/" +
                        "variant_summary.txt");

        if (file.exists()) {
            /* Als het variant_summary bestand al bestaat wordt de hash
            vergeleken met het bestand van de NCBI servers */
            String webHash = getWebHash();
            System.out.println("Hash van NCBI servers: " + webHash);
            String localHash = getLocalHash();
            System.out.println("Hash van lokaal bestand: " + localHash);

            if (!webHash.equals(localHash)) {
                /* Als de hashes niet overeen komen wordt het lokale bestand
                verwijderd en opnieuw gedownload. */
                file.delete();
                System.out.println("Hashes niet gelijk!");
                fileExists();
            }
            System.out.println("File is aanwezig en hash klopt");
        } else {

            /* Als het variant_summary bestand niet bestaat wordt het
            gedownload en unzipt */
            System.out.println("File niet gevonden, beginnen met downloaden.");
            downloadFile();
            unzipFile();
            fileExists();
        }
    }

    /**
     * Functie om de hash van het variant_summary bestand op te halen van de
     * NCBI FTP server.
     *
     * @return de md5 hash van het variant_summary bestand.
     */

    public static String getWebHash() {
        String webHash = "";
        try {
            URL url = new URL("ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar/" +
                    "tab_delimited/variant_summary.txt.gz.md5");
            URLConnection urlConn = url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                webHash = line.split(" ")[0];
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return webHash;
    }

    /**
     * Functie om de hash van het lokale variant_summary bestand op te halen.
     *
     * @return de md5 hash van het lokale variant_summary bestand.
     */

    public static String getLocalHash() {
        try {
            String[] command = new String[]{"md5", "Eindopdracht/resources/" +
                    "variantSummary/variant_summary.txt.gz"};

            Process pro = new ProcessBuilder(command).start();

            String localHash = "";
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(pro.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                localHash = line.split(" ")[3];
            }

            pro.waitFor();

            return localHash;

        } catch (IOException | InterruptedException e) {
            System.out.println(e);
            return "";
        }
    }

    /**
     * Functie om het variant_summary bestand te downloaden van de NCBI FTP
     * servers.
     */

    public static void downloadFile() {
        try {
            String[] command = new String[]{"wget", "-q", "-O",
                    "Eindopdracht/resources/variantSummary/" +
                            "variant_summary.txt.gz",
                    "ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar" +
                            "/tab_delimited/variant_summary.txt.gz"};

            Process pro = new ProcessBuilder(command).start();
            pro.waitFor();
            System.out.println("Bestand gedownload");
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Functie om het gedownloade variant_summary bestand te unzippen.
     */

    public static void unzipFile() {
        try {
            String[] commandUnzip = new String[]{"gzip", "-k", "-d",
                    "Eindopdracht/resources/variantSummary/" +
                            "variant_summary.txt.gz"};

            Process proUnzip = new ProcessBuilder(commandUnzip).start();
            proUnzip.waitFor();
            System.out.println("Bestand geunzipt");
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }
}


