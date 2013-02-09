/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf1oop;

/**
 *
 * 
 */
public class Board{

public  int[][] cells;    



/*
 * @Matus - So, here I modified the code Bilyana did in the first week
 * What happens is - we start the function on a particular sotne with the 
 * coordinates p and q. We assign the value in the cell to k and automatically
 * set the value in p q to the sentinel value of -k.
 * 
 * The first round of ifs checks for liberties (0-cells) if a liberty is found 
 * the liberty integer is updated and the value in the cell is changed to -9
 * 
 * The second round of ifs uses the main part of the taken algorithm from Bilyana's
 * source code. This one checks for any friendly neighbourong stones. If such a neighbour
 * is found, we recurse on it.
 * 
 * Once all the recursions are done we have all the liberties
 * 
 * 
 * We will need to run a rewirte function after the use of this to change what was made
 * negative ;)
 */
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

/*
 * The previously mentioned work from the 1st week - the simple area of the 
 * islnd recursive algorithm. During the 2nd week's session we had an idea
 * that we can actually reuse this algorithm, as when we imprison some stones
 * we will need to calculate the area of their island so as to add the proper 
 * value for the imprisonment to the score.
 */

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

/*
 * The previously mentioned rewrite function, we may need to redo this one
 * later on, but so far it fixes some -9,-1 and -2 changes.
 */
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
/*
 * An elegant use of the imprison function. If the return value is 0 (0 liberties)
 * the stones are imprisoned, therefore puting the stone there would mean suicide.
 * If this is not the case, the move is considered perfectly viable.
 * 
 * I made it a boolean, so that when we play a move this funciton will run. If 
 * the output is true, the stone will be placed on the board. If it is false,
 * the we will get and error message.
 */

public boolean suicide (int a, int b)
{
    if(imprison(a,b)==0){return false;}
    else return true;
}

/*
 * The Ko rule - imprisonment takes priority over suicide.
 * 
 * it is in a very raw state at the moment, but it should work.
 * 
 * the round of ifs checks if a neighbour is an enemy (2) and if the liberty
 * of the enemy's stone is 0. If this is so, it will run the taken funciton, making
 * every part of the enemy island 0. We will need to add a parameter for player
 * and have an addition of a player score integer variable (actually a double would be better).
 * we will add the value of the taken function to the scores afterwards.
 * 
 * Additional idea - maybe splitting the score into different ways of achieveing
 * (territory, komi and imprisonment), for better statistics
 */

public boolean ko(int p, int q)
{
    boolean done=false;
    if (p>0 && cells [p-1][q] == 2 && imprison(p-1,q)==0) {taken(p-1,q);done=true;}
    if (p<8 && cells [p+1][q] == 2 && imprison(p+1,q)==0) {taken(p+1,q);done=true;}
    if (q>0 && cells [p][q-1] == 2 && imprison(p,q-1)==0) {taken(p,q-1);done=true;}
    if (q<8 && cells [p][q+1] == 2 && imprison(p,q+1)==0) {taken(p,q+1);done=true;}
    if(done==false)
    {
        return suicide(p,q);
    }
    else
    {
        return true;
    }
/*
 * Note: there is a slight problem with ko, and that is that you are not allowed to
 * avenge a ko through a ko in the very next turn. We will have to have a thought about that.
 */    
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





