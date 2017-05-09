package museumvolunteer.BLL;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class ContainsSearch extends QuerySearchTemplate {

    /**
     * String query.
     * @param query
     */
    public ContainsSearch(String query) {
        super(query);
    }

    /**
     * Sets query to lowercase.
     * @param word
     * @return
     */
    @Override
    public boolean compare(String word) {
        return word.toLowerCase().contains(query.toLowerCase());
    }
}