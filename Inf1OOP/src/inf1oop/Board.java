/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf1oop;

/**
 *
 * @author admin
 */
public class Board{

public  int[][] cells;    


public int imprison (int p, int q)
{
int liberty=0;
int k = cells[p][q];
cells[p][q]=-k;
if (p>0 && cells [p-1][q] == 0) {liberty+= 1; cells [p-1][q]=-9;}
if (p<8 && cells [p+1][q] == 0) {liberty+= 1; cells [p+1][q]=-9;}
if (q>0 && cells [p][q-1] == 0) {liberty+= 1; cells [p][q-1]=-9;}
if (q<8 && cells [p][q+1] == 0) {liberty+= 1; cells [p][q+1]=-9;}

if (p>0 && cells [p-1][q] == k) {liberty+= imprison(p-1, q);}
if (p<8 && cells [p+1][q] == k) {liberty+= imprison (p+1, q);}
if (q>0 && cells [p][q-1] == k) {liberty+= imprison(p,q-1);}
if (q<8 && cells [p][q+1] == k) {liberty+= imprison (p,q+1);}

return liberty;
 
}

public int taken (int p, int q)
{
int area=1;
int k = cells[p][q];
cells[p][q]=0;

if (p>0 && cells [p-1][q] == k) {area+= taken(p-1, q);}
if (p<8 && cells [p+1][q] == k) {area+=taken (p+1, q);}
if (q>0 && cells [p][q-1] == k) {area+= taken(p,q-1);}
if (q<8 && cells [p][q+1] == k) {area+= taken (p,q+1);}



return area;
 


}


public void rewirte()
{
    for(int i=0;i<cells[0].length;i++)
    {
        for(int j=0;j<cells[0].length;j++)
        {
            if(cells[i][j]==-9){cells[i][j]=0;}
            else if(cells[i][j]==-1){cells[i][j]=1;}
            else if(cells[i][j]==-2){cells[i][j]=2;}
        }
    }
}

public boolean suicide (int a, int b)
{
    if(imprison(a,b)==0){return false;}
    else return true;
}

public boolean ko(int p, int q)
{
    boolean done=false;
    if (p>0 && cells [p-1][q] == 2 && imprison(p-1,q)==0) {taken(p-1,q);done=true;}
    if (p<8 && cells [p+1][q] == 2 && imprison(p+1,q)==0) {taken(p+1,q);done=true;}
    if (q>0 && cells [p][q-1] == 2 && imprison(p,q-1)==0) {taken(p,q-1);done=true;}
    if (q<8 && cells [p][q+1] == 2 && imprison(p,q+1)==0) {taken(p,q+1);done=true;}
    if(done==false)
    {
        return suicide(int a, int b)
    }
    else
    {
        return true;
    }
    
}
    public static void main (int n) {

    Board b=new Board();
        int m = (n);
        if (m != 13 && m!= 19 && m!= 9) System.out.println("Enter 9, 13 or 19");
        else {
        b.cells = new int [m][m];
        for (int i=0; i<m; i++)
        for (int j=0; j<m; j++) 
            b.cells [i][j] = (int)(Math.random()*3);
        
        for (int i=0; i<m; i++)
        { for (int j = 0; j<m; j++)
            {System.out.print(b.cells[i][j] + " ");}
        System.out.println();}
 
System.out.println(b.imprison(5,5));


        }}}





