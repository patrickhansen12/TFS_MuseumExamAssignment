package museumvolunteer.BLL;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public interface SearchPattern {

    /**
     * Boolean for comparing words.
     * @param word
     * @return
     */
    public boolean compare(String word);
}
