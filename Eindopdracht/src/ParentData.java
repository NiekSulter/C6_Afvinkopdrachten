/**
 * Een class voor de data opgehaald uit de 23andme bestanden.
 */


public class ParentData {

    protected int rsID, chromosome, position, parentID;
    protected String genotype;

    /**
     * @param rsID       rsid van de parent.
     * @param chromosome chromosoomnummer van de nucleotiden.
     * @param position   position van de nucleotiden.
     * @param genotype   het genotype van de parent.
     * @param parentID   het id van de parent (eerste 4 cijfers van het 23
     *                   bestand).
     */

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

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getGenotype() {
        return this.genotype;
    }

    /**
     * Setter van het genotype van de parent kijkt of dat er -- staat, als dit
     * het geval is dan worden deze vervangen door een lege string.
     *
     * @param genotype genotype van de parent.
     */
    public void setGenotype(String genotype) {
        if (!genotype.equals("--")) {
            this.genotype = genotype;
        } else {
            this.genotype = "";
        }
    }

    public int getParentID() {
        return this.parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
}
