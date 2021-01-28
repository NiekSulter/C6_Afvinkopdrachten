import java.io.*;

import java.net.URL;
import java.net.URLConnection;

public class DownloadVS {

    public static void main(String[] args) throws IOException, InterruptedException {
        DownloadVS d1 = new DownloadVS();
        d1.fileExists();
    }

    public static void fileExists() throws IOException, InterruptedException {
        File file = new File("Eindopdracht/resources/variantSummary/variant_summary.txt");

        if (file.exists()) {
            String webHash = getWebHash();
            System.out.println(webHash);
            String localHash = getLocalHash();
            System.out.println(localHash);
            if (!webHash.equals(localHash)) {
                file.delete();
                fileExists();
                System.out.println("Hashed niet gelijk!");
            }
            System.out.println("File is aanwezig en hash klopt");
        } else {
            System.out.println("File niet gevonden, beginnen met downloaden.");
            downloadFile();
            unzipFile();
            fileExists();
        }
    }

    public static String getWebHash() throws IOException {
        URL url = new URL("ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar/tab_delimited/variant_summary.txt.gz.md5");
        URLConnection urlConn = url.openConnection();
        String webHash = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            webHash = line.split(" ")[0];
        }

        return webHash;
    }

    public static String getLocalHash() throws IOException, InterruptedException {
        String[] command = new String[]{"md5", "Eindopdracht/resources/variantSummary/variant_summary.txt.gz"};
        Process pro = new ProcessBuilder(command).start();
        String localHash = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            localHash = line.split(" ")[3];
        }

        pro.waitFor();

        return localHash;
    }

    public static void downloadFile() throws InterruptedException, IOException {
        String[] command = new String[]{"wget", "-q", "-O",
                "Eindopdracht/resources/variantSummary/variant_summary.txt.gz",
                "ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar/tab_delimited/variant_summary.txt.gz"};
        Process pro = new ProcessBuilder(command).start();
        pro.waitFor();
        System.out.println("Bestand gedownload");
    }

    public static void unzipFile() throws InterruptedException, IOException {
        String[] commandUnzip = new String[]{"gzip", "-k", "-d",
                "Eindopdracht/resources/variantSummary/variant_summary.txt.gz"};
        Process proUnzip = new ProcessBuilder(commandUnzip).start();
        proUnzip.waitFor();
        System.out.println("Bestand geunzipt");
    }
}


