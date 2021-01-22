public class Variant {

    protected int chromosome, rsID;
    protected String snp;

    public Variant(int chromosome, int rsID, String snp) {
        setChromosome(chromosome);
        setRSID(rsID);
        setSNP(snp);
    }

    public void setChromosome(int chromosome) {this.chromosome = chromosome;}

    public int getChromosome() {return this.chromosome;}

    public void setRSID(int rsID) {this.rsID = rsID;}

    public int getRSID() {return this.rsID;}

    public void setSNP(String snp) {this.snp = snp;}

    public String getSNP() {return this.snp;}
}
