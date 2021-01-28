public class ParentData {

    protected int rsID, chromosome, position, parentID;
    protected String genotype;

    public ParentData(String rsID, String chromosome,
                      int position, String genotype,
                      int parentID) {
        setrsID(rsID);
        setChromosome(chromosome);
        setPosition(position);
        setGenotype(genotype);
        setParentID(parentID);
    }

    public void setrsID(String rsID) {
        this.rsID = Integer.parseInt(rsID.replaceAll("\\D+",
                ""));
    }

    public int getrsID() {
        return this.rsID;
    }

    public void setChromosome(String chromosome) {
        switch (chromosome) {
            case "X" -> this.chromosome = 23;
            case "Y" -> this.chromosome = 24;
            case "MT" -> this.chromosome = 25;
            default -> this.chromosome = Integer.parseInt(chromosome);
        }
    }

    public int getChromosome() {
        return this.chromosome;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public void setGenotype(String genotype) {
        if (!genotype.equals("--")) {
            this.genotype = genotype;
        } else {
            this.genotype = "";
        }
    }

    public String getGenotype() {
        return this.genotype;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getParentID() {
        return this.parentID;
    }
}
