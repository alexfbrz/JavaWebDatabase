/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import java.sql.Statement;

public class DbPersonCollection {

    public static String update(Statement statement, HttpServletRequest request)
    {
        String errorMessage= "";
        String action = request.getParameter("action");
        if(action != null)
        {
            String name = request.getParameter("Name");
            String eyeColor = request.getParameter("Eye Color");
            String hairColor = request.getParameter("Hair Color");
            String height = request.getParameter("Height");
            String weight = request.getParameter("Weight");
            //int id = Integer.parseInt(request.getParameter("Id"));
            
        
            DbPerson person = new DbPerson(name, eyeColor, hairColor, height, weight);
            
            String strId;
            int id;
            
            switch(action)
            {
                case "Clear List":
                    errorMessage = DbPerson.remove(-1, statement);
                    break;
                case "add":
                    errorMessage = person.insert(statement);
                    System.out.println(person);
                    break;
                case "remove":
                    strId = request.getParameter("Id");
                    id = Integer.parseInt(strId);
                    errorMessage = DbPerson.remove(id, statement);
                    break;
                case "update":
                    strId = request.getParameter("Id");
                    id = Integer.parseInt(strId);
                    errorMessage = person.update(id, statement);
                    break;
            }
        }   
        
        ArrayList<DbPerson> personCollection = new ArrayList<>();
        errorMessage += DbPerson.getPersons(statement, personCollection);
        request.setAttribute("PersonCOllection", personCollection);
        
        return errorMessage;
    }
}