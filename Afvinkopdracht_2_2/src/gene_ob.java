public class gene_ob {

    protected int geneID;
    protected String symbol, chromap;

    public gene_ob(int geneID, String symbol, String chromap) {
        setGeneID(geneID);
        setSymbol(symbol);
        setChromap(chromap);

    }

    public void setGeneID(int geneID) {
        this.geneID = geneID;
    }

    public int get_gene_id() {
        return this.geneID;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setChromap(String chromap) {
        this.chromap = chromap;
    }

    public String getChromap() {
        return this.chromap;
    }

    public String toString() {
        return this.chromap;
    }

}
