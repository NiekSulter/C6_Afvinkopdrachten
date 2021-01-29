/**
 * Variant class om de data opgehaald uit het variant_summary bestand in
 * op te slaan.
 */


public class Variant {

    protected int chromosome, rsID;
    protected String snp1, snp2, snp;

    /**
     * @param chromosome chromosoomnummer van de snp.
     * @param rsID       het rsid van de snp.
     * @param snp1       eerste kolom (W).
     * @param snp2       tweede kolom (AH).
     */
    public Variant(String chromosome, int rsID, String snp1, String snp2) {
        setChromosome(chromosome);
        setRSID(rsID);
        setSNP(snp1, snp2);
    }

    public int getChromosome() {
        return this.chromosome;
    }

    /**
     * Setter van het chromosoomnummer kijkt eerst of dat het een X, Y of MT
     * is, als dit het geval is worden deze vervangen door een nummer.
     *
     * @param chromosome chromosoomnummer
     */

    public void setChromosome(String chromosome) {
        switch (chromosome) {
            case "X" -> this.chromosome = 23;
            case "Y" -> this.chromosome = 24;
            case "MT" -> this.chromosome = 25;
            default -> this.chromosome = Integer.parseInt(chromosome);
        }
    }

    public int getRSID() {
        return this.rsID;
    }

    public void setRSID(int rsID) {
        this.rsID = rsID;
    }

    /**
     * De setSNP functie kijkt eerst naar welke kolom uit het variant_summery
     * bestand de SNP bevat. De kolom die niet leeg is zal worden meegenomen.
     * Als beide kolommen leeg zijn komt er een na te staan.
     *
     * @param snp1 kolom (W).
     * @param snp2 kolom (AH).
     */

    public void setSNP(String snp1, String snp2) {
        if (!snp2.equals("na") && snp1.equals("na")) {
            this.snp = snp2;
        } else if (!snp1.equals("na") && snp2.equals("na")) {
            this.snp = snp1;
        } else {
            this.snp = "na";
        }
    }

    public String getSNP() {
        return this.snp;
    }
}
