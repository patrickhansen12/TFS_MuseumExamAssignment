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
public class ContainsSearch extends QuerySearchTemplate {

    public ContainsSearch(String query) {
        super(query);
    }

    @Override
    public boolean compare(String word) {
        return word.toLowerCase().contains(query.toLowerCase());
    }

}