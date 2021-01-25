public class ParentData {

    protected int rsID, chromosome, position;
    protected String genotype;

    public ParentData(String rsID, String chromosome, int position, String genotype) {
        setrsID(rsID);
        setChromosome(chromosome);
        setPosition(position);
        setGenotype(genotype);
    }

    public void setrsID(String rsID) {
        this.rsID = Integer.parseInt(rsID.replaceAll("\\D+", ""));
    }

    public int getrsID() {return this.rsID;}

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

    public int getChromosome() {return this.chromosome;}

    public void setPosition(int position) {this.position = position;}

    public int getPosition() {return this.position;}

    public void setGenotype(String genotype) {this.genotype = genotype;}

    public String getGenotype() {return this.genotype;}
}
