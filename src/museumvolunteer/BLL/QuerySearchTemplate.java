/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BLL;

/**
 *
 * @author Fage
 */
public abstract class QuerySearchTemplate implements SearchPattern
{
    protected final String query;
    
    public QuerySearchTemplate(String query) {
        this.query = query;
    }
    
}