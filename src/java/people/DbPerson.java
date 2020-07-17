/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;


public class DbPerson {

    private String name;
    private String eyeColor;
    private String hairColor;
    private String height;
    private String weight;
    int id;
    
    public DbPerson(String name, String eyeColor, String hairColor, String height, String weight, int id)
    {
        this.name = name;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.height = height;
        this.weight = weight;
        this.id = id;
    }
    
    public DbPerson(String name, String eyeColor, String hairColor, String height, String weight)
    {
        this(name, eyeColor, hairColor, height, weight, -1);
    }
    
    public void setName(String n)
    {
        name = n;
    }
    public void setEyeColor(String e)
    {
        eyeColor = e;
    }
    public void setHairColor(String hC)
    {
        hairColor = hC;
    }
    public void setHeight(String h)
    {
        height = h;
    }
    public void setWweight(String w)
    {
        weight = w;
    }
    
    public String getName()
    {
        return name;
    }
    public String getEyeColor()
    {
        return eyeColor;
    }
    public String getHairColor()
    {
        return hairColor;
    }
    public String getHeight()
    {
        return height;
    }
    public String getWeight()
    {
        return weight;
    }
    public int getId()
    {
        return id;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other == null)
            return false;
        if(other.getClass() != getClass())
            return false;
        DbPerson personOther = (DbPerson)other;
        if(name.equals(personOther) && eyeColor.equals(personOther.eyeColor) && hairColor.equals(hairColor) && height.equals(height) && weight.equals(weight))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.eyeColor);
        hash = 79 * hash + Objects.hashCode(this.hairColor);
        hash = 79 * hash + Objects.hashCode(this.height);
        hash = 79 * hash + Objects.hashCode(this.weight);
        return hash;
    }
    
    public String update(int id, Statement statement)
    {
        String sql = "UPDATE PERSONCOLLECTION set name=" + q_surround(name) + ", eyeColor= " + q_surround(eyeColor ) + ", hairColor=" + q_surround(hairColor ) + ", height= " + q_surround(height) + ", weight=" + q_surround(weight) + "where id=" + this.id;
        return executeUpdate(sql, statement);
    }
    
    public String insert(Statement statement)
    {
       String sql = "SELECT name FROM PERSONCOLLECTION where name=" + q_surround(name) + " and eyeColor=" + q_surround(eyeColor) + " and hairColor=" + q_surround(hairColor) + " and height=" + q_surround(height) + " and weight=" +q_surround(weight);
        
        try
        {
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next())
                return "Person already exists";
        }
        catch(SQLException e)
        {
            return e.toString();
        }
        
        sql = "INSERT into PERSONCOLLECTION values (" + q_surround(name) + "," + q_surround(eyeColor) + "," + q_surround(hairColor) + "," + q_surround(height) + "," + q_surround(weight) + "," + id + ")";
        
        return executeUpdate(sql, statement);
    }
    
    public static String remove(int index, Statement statement)
    {
        String sql = "DELETE from PERSONCOLLECTION";
        if(index>=0)
            sql += " where id= " + index;
        return executeUpdate(sql, statement);
    }
    
    public static String executeUpdate(String sql, Statement statement)
    {
        String error = "";
        try
        {
            System.out.println("sql= " + sql);
            statement.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            error = e.toString();
        }
        return error;
    }
    
    public static String getPersons(Statement statement, ArrayList<DbPerson> personCollection)
    {
        String error = "";
        try
        {
            String sql = "select * from PERSONCOLLECTION";
            System.out.print("sql=" +sql);
            ResultSet rs = statement.executeQuery(sql);
            personCollection.clear();
            
            while(rs.next())
            {
                String t = rs.getString("name");
                String e = rs.getString("eyeColor");
                String hC = rs.getString("hairColor");
                String h = rs.getString("height");
                String w = rs.getString("weight");
                int ind = rs.getInt("id");
                DbPerson person = new DbPerson(t,e,hC,h,w,ind);
                personCollection.add(person);
            }
        }
        catch(SQLException e)
        {
            error = e.toString();
        }
        return error;
    }   
    private String q_surround(String s)
    {
        return "\'" + s + "\'";
    }
}