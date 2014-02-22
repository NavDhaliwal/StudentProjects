import java.awt.event.*;
import java.awt.*;
import java.io.*;
public class menu extends CardDemoGUI implements ActionListener,Serializable
{


		String com="";
        //Thread tnew;
        BluffGame g;
        File fl=new File("myobject.data");
        menu(BluffGame g)
        {
			this.g=g;
		}
	public void actionPerformed(ActionEvent ae)
	{

		com=(String)ae.getActionCommand();
		if(com.equals("Save Game"))
		{
			newgamecounter=0;
			saved save=new saved();
			save.initX=initX;
			save.initX1=initX1;
			save.initX2=initX2;
			save.initX3=initX3;
			save.initY=initY;
			save.initY1=initY1;
			save.initY2=initY2;
			save.initY3=initY3;
			save.urturn=urturn;
			         save.truthcounter=truthcounter;
			         save.newgamecounter=newgamecounter;
			         save.posx2=posx2;
			         save.posy2=posy2;
			         save.add2=add2;
			         save.posx3=posx3;
			         save.posy3=posy3;
			         save.add3=add3;
			         save.posx4=posx4;
			         save.posy4=posy4;
			         save.add4=add4;
			         save.throwcounter=throwcounter;
			         save.z1=z1;save.z2=z2;save.z3=z3;save.z4=z4;
			         save.turn=turn;
			         save.pass=pass;
			         save.passcounter=passcounter;
			         save.add=add;save.posx=posx;save.posy=posy;
			         save.last=last;
			         save.des=des;
			         save.cfirst=cfirst;
    save.passpaint2=passpaint2;
    save.passpaint3=passpaint3;
    save.passpaint4=passpaint4;
    save.currentCard=currentCard;
    for(int i=0;i<52;i++)
    {
		save.deck[i]=deck[i];
		save.ndeck[i]=ndeck[i];
		save.player1[i]=player1[i];
		save.player2[i]=player2[i];
		save.player3[i]=player3[i];
		save.player4[i]=player4[i];
		save.thrown[i]=thrown[i];
		save.a[i]=a[i];

	}
	for(int i=0;i<8;i++)
	{
		save.index1[i]=index1[i];
		save.index2[i]=index2[i];
		save.index3[i]=index3[i];
		save.index4[i]=index4[i];
		save.showindex[i]=showindex[i];
	}
	//////////////////////////////////
	try{
		//fl=new File("myobject.data");
	// Write to disk with FileOutputStream
	FileOutputStream f_out = new
		FileOutputStream(fl);

	// Write object with ObjectOutputStream
	ObjectOutputStream obj_out = new
		ObjectOutputStream (f_out);

	// Write object out to disk
obj_out.writeObject (save);
System.out.println("Saved");
       }//try ends
       catch(IOException io)
       {
		   System.out.println("not saved "+io);
	   }
	//////////////////////////////////
		}
		try{
		if(com.equals("New Game"))
		{
			newgamecounter=1;
			//fl=new File("myobject.data");
			//if(fl.exists())
			//fl.delete();
			//only needed when music is on
            //BluffGame.player1.stop();
            g.dispose();
			new BluffGame();

		}}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
		if(com.equals("How to Play"))
		{
          final Frame f=new Frame("HOW TO PLAY");
          f.setSize(700,300);
          TextArea l=new TextArea();
          l.setFont(new Font("SansSerif", Font.BOLD, 24));
          l.setText("Trick to win is to finish all your cards before the other"+"\n"+" players either by bluffing about your cards or by truth."+"\n"+"You can make the last person to throw, to show its cards"+"\n"+" by pressing the show button."+"\n"+"You can also throw or pass when its your turn."+"\n"+"You will win if you have no cards left."+"\n"+"BEST OF LUCK.");
          l.setEditable(false);
          f.add(l);
          f.addWindowListener(new WindowAdapter()
          {
          public void windowClosing(WindowEvent we)
          {
		            f.setVisible(false);
	  }}
          );
          f.setVisible(true);
          }
          if(com.equals("About"))
          {
			  final Frame fmy=new Frame("ABOUT");
			  fmy.setSize(550,200);
			  TextArea l=new TextArea();
			            l.setFont(new Font("SansSerif", Font.BOLD, 24));
			            l.setText("This game was developed by NAVJOT DHALIWAL."+"\n"+"For ur comments plz feel free to mail me at"+"\n"+"navidhaliwal007@gmail.com");
			            l.setEditable(false);
			            fmy.add(l);
			            fmy.addWindowListener(new WindowAdapter()
			            {
			            public void windowClosing(WindowEvent we)
			            {
			  		            fmy.setVisible(false);
			  	  }}
			            );
          fmy.setVisible(true);

		  }
		  if(com.equals("Close"))
		  {
			  System.exit(1);
		  }
	}

}

