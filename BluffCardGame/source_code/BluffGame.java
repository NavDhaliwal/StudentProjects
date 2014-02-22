import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
//for music
import javax.media.*;
import java.net.*;
public class BluffGame extends JFrame
{
	//for music
	static Player player1;
	String str="";

	public BluffGame()
	{
         super("Bluff by Navjot'09'") ;

         setSize(1200,750) ;
         try
		        {
					//for playing music
		          	player1=Manager.createRealizedPlayer(new URL("file:music\\1.mp3")) ;

		 		}
		 		catch(Exception e)
		 		{
				}
		 player1.start();


		 MenuBar mbar = new MenuBar();
		 	setMenuBar(mbar);
		    Menu game = new Menu("Game");
		 	Menu help = new Menu("Help");
		 	MenuItem item1, item2, item3, item4, item5;
		 	game.add(item1 = new MenuItem("New Game"));
		 	game.add(item2 = new MenuItem("Save Game"));
		 	game.add(item3 = new MenuItem("Close"));
	        help.add(item4 = new MenuItem("How to Play"));
		 	help.add(item5 = new MenuItem("About"));
		 		 mbar.add(game);
                 mbar.add(help);
                 item1.addActionListener(new menu(this));
				 item2.addActionListener(new menu(this));
				 item3.addActionListener(new menu(this));
				 item4.addActionListener(new menu(this));
                 item5.addActionListener(new menu(this));
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setContentPane(new CardDemoGUI());
		 pack() ;
		setVisible(true);

    }

    public static void main(String[] args)
    {

        new BluffGame() ;
    }

}