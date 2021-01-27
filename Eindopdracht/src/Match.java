public class Match implements Comparable<Match>{

    protected int parentID1, parentID2, chromosome, rsID;
    protected String parent1Nuclo, parent2Nuclo, varNuclo;
    protected boolean variantMatch;

    public Match(int parentID1, int parentID2, int chromosome, String parent1Nuclo, String parent2Nuclo, int rsID) {
        setParentID1(parentID1);
        setParentID2(parentID2);
        setParent1Nuclo(parent1Nuclo);
        setParent2Nuclo(parent2Nuclo);
        setChromosome(chromosome);
        setRSID(rsID);

    }

    public void setParentID1(int parentID1) {this.parentID1 = parentID1;}

    public int getParentID1() {return this.parentID1;}

    public void setParentID2(int parentID2) {this.parentID2 = parentID2;}

    public int getParentID2() {return this.parentID2;}

    public void setParent1Nuclo(String parent1Nuclo) {this.parent1Nuclo = parent1Nuclo;}

    public String getParent1Nuclo() {return this.parent1Nuclo;}

    public void setParent2Nuclo(String parent2Nuclo) {this.parent2Nuclo = parent2Nuclo;}

    public String getParent2Nuclo() {return this.parent2Nuclo;}

    public void setChromosome(int chromosome) {this.chromosome = chromosome;}

    public int getChromosome() {return this.chromosome;}

    public void setVariantMatch(boolean variantMatch) {this.variantMatch = variantMatch;}

    public boolean getVariantMatch() {return this.variantMatch;}

    public void setVarNuclo(String varNuclo) {this.varNuclo = varNuclo;}

    public String getVarNuclo() {return this.varNuclo;}

    public void setRSID(int rsID) {this.rsID = rsID;}

    public int getRSID() {return this.rsID;}

    @Override
    public String toString() {
        return "Match{" +
                "parentID1=" + parentID1 +
                ", parentID2=" + parentID2 +
                ", chromosome=" + chromosome +
                ", rsID=" + rsID +
                ", parent1Nuclo='" + parent1Nuclo + '\'' +
                ", parent2Nuclo='" + parent2Nuclo + '\'' +
                ", varNuclo='" + varNuclo + '\'' +
                ", variantMatch=" + variantMatch +
                '}';
    }

    @Override
    public int compareTo(Match o) {
        return Integer.compare(o.getChromosome(), this.chromosome);
    }
}
