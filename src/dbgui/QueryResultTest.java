/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgui;

import java.util.List;

/**
 *
 * @author Sebas
 */
public class QueryResultTest {
    private List<String> tobechecked;
    
    QueryResultTest(List result){
        this.tobechecked = result;
    }
    
    public String checkQueryResult(){
        int nulls = 0;
        int notnulls = 0;
        for(String st : this.tobechecked){
            if(st == null || st.equals("")){
                nulls++;
            }else{
                notnulls++;
            }
        }
        
        return "Query returned results.\n" + nulls + " Values were null, " + notnulls + " values were not null.";
    }
}
