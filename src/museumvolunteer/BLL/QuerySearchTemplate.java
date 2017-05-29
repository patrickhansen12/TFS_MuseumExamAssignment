package museumvolunteer.BLL;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public abstract class QuerySearchTemplate implements SearchPattern
{
    /**
     * Variable for String query.
     */
    protected final String query;
    
    /**
     * Sets current query equal to query.
     * @param query
     */
    public QuerySearchTemplate(String query) {
        this.query = query;
    }  
}