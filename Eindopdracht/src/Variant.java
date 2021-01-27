public class Variant {

    protected int chromosome, rsID;
    protected String snp1, snp2, snp;

    public Variant(String chromosome, int rsID, String snp1, String snp2) {
        setChromosome(chromosome);
        setRSID(rsID);
        setSNP(snp1, snp2);
    }

    public void setChromosome(String chromosome) {
        switch (chromosome) {
            case "X":
                this.chromosome = 24;
                break;
            case "Y":
                this.chromosome = 25;
                break;
            case "MT":
                this.chromosome = 26;
                break;
        }
    }

    public int getChromosome() {
        return this.chromosome;
    }

    public void setRSID(int rsID) {
        this.rsID = rsID;
    }

    public int getRSID() {
        return this.rsID;
    }

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
