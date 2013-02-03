/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf1oop;

/**
 * Write a description of class Memorization here.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mem
{
    String s;
    String [] stringz;
    int [][] numz;
    public Mem (String s)
    {
        this.s=s;
    }
    
    // gets how many characters C are in the string s - C works as a dividing character
    
    public static int counts(String s, char c)
    {
        int count = 0;
        for(int i =0; i<s.length();i++)
        {
            if(s.substring(i,i+1).equals((""+c)))count++;
        }
        return count+1;
    }
    
    /*splits a string according to a dividing character - the result of this is 
     *an array of substrings.*/
    public void split( char c)
    {
        String [] stringz = new String [counts(s,c)];
        int u=0;
        String x="";
        for(int i=0;i<s.length();i++)
        {
            if(s.substring(i,i+1).equals(""+c) )
            {
                stringz [u]=x;
                u++;
                x="";
            } // if we hit a character that is equal to c, we cut the string
            else if(s.length()-1==i)
            {
                stringz [u]=x+s.substring (i,i+1);
                u++;
            } /* if this is the end of the string, we memorize all that has happened
               * since the last dividing character
               * /
            else
            {
                x=x+s.substring(i,i+1);
            }
                /* if we are not hitting a dividing character or at the end of the string
                 * just add it to the currently memorized substring and move on.
                 */
        }
        
        for(int i=0;i<u;i++)
        {
            System.out.println(stringz[i]);
        }
        this.stringz=stringz;
    }
    
    public void splitnums(char c, int o)
    {
        String s=stringz[o];
        String s1="";
        String s2="";
        int i=0;
        while(!s.substring(i,i+1).equals(c+""))
        {
            i++;
        }
        s1=s.substring(0,i);
        s2=s.substring(i+1,s.length());
        int n1=Integer.parseInt(s1);
        int n2=Integer.parseInt(s2);
        numz[o][0]=n1;
        numz[o][1]=n2;
    }
    
    public void memgame(char c)
    {
        numz=new int [stringz.length][2]; 
        for(int i=0; i<stringz.length;i++)
        {
            splitnums(c,i);
        }
    }
    
    public void print()
    {
        for(int i=0; i<numz.length;i++)
        {
            System.out.println(numz[i][0] + " , " + numz[i][1] );
        }
    }
    
    public static void main()
    {
        Mem m= new Mem("12,13;15,16;11,15;19,19");
        m.split(';');
        m.memgame(',');
        m.print();
    }
}
